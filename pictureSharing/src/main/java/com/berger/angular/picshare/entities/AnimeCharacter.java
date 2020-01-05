package com.berger.angular.picshare.entities;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class AnimeCharacter implements Serializable {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	private Long idCharacter;

	private String characterName;

	private String legend;

	private String category;

	private String strength;

	@Lob
	private byte[] photo;

	private boolean shared;

	@ManyToOne
	@JoinColumn(name = "idUser")
	private User user;

	public AnimeCharacter() {
	}

	public Long getIdCharacter() {
		return this.idCharacter;
	}

	public void setIdCharacter(Long idCharacter) {
		this.idCharacter = idCharacter;
	}

	public String getCharacterName() {
		return this.characterName;
	}

	public void setCharacterName(String characterName) {
		this.characterName = characterName;
	}

	public String getCategory() {
		return this.category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getStrength() {
		return this.strength;
	}

	public void setStrength(String strength) {
		this.strength = strength;
	}

	public byte[] getPhoto() {
		return this.photo;
	}

	public void setPhoto(byte[] photo) {
		this.photo = photo;
	}

	public boolean isShared() {
		return this.shared;
	}

	public void setShared(boolean shared) {
		this.shared = shared;
	}

	@JsonIgnore
	public User getUser() {
		return this.user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getLegend() {
		return this.legend;
	}

	public void setLegend(String legend) {
		this.legend = legend;
	}
}