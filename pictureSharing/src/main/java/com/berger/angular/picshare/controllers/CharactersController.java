package com.berger.angular.picshare.controllers;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.berger.angular.picshare.entities.AnimeCharacter;
import com.berger.angular.picshare.repository.IAnimeCharacter;


@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/v1/characters")
public class CharactersController {

	@Autowired
	private IAnimeCharacter characterRepository;
	
	
	@GetMapping("/")
	public ResponseEntity<List<AnimeCharacter>> findAll() {
		return ResponseEntity.ok(this.characterRepository.findAll());
	}

	@GetMapping("/{idCharacter}")
	public ResponseEntity<Serializable> findCharacterById(
			@PathVariable(name = "idCharacter") Long idCharacter) {
		if (idCharacter == null) {
			return ResponseEntity.badRequest().body("Cannot find anime with null ID");
		}
		AnimeCharacter character = this.characterRepository.getOne(idCharacter);
		if (character == null) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(character);
	}

	@PostMapping("/")
	public ResponseEntity<Serializable> createCharacter(@RequestBody AnimeCharacter character) {
		if (character == null) {
			return ResponseEntity.badRequest().body("Cannot create character with empty fields");
		}
		return ResponseEntity.ok(this.characterRepository.save(character));
	}

	@DeleteMapping("/{idCharacter}")
	public ResponseEntity<String> deleteCharacter(
			@PathVariable(name = "idCharacter") Long idCharacter) {
		if (idCharacter == null) {
			return ResponseEntity.badRequest().body("Cannot remove character with null ID");
		}
		AnimeCharacter character = this.characterRepository.getOne(idCharacter);
		if (character == null) {
			return ResponseEntity.badRequest().body("Il n'y a pas d'id correspondant");
		}
		this.characterRepository.delete(character);
		return ResponseEntity.ok("Character removed with success");
	}

	@GetMapping("/share/{idCharacter}/{isShared}")
	public ResponseEntity<Serializable> shareCharacter(
			@PathVariable(name = "idCharacter") Long idCharacter,
			@PathVariable(name = "isShared") boolean isShared) {
		if (idCharacter == null) {
			return ResponseEntity.badRequest().body("Cannot remove character with null ID");
		}
		AnimeCharacter character = this.characterRepository.getOne(idCharacter);
		if (character == null) {
			return ResponseEntity.notFound().build();
		}
		character.setShared(!isShared);
		return ResponseEntity.ok(this.characterRepository.save(character));
	}
}