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
import { AppRoutingModule } from './/app-routing.module';
import { RegisterLoginService } from '../app/services/registerLogin.service';
import { AdminComponent } from './components/admin/admin.component';
import { ModalDialogModule } from 'ngx-modal-dialog';
import { CreateNewAdvertisementGroupComponent } from './components/create-new-advertisement-group/create-new-advertisement-group.component';
import { SearchByAdvertisementGroupComponent } from './components/search-by-advertisement-group/search-by-advertisement-group.component';
import { MyAdvertisementsComponent } from './components/my-advertisements/my-advertisements.component';
@NgModule({
  declarations: [
    AppComponent,
    RegisterLoginComponent,
    HomeComponent,
    AddAdvertisementComponent,
    ProfileComponent,
    AdminComponent,
    CreateNewAdvertisementGroupComponent,
    SearchByAdvertisementGroupComponent,
    MyAdvertisementsComponent  
  ],
  imports: [
    BrowserModule,
    FormsModule,
    HttpClientModule,
    AppRoutingModule,
    ModalDialogModule.forRoot()  
  ],
  providers: [BackEndService,RegisterLoginService],
  bootstrap: [AppComponent]
})
export class AppModule { }
