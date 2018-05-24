import { Component, OnInit } from '@angular/core';

import { User } from '../../models/User';
import { UserStates } from '../../models/UserStates';
import { RegisterLoginService } from '../../services/registerLogin.service';

@Component({
  selector: 'registerLogin',
  templateUrl: './registerLogin.component.html',
  styleUrls: ['./registerLogin.component.css'],
  providers: [RegisterLoginService]
})
export class RegisterLoginComponent implements OnInit {

 
  constructor(private rls: RegisterLoginService) { }


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
    this.rls.registerUser(this.user);
   }


}
