import { constants } from './../../constants';
import { Component, OnInit } from '@angular/core';

import { User } from '../../models/User';
import { UserStates } from '../../models/UserStates';
import { UserAction } from '../../models/UserAction';
import { RegisterLoginService } from '../../services/registerLogin.service';
import { Router } from '@angular/router';

@Component({
  selector: 'registerLogin',
  templateUrl: './registerLogin.component.html',
  styleUrls: ['./registerLogin.component.css'],
  providers: [RegisterLoginService]
})
export class RegisterLoginComponent implements OnInit {


  constructor(private rls: RegisterLoginService, private router: Router) { }


  registration: boolean;

  registrationSuccess: boolean;

  private usernameValid: boolean = true;
  private passwordValid: boolean = true;
  private countryValid: boolean = true;
  private cityValid: boolean = true;
  private telephoneValid: boolean = true;
  private mailValid: boolean = true;
  private userImageValid: boolean = true;
  
  private errorMessages : any = constants.ERROR_MESSAGES;
  
  ngOnInit() {
    //this.router.navigate(['/home']);
  
    if (this.rls.isUserLogged()) {
          this.router.navigate(['/home']);
    } else {
      this.determineSelectedOperation();
      this.rls.showRegistrationMessage = false;

    }
  }

  register() {
    if(this.validUserInformation()){
         this.rls.registerUser();
    }
  }


  login() {
if(this.validLoginUserInformation()){
    this.rls.loginUser();
}
  }
  determineSelectedOperation() {
    this.registration = (this.rls.currentOperation === UserAction.REGISTRATION) ? true : false;
  }

  changeToRegistrationScreen() {
    this.clearInvalidMessages();
    this.rls.currentOperation = UserAction.REGISTRATION;
    this.determineSelectedOperation();

  }

  changeToLoginScreen() {
    this.clearInvalidMessages();
    this.rls.currentOperation = UserAction.LOGIN;
    this.determineSelectedOperation();
  }


  onFileSelected(event) {
    this.rls.user.userImage = <File>event.target.files[0];
  }

  private validLoginUserInformation(){
    var userInformation : User = this.rls.user; 
    
    this.usernameValid = (userInformation.username.match(constants.REGULAR_EXPRESSIONS.usernameRegex) != null);
    this.passwordValid = (userInformation.password.match(constants.REGULAR_EXPRESSIONS.passwordRegex) != null);
  
    return (this.usernameValid && this.passwordValid);
  }
  private validUserInformation(){
    var userInformation : User = this.rls.user; 
    
    this.usernameValid = (userInformation.username.match(constants.REGULAR_EXPRESSIONS.usernameRegex) != null);
    this.passwordValid = (userInformation.password.match(constants.REGULAR_EXPRESSIONS.passwordRegex) != null);
    this.cityValid = (userInformation.city.match(constants.REGULAR_EXPRESSIONS.cityRegex) != null);
    this.telephoneValid = (userInformation.telephone.match(constants.REGULAR_EXPRESSIONS.telephoneRegex) != null);
    this.mailValid = (userInformation.mail.match(constants.REGULAR_EXPRESSIONS.mailRegex) != null);
    this.userImageValid = this.rls.user.userImage != null;
    
    return (this.usernameValid && this.passwordValid && this.cityValid
       && this.telephoneValid && this.mailValid && this.userImageValid);
  }

  private clearInvalidMessages(){ 
    this.usernameValid = this.passwordValid =  this.cityValid = this.telephoneValid =  this.mailValid =  this.userImageValid = true;
  }
}
