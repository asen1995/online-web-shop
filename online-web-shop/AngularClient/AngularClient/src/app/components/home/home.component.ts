import { Component, OnInit } from '@angular/core';

import { User } from '../../models/User';
import { UserStates } from '../../models/UserStates';
import { RegisterLoginService } from '../../services/registerLogin.service';

@Component({
  selector: 'home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css'],
  providers: [RegisterLoginService]
})
export class HomeComponent implements OnInit {



  ngOnInit() {
  

  }


  changeToLoginScreen(){
    
  }
}
