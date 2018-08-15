import { User } from './../../models/User';
import { Component, OnInit, Input } from '@angular/core';

import { UserStates } from '../../models/UserStates';
import { RegisterLoginService } from '../../services/registerLogin.service';

import { HttpClient, HttpHeaders, HttpParams } from '@angular/common/http';
import { BackEndService } from '../../services/backEndService.service';

import { ImageDecoder } from '../../models/ImageDecoder';


@Component({
  selector: 'admin',
  templateUrl: './admin.component.html',
  styleUrls: ['./admin.component.css']
})
export class AdminComponent implements OnInit {



  @Input() loggedUser: User;
  imageDecoder: string = ImageDecoder.DECODER;




  constructor(private rls: RegisterLoginService, private http: HttpClient, private backendServer: BackEndService) { }


  ngOnInit() {
    this.getAdvertisementsForApprove();
  }


  getAdvertisementsForApprove(): any {

    return this.http.get(this.backendServer.getServer() + "edit/getAdvertisementsForApprove")
      .subscribe(data => {
        this.advertisements = data;
        console.log(this.advertisements);
      });


    // const params =
    //   {
    //     params: new HttpParams()
    //       .set('username', 'asen1995')

    //   };

    // return this.http.get(this.backendServer.getServer() + "edit/getAdvertisementsByUsername", params)
    //   .subscribe(data => {
    //     this.advertisements = data;

    //   });
  }


  private approveAdvertisement(advertisementId): any {
    console.log("approve advertisementId  " + advertisementId);
    const params =
      {
        params: new HttpParams()
          .set('advertisementId', advertisementId)
      };

    this.http.post(this.backendServer.getServer() + "admin/approveAdvertisement", '', params)
      .subscribe(
      (val) => {
        this.getAdvertisementsForApprove();
      },
      response => { },
      () => { });

  }


  private rejectAdvertisement(advertisementId): any {
    console.log("rejectAdvertisement advertisementId  " + advertisementId);
    const params =
      {
        params: new HttpParams()
          .set('advertisementId', advertisementId)
      };

    this.http.post(this.backendServer.getServer() + "admin/rejectAdvertisement", '', params)
      .subscribe(
      (val) => {
        this.getAdvertisementsForApprove();
      },
      response => { },
      () => { });

  }


}





   
            


 

  
}
