import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { HttpClient, HttpHeaders, HttpParams } from '@angular/common/http';

import { BackEndService } from '../services/backEndService.service';
import { User } from '../models/User';
import { UserAction } from '../models/UserAction';

import { constants } from '../constants';

import { UserStates } from '../models/UserStates';
import { Router } from '@angular/router';
@Injectable()
export class RegisterLoginService {

    user: User = {
        userId: null,
        username: '',
        password: '',
        country: '',
        city: '',
        telephone: '',
        mail: '',
        user_state: UserStates.SIMPLE_USER,
        userImage: null,
        userImages: null
    };


    currentOperation: UserAction;


    registrationSuccess: boolean;
    showRegistrationMessage: boolean = false;

    userIsLogged: boolean = false;

    constructor(private http: HttpClient, private backendServer: BackEndService, private router: Router) { }

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

        const fileUpload = new FormData();

        if (this.user.userImage != null) {
            fileUpload.append("image", this.user.userImage, this.user.userImage.name);
        };

        this.http.post(this.backendServer.getServer() + "login/registerUser", fileUpload, params)

            .subscribe(
            (val) => {
                this.updateMessageStatus(val);

            },
            response => {

            },
            () => {

            });

    }
    updateMessageStatus(response) {
        if (response == constants.REGISTRATION_SUCCESS) {
            this.registrationSuccess = this.showRegistrationMessage = true;
            this.loginUser();
        } else {
            this.registrationSuccess = false;
            this.showRegistrationMessage = true;
        }
    }


    loginUser() {

        const params =
            {
                params: new HttpParams()
                    .set('username', this.user.username)
                    .set("password", this.user.password)

            };

        this.http.get(this.backendServer.getServer() + "login/loginUser", params).subscribe(data => {

            this.userIsLogged = true;
            this.user.userId = data.userId;
            this.user.username = data.username;
            //  this.password: data.password,
            this.user.password = null;
            this.user.country = data.country;
            this.user.city = data.city;
            this.user.telephone = data.telephone;
            this.user.mail = data.mail;
            this.user.user_state = data.user_state;
            this.user.userImages = data.images;

            localStorage.setItem("user", JSON.stringify(this.user));
            location.reload();
    
        });
    }


    getUser(): User {
        return JSON.parse(localStorage.getItem("user"));
    }
    
    getSelectedUser(): User {
        return JSON.parse(localStorage.getItem("selectedUser"));
    }
    isUserLogged(): boolean {

        if (localStorage.getItem("user") != null || localStorage.getItem("user") != undefined) {
            return true;
        }
        return false;
    }

    logout(): void {
        localStorage.removeItem('user');
        this.router.navigate(['/home']);
        location.reload();
    }

}