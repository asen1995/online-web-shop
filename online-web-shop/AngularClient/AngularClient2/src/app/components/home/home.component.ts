import { Component, OnInit, Input } from '@angular/core';

import { User } from '../../models/User';
import { UserStates } from '../../models/UserStates';
import { RegisterLoginService } from '../../services/registerLogin.service';

import { HttpClient, HttpHeaders, HttpParams } from '@angular/common/http';
import { BackEndService } from '../../services/backEndService.service';

import { ImageDecoder } from '../../models/ImageDecoder';
@Component({
  selector: 'home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css'],
  providers: [RegisterLoginService]
})
export class HomeComponent implements OnInit {

  @Input() loggedUser: User;

  constructor(private rls: RegisterLoginService, private http: HttpClient, private backendServer: BackEndService) { }

  showLgnScr: boolean;
  newAdvertisement: boolean;

  showUserProfile: boolean;
  imageDecoder: string = ImageDecoder.DECODER;

  ngOnInit() {
    this.showLgnScr = false;
   
    if (this.loggedUser === null || this.loggedUser === undefined) {
      this.loggedUser = this.rls.getUser();   
      if(this.loggedUser === null ){
         this.getAdvertisements();
    
      }else {
        this.getAdvertisementsByUsername();
      }
    }
  }


  advertisements: Object;

  getAdvertisements(): any {

    return this.http.get(this.backendServer.getServer() + "edit/getAdvertisements")
      .subscribe(data => {
        this.advertisements = data;
     
      });
  }

  
  getAdvertisementsByUsername(): any {

    const params =
    {
        params: new HttpParams()
            .set('username',this.loggedUser.username)

    };
    
        return this.http.get(this.backendServer.getServer() + "edit/getAdvertisementsByUsername",params)
          .subscribe(data => {
            this.advertisements = data;
      
          });
      }
    

  changeToLoginScreen() {
    this.showLgnScr = true;
  }


  createAdvertisementScreen() {
    this.newAdvertisement = true;
  }



  showProfile() {
    this.showUserProfile = true;
  }

  addLike(advertisement): void {
   
    var like = advertisement.likes;
   
    const params =
      {
        params: new HttpParams()
          .set('likeId', like.likeId)
          .set('likeCount', like.likeCount)
          .set("username", this.loggedUser.username)
          .set("type",'ADV')
        

      };

    this.http.post(this.backendServer.getServer() + "edit/addLike",'', params)

      .subscribe(
      (val) => { 
        advertisement.loggedUserLike = true;
        advertisement.likes.likeCount++;
      },
      response => { },
      () => { });

  }

  dislike(advertisement): void {
    var like = advertisement.likes;
        const params =
          {
            params: new HttpParams()
              .set('likeId', like.likeId)
              .set('likeCount', like.likeCount)
              .set("username", this.loggedUser.username)
              .set("type",'ADV')
            
    
          };
    
        this.http.post(this.backendServer.getServer() + "edit/dislike",'', params)    
          .subscribe(
          (val) => {
            advertisement.loggedUserLike = false;
            advertisement.likes.likeCount--;
          },
          response => { },
          () => { });
    
      }

}
