import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { AppSettings } from 'src/app/settings/app.settings';
import { Character } from 'src/app/models/character';

@Injectable({
  providedIn: 'root'
})
export class CharacterService {

  constructor(private http : HttpClient) { }

  findAllCharacters(){
    return this.http.get(AppSettings.APP_URL + "/characters/");
  }

  findCharacterById(idCharacter : number){
    return this.http.get(AppSettings.APP_URL + "/characters/" + idCharacter);
  }

  saveCharacter ( character : Character) {
    return this.http.post(AppSettings.APP_URL + "/characters/", character);
  }

  sharCharacter(idCharacter : number, isShared : boolean){
    return this.http.get(AppSettings.APP_URL + "/characters/share/" + idCharacter + "/" + isShared );
  }

}
