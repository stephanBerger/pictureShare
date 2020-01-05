import { Component, OnInit } from '@angular/core';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { UserService } from 'src/app/services/user/user.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['../../resources/bootstrap/css/bootstrap.min.css','./login.component.css']
})
export class LoginComponent implements OnInit {

  errorMessage: string;
  private loginForm : FormGroup
  

  constructor( private userService : UserService, private formBuilder : FormBuilder , private router: Router) {

    this.loginForm = formBuilder.group({
      login: ['', [Validators.required]],
      password: ['', [Validators.required]]
    });
   }

  ngOnInit() {
  }

  loginUser(){
    if(this.loginForm.invalid){
      this.errorMessage="Mail and / or password is incorrect "
      return;
    }
    this.userService.login(this.login.value,this.password.value)
        .pipe().subscribe(data => {
          localStorage.setItem('currentUser', JSON.stringify(data));
          this.router.navigate(['/home']);
    
    },error => {
      if (error.status === 404) {
        this.errorMessage="Login non trouvé";
      }else if (error.status === 400){
        this.errorMessage="Une erreur est survenue pendant le login";
      }
    });
  }

  get login(){
    return this.loginForm.get('login')
  }

  get password(){
    return this.loginForm.get('password')
  }

}
