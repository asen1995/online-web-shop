import { Component, OnInit , Input } from '@angular/core';

import { User } from '../../models/User';
import { UserStates } from '../../models/UserStates';
import { RegisterLoginService } from '../../services/registerLogin.service';

import { HttpClient, HttpHeaders, HttpParams } from '@angular/common/http';
import { BackEndService } from '../../services/backEndService.service';

import { ImageDecoder } from '../../models/ImageDecoder';
@Component({
  selector: 'profile',
  templateUrl: './profile.component.html',
  styleUrls: ['./profile.component.css'],
  providers: [RegisterLoginService]
})
export class ProfileComponent implements OnInit {

  @Input() loggedUser : User;

  imageDecoder : string = ImageDecoder.DECODER;

  constructor(private rls: RegisterLoginService, private http: HttpClient,private backendServer: BackEndService) { }

  
  ngOnInit() {  
    console.log(this.loggedUser);
  }
}
