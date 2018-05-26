import { Component, OnInit , Input } from '@angular/core';

import { User } from '../../models/User';
import { UserStates } from '../../models/UserStates';
import { RegisterLoginService } from '../../services/registerLogin.service';

import { HttpClient, HttpHeaders, HttpParams } from '@angular/common/http';
import { BackEndService } from '../../services/backEndService.service';
@Component({
  selector: 'home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css'],
  providers: [RegisterLoginService]
})
export class HomeComponent implements OnInit {

  @Input() user:User;

  constructor(private rls: RegisterLoginService, private http: HttpClient,private backendServer: BackEndService) { }

  showLgnScr: boolean;
  newAdvertisement : boolean;


  ngOnInit() { 
    this.showLgnScr = false;
    this.getAdvertisements();
  
  }


  advertisements: Object;
  
      getAdvertisements() : any {
  
        return  this.http.get(this.backendServer.getServer() + "edit/getAdvertisements")
          .subscribe(data => {
              this.advertisements = data;
          });
      }

  changeToLoginScreen() {
    this.showLgnScr = true;
  }


  createAdvertisementScreen(){
    this.newAdvertisement = true;
  }
}