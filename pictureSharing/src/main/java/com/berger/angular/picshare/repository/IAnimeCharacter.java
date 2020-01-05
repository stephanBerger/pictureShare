package com.berger.angular.picshare.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.berger.angular.picshare.entities.AnimeCharacter;

public interface IAnimeCharacter extends JpaRepository<AnimeCharacter, Long> {

}