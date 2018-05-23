import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppComponent } from './app.component';
import { FormsModule } from '@angular/forms'; 
import { RegisterLoginComponent } from './components/registerLogin/registerLogin.component';
import {HttpModule} from '@angular/http';

@NgModule({
  declarations: [
    AppComponent,
    RegisterLoginComponent
  ],
  imports: [
    BrowserModule,
    FormsModule,
    HttpModule
  ],
  providers: [],
  bootstrap: [RegisterLoginComponent]
})
export class AppModule { }
