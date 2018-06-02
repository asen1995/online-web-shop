import { Component, OnInit, Input } from '@angular/core';
import { User } from '../../models/User';
import { UserStates } from '../../models/UserStates';
import { RegisterLoginService } from '../../services/registerLogin.service';

import { HttpClient, HttpHeaders, HttpParams } from '@angular/common/http';
import { BackEndService } from '../../services/backEndService.service';

import { Advertisement } from '../../models/Advertisement';
import { Router } from '@angular/router';
@Component({
    selector: 'addAdvertisement',
    templateUrl: './addAdvertisement.component.html',
    styleUrls: ['./addAdvertisement.component.css']
})
export class AddAdvertisementComponent implements OnInit {

    constructor(private http: HttpClient, private backendServer: BackEndService, private rls: RegisterLoginService, private router: Router) { }

    @Input() loggedUser: User;

    selectedImage: File = null;

    advertisement: Advertisement = {
        advertisementId: null,
        title: '',
        information: '',
        createDate: null,
        advertisementStatus: null,
        userId: null,
        price: null

    };

    createAdvertisement() {

        const params =
            {
                params: new HttpParams()
                    .set('advertisementId', String(this.advertisement.advertisementId))
                    .set("title", this.advertisement.title)
                    .set("information", this.advertisement.information)
                    .set("price", String(this.advertisement.price))
                    .set("userId", String(this.advertisement.userId))

            };

        const fileUpload = new FormData();
        fileUpload.append("image", this.selectedImage, this.selectedImage.name);

        this.http.post(this.backendServer.getServer() + "edit/createAdvertisement", fileUpload, params)

            .subscribe(
            (val) => {


            },
            response => { },
            () => { });

    }



    onFileSelected(event) {
        this.selectedImage = <File>event.target.files[0];
    }


    ngOnInit() {

        if (this.rls.isUserLogged()) {
            this.loggedUser = this.rls.getUser();

            this.advertisement.userId = this.loggedUser.userId;
        }
        else {
            this.router.navigate(['/login']);
        }
    }

}
