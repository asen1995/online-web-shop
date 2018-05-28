import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppComponent } from './app.component';
import { FormsModule } from '@angular/forms';


import { HttpClientModule } from '@angular/common/http';

import { BackEndService } from '../app/services/backEndService.service';

import { RegisterLoginComponent } from './components/registerLogin/registerLogin.component';
import { HomeComponent  } from '../app/components/home/home.component';
import { AddAdvertisementComponent } from '../app/components/addAdvertisement/addAdvertisement.component'
import { ProfileComponent } from '../app/components/profile/profile.component';
@NgModule({
  declarations: [
    AppComponent,
    RegisterLoginComponent,
    HomeComponent,
    AddAdvertisementComponent,
    ProfileComponent  
  ],
  imports: [
    BrowserModule,
    FormsModule,
    HttpClientModule
  ],
  providers: [BackEndService],
  bootstrap: [RegisterLoginComponent]
})
export class AppModule { }
