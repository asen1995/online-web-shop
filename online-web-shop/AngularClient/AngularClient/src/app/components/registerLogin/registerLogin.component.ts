import { Component, OnInit } from '@angular/core';

import { User } from '../../models/User';
import { UserStates } from '../../models/UserStates';
import { UserAction } from '../../models/UserAction';
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
  


  registration: boolean;

  chosenOperation: string;
  buttonName: string;


  response: string;

  ngOnInit() {
    this.chosenOperation = this.registration ? "registration" : "login";
    this.buttonName = this.registration ? "register" : "login";
    this.determineSelectedOperation();

  }

   register() {
    this.rls.registerUser(this.user);
   }

   determineSelectedOperation(){
    this.registration = (this.rls.currentOperation ===  UserAction.REGISTRATION )? true:false;
   }

}
