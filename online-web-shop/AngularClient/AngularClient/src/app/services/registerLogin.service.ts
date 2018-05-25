import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { HttpClient, HttpHeaders, HttpParams } from '@angular/common/http';

import { BackEndService } from '../services/backEndService.service';
import { User } from '../models/User';
import { UserAction } from '../models/UserAction';

import { constants } from '../constants';

@Injectable()
export class RegisterLoginService {


    currentOperation: UserAction;


    registrationSuccess: boolean;
    showRegistrationMessage: boolean = false;


    constructor(private http: HttpClient, private backendServer: BackEndService) { }

    setCurrentOperation(selectedOperation: UserAction) {
        this.currentOperation = selectedOperation;
    }

    registerUser(user: User) {

        const params =
            {
                params: new HttpParams()
                    .set('username', user.username)
                    .set("password", user.password)
                    .set("country", user.country)
                    .set("city", user.city)
                    .set("telephone", user.telephone)
                    .set("mail", user.mail)
                    .set("user_state", String(user.user_state))
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
            alert("suc");
            this.registrationSuccess = this.showRegistrationMessage = true;
        }else {
            alert("fal");
            this.registrationSuccess = false;
            this.showRegistrationMessage = true;
        }
    }
}