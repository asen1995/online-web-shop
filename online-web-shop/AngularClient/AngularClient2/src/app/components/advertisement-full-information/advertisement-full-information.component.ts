import { Router } from '@angular/router';
import { ImageDecoder } from './../../models/ImageDecoder';
import { User } from './../../models/User';
import { RegisterLoginService } from './../../services/registerLogin.service';
import { Advertisement } from './../../models/Advertisement';
import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-advertisement-full-information',
  templateUrl: './advertisement-full-information.component.html',
  styleUrls: ['./advertisement-full-information.component.css']
})
export class AdvertisementFullInformationComponent implements OnInit {


  private loggedUser: User;
  private advertisement: Advertisement;

  private imageDecoder: string = ImageDecoder.DECODER;
  constructor(private rls: RegisterLoginService, private router: Router) { }

  ngOnInit() {
    this.advertisement = this.getSelectedAdvertisement();
    console.log(this.advertisement);
    this.loggedUser = this.rls.getUser();
  }


  private  getSelectedAdvertisement(): Advertisement {
    return JSON.parse(localStorage.getItem("selectedAdvertisement"));
  }

  public openProfile(user: User) {
    localStorage.removeItem('selectedUser');
    localStorage.setItem("selectedUser", JSON.stringify(user));
    this.router.navigate(['/profile']);
  }

}
