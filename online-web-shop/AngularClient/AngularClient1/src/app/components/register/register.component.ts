import { Component } from '@angular/core';

@Component({
  selector: 'register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent {

   username : string;
   password : string;
   country  : string;
   city     : string;
   telephone: string;
   mail     : string;

   
}
