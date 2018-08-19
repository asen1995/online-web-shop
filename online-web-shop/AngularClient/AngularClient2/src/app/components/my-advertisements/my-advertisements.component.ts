import { RegisterLoginService } from './../../services/registerLogin.service';
import { BackEndService } from './../../services/backEndService.service';
import { HttpClient, HttpParams } from '@angular/common/http';
import { ImageDecoder } from './../../models/ImageDecoder';
import { User } from './../../models/User';
import { Component, OnInit, Input } from '@angular/core';

@Component({
  selector: 'app-my-advertisements',
  templateUrl: './my-advertisements.component.html',
  styleUrls: ['./my-advertisements.component.css']
})
export class MyAdvertisementsComponent implements OnInit {

    private loggedUser: User;
    private groups: Object[];  
    private selectedGroupId: any;
    private selectedGroup: Object;
  
    advertisements: Object;
    imageDecoder: string = ImageDecoder.DECODER;
  
    constructor(private http: HttpClient, private backendServer: BackEndService,private userInformation: RegisterLoginService) { }
  
    ngOnInit() {

      this.loggedUser = this.userInformation.getUser();   
      this.getLoggedUserAdvertisements();
    }
  
    private getLoggedUserAdvertisements(): any {
  
      const params =
        {
          params: new HttpParams()
            .set('userId', String(this.loggedUser.userId))
        };
  
      return this.http.get(this.backendServer.getServer() + "edit/getLoggedUserAdvertisements", params)
        .subscribe(data => {
          this.advertisements = data;  
          console.log(this.advertisements);
        });
    }
    
    private deleteAdvertisement(advertisementId){
      alert(advertisementId);

      const params =
      {
        params: new HttpParams()
         
          .set("advertisementId",advertisementId)          

      };

    this.http.post(this.backendServer.getServer() + "edit/deleteAdvertisement",'', params)

      .subscribe(
      (val) => { 
        this.getLoggedUserAdvertisements();
      },
      response => { },
      () => { });

    }
}
