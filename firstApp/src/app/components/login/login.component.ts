import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['../../resources/bootstrap/css/bootstrap.min.css','./login.component.css']
})
export class LoginComponent implements OnInit {

  errorMessage: string;
  

  constructor() { }

  ngOnInit() {
  }

}
