import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppComponent } from './app.component';
import { FormsModule } from '@angular/forms';
import { RegisterLoginComponent } from './components/registerLogin/registerLogin.component';

import { HttpClientModule } from '@angular/common/http';

import { BackEndService } from '../app/services/backEndService.service';


@NgModule({
  declarations: [
    AppComponent,
    RegisterLoginComponent
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
