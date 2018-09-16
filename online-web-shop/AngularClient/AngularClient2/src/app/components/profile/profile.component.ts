import { Advertisement } from './../../models/Advertisement';
import { Component, OnInit, Input } from '@angular/core';

import { User } from '../../models/User';
import { UserStates } from '../../models/UserStates';
import { RegisterLoginService } from '../../services/registerLogin.service';

import { HttpClient, HttpHeaders, HttpParams } from '@angular/common/http';
import { BackEndService } from '../../services/backEndService.service';

import { ImageDecoder } from '../../models/ImageDecoder';
import { Router } from '@angular/router';
@Component({
  selector: 'profile',
  templateUrl: './profile.component.html',
  styleUrls: ['./profile.component.css'],
  providers: [RegisterLoginService]
})
export class ProfileComponent implements OnInit {

  @Input() loggedUser: User;


  @Input() advertisementByUser: Advertisement;

  imageDecoder: string = ImageDecoder.DECODER;

  private selectedUser : User;

  constructor(private rls: RegisterLoginService, private http: HttpClient, private backendServer: BackEndService, private router: Router) { }


  ngOnInit() {
    debugger;
    if (this.isSelectedUser()) {
      this.updateProfileCount(this.selectedUser.username);
    }
    else if (this.rls.isUserLogged()) {
      this.loggedUser = this.rls.getUser();
    }
    else {
      this.router.navigate(['/login']);
    }

  }

  private isSelectedUser() {
  this.selectedUser = this.rls.getSelectedUser();
    if (this.selectedUser != null) {
      this.loggedUser = this.selectedUser;
      localStorage.removeItem('selectedUser');
      return true;
    }
    return false;
  }

  private updateProfileCount(username){
   debugger;
       const params =
         {
           params: new HttpParams()
             .set("username", this.loggedUser.username) 
         };
   
       this.http.post(this.backendServer.getServer() + "login/updateProfileCount",'', params)
   
         .subscribe(
         (val) => {            
         },
         response => { },
         () => { });
   
     }
  }


