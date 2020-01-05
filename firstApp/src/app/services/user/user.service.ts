import { Injectable } from '@angular/core';
import { HttpClient, HttpParams } from '@angular/common/http';
import {  AppSettings } from 'src/app/settings/app.settings';
import { User } from 'src/app/models/user';

@Injectable({
  providedIn: 'root'
})
export class UserService {

  constructor(private http: HttpClient) {}

    findAllUsers(){
      return this.http.get(AppSettings.APP_URL + "/users/");
    }

    findUserById(idUser: number) {
      return this.http.get(AppSettings.APP_URL + "/users/" + idUser);
    }

    saveUser(user : User){
      return this.http.post(AppSettings.APP_URL + "/users/" , user);
    }

    login(mail : string,password: string){
      let param : HttpParams = new HttpParams();
      param.append("mail",mail);
      param.append("password",password);
      return this.http.post(AppSettings.APP_URL + "/users/login" , param);
    }

}
