import { Component, OnInit, Input } from '@angular/core';
import { User } from '../app/models/User';
import { UserStates } from '../app/models//UserStates';
import { RegisterLoginService } from '../app/services/registerLogin.service';
import { Router } from '@angular/router';
@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit {

  @Input() loggedUser: User;


  constructor(private rls: RegisterLoginService, private router: Router) { }
  ngOnInit() {

    if (this.loggedUser === null || this.loggedUser === undefined) {
      this.loggedUser = this.rls.getUser();
      console.log("home0;");
      console.log(this.loggedUser);


    }
  }



  logout(): void {
   
    this.rls.logout();
    this.router.navigate(['/home']);
  }
}
