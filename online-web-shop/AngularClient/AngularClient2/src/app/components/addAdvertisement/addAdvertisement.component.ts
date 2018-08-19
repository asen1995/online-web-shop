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
   
    private maxByteSizeOfFile: number = 1048576;

    constructor(private http: HttpClient, private backendServer: BackEndService, private rls: RegisterLoginService, private router: Router) { }

    @Input() loggedUser: User;

    selectedImage: File = null;
    private validFields:boolean;

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
            this.validateFields();        
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

    private validateFields(){  
        debugger;
        this.validFields =
        this.advertisement.title != null && this.advertisement.title != undefined && this.advertisement.title.length > 4 &&
        this.advertisement.information != null && this.advertisement.information != undefined && this.advertisement.information.length > 15 &&
        this.advertisement.price != null && this.advertisement.price != undefined && Number(this.advertisement.price) >= 0 &&
         this.selectedImage != null && this.selectedImage != undefined ;
        //  && Number(this.selectedImage.size) >  this.maxByteSizeOfFile;         
         
    }
}
