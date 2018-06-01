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


  registration: boolean;

  registrationSuccess: boolean;



  ngOnInit() {
    console.log(this.rls.user);
    this.determineSelectedOperation();
    this.rls.showRegistrationMessage = false;
  }

  register() {
    this.rls.registerUser();
  }


  login() {
    this.rls.loginUser();
  }
  determineSelectedOperation() {
    this.registration = (this.rls.currentOperation === UserAction.REGISTRATION) ? true : false;
  }

  changeToRegistrationScreen() {
    this.rls.currentOperation = UserAction.REGISTRATION;
    this.determineSelectedOperation();

  }

  changeToLoginScreen() {
    this.rls.currentOperation = UserAction.LOGIN;
    this.determineSelectedOperation();
  }


  onFileSelected(event){      
    this.rls.user.userImage = <File> event.target.files[0];
  } 

}
