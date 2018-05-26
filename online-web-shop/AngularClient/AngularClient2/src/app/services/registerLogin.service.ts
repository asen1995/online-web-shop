import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { HttpClient, HttpHeaders, HttpParams } from '@angular/common/http';

import { BackEndService } from '../services/backEndService.service';
import { User } from '../models/User';
import { UserAction } from '../models/UserAction';

import { constants } from '../constants';

import { UserStates } from '../models/UserStates';
@Injectable()
export class RegisterLoginService {
    
  user: User = {
    username: '',
    password: '',
    country: '',
    city: '',
    telephone: '',
    mail: '',
    user_state: UserStates.SIMPLE_USER
  };


    currentOperation: UserAction;


    registrationSuccess: boolean;
    showRegistrationMessage: boolean = false;

    userIsLogged : boolean = false;

    constructor(private http: HttpClient, private backendServer: BackEndService) { }

    setCurrentOperation(selectedOperation: UserAction) {
        this.currentOperation = selectedOperation;
    }

    registerUser() {

        const params =
            {
                params: new HttpParams()
                    .set('username', this.user.username)
                    .set("password", this.user.password)
                    .set("country", this.user.country)
                    .set("city", this.user.city)
                    .set("telephone", this.user.telephone)
                    .set("mail", this.user.mail)
                    .set("user_state", String(this.user.user_state))
            };


        this.http.post(this.backendServer.getServer() + "login/registerUser", '', params)

            .subscribe(
            (val) => {
                this.updateMessageStatus(val);
              
            },
            response => {

            },
            () => {

            });

    }
    updateMessageStatus(response){
        if (response == constants.REGISTRATION_SUCCESS ){
            this.registrationSuccess = this.showRegistrationMessage = true;
        }else {
            this.registrationSuccess = false;
            this.showRegistrationMessage = true;
        }
    }


    loginUser(){

        const params =
        {
            params: new HttpParams()
                .set('username',this.user.username)
                .set("password",this.user.password)
          
        };

         this.http.get(this.backendServer.getServer() + "login/loginUser",params).subscribe(data => {
             console.log(data);
             this.userIsLogged = true;
             alert(   this. userIsLogged);
           });
        }
    

}