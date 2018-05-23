import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppComponent } from './app.component';
import { FormsModule } from '@angular/forms'; 
import { RegisterLoginComponent } from './components/registerLogin/registerLogin.component';


@NgModule({
  declarations: [
    AppComponent,
    RegisterLoginComponent
  ],
  imports: [
    BrowserModule,
    FormsModule
  ],
  providers: [],
  bootstrap: [RegisterLoginComponent]
})
export class AppModule { }
