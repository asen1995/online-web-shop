import { User } from './../../models/User';
import { Router } from '@angular/router';
import { Advertisement } from './../../models/Advertisement';
import { Component, OnInit, Input } from '@angular/core';

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

  constructor(private rls: RegisterLoginService, private http: HttpClient, private backendServer: BackEndService
  , private router: Router) { }

  showLgnScr: boolean;
  newAdvertisement: boolean;

  showUserProfile: boolean;
  imageDecoder: string = ImageDecoder.DECODER;

  private clickedAdvertisementSeePeople : any;

  private selectedUser: any;

  ngOnInit() {
    this.showLgnScr = false;
   debugger;
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
        console.log(this.advertisements);
     
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
   
    debugger;
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
          debugger;
        this.http.post(this.backendServer.getServer() + "edit/dislike",'', params)    
          .subscribe(
          (val) => {
            advertisement.loggedUserLike = false;
            advertisement.likes.likeCount--;
          },
          response => { },
          () => { });
    
      }


      deleteAdvertisement(advertisementId): void {     

        const params =
        {
          params: new HttpParams()
           
            .set("advertisementId",'advertisementId')          
  
        };
  
      this.http.post(this.backendServer.getServer() + "edit/deleteAdvertisement",'', params)
  
        .subscribe(
        (val) => { 
          
        },
        response => { },
        () => { });
  

      }

      private showAdvertisement(advertisement : Advertisement) {
        localStorage.removeItem('selectedAdvertisement');
        localStorage.setItem("selectedAdvertisement", JSON.stringify(advertisement));
        this.router.navigate(['/advertisementFullInformation']);    
      }

      public getUsersThatLikeThis(advertisement : any){
        advertisement.showUserLiked = true;
        this.clickedAdvertisementSeePeople = advertisement;
        this.getPeopleThatLiked(advertisement.likes.likeId);
      }
    

      getPeopleThatLiked(likeId): any {
        
            const params =
            {
                params: new HttpParams()
                    .set('likeId',likeId)
        
            };           
                return this.http.get(this.backendServer.getServer() + "edit/getPeopleThatLiked",params)
                  .subscribe(data => {
                    this.clickedAdvertisementSeePeople.peopleWhoLiked = data;
                    debugger;
              
                  });
              }

              openProfile(username){
                const params =
                {
                    params: new HttpParams()
                        .set('username', username)
                           
                };
    
            this.http.get(this.backendServer.getServer() + "login/getUserByUsername", params).subscribe(data => {
    
                // this.userIsLogged = true;
                // this.user.userId = data.userId;
                // this.user.username = data.username;
                // //  this.password: data.password,
                // this.user.password = null;
                // this.user.country = data.country;
                // this.user.city = data.city;
                // this.user.telephone = data.telephone;
                // this.user.mail = data.mail;
                // this.user.user_state = data.user_state;
                // this.user.userImages = data.images;
                this.selectedUser = data;
              localStorage.removeItem('selectedUser');
              localStorage.setItem("selectedUser", JSON.stringify(this.selectedUser));
              this.router.navigate(['/profile']);
        
            });
              }
}
