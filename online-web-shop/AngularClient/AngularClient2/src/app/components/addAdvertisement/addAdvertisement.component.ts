import { Component, OnInit , Input } from '@angular/core';
import { User } from '../../models/User';
import { UserStates } from '../../models/UserStates';
import { RegisterLoginService } from '../../services/registerLogin.service';

import { HttpClient, HttpHeaders, HttpParams } from '@angular/common/http';
import { BackEndService } from '../../services/backEndService.service';
@Component({
    selector: 'addAdvertisement',
    templateUrl: './addAdvertisement.component.html',
    styleUrls: ['./addAdvertisement.component.css']
})
export class AddAdvertisementComponent implements OnInit {


    @Input()  hero :string;

    ngOnInit() {
      

    }

}
