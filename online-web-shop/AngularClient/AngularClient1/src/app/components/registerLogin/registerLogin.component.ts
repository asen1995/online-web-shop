import { Component, OnInit } from '@angular/core';
import { Http } from '@angular/http';
import { User } from '../../models/User';
import { UserStates } from '../../models/UserStates';

import { environment } from '../../../environments/environment';
@Component({
  selector: 'registerLogin',
  templateUrl: './registerLogin.component.html',
  styleUrls: ['./registerLogin.component.css']
})
export class RegisterLoginComponent implements OnInit {

 

  constructor(private http: Http) {}

   user : User = {
    username: '',
    password: '',
    country: '',
    city: '',
    telephone: '',
    mail: '',
    user_state : UserStates.SIMPLE_USER
   };
  


  registration: boolean = true;

  chosenOperation: string;
  buttonName: string;


  response: string;

  ngOnInit() {
    this.chosenOperation = this.registration ? "registration" : "login";
    this.buttonName = this.registration ? "register" : "login";

  }

  register() {
   
  }
}
