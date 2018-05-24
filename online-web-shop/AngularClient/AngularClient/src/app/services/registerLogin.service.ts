import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { HttpClient, HttpHeaders, HttpParams } from '@angular/common/http';

import { BackEndService } from '../services/backEndService.service';
import { User } from '../models/User';


@Injectable()
export class RegisterLoginService {



    constructor(private http: HttpClient, private backendServer: BackEndService) { }



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
                console.log("POST call successful value returned in body",
                    val);
            },
            response => {
                console.log("POST call in error", response);
            },
            () => {
                console.log("The POST observable is now completed.");
            });


    }

}