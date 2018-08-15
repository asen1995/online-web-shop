import { Group } from './../../models/Group';
import { HttpClient, HttpHeaders, HttpParams } from '@angular/common/http';
import { BackEndService } from '../../services/backEndService.service';
import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-search-by-advertisement-group',
  templateUrl: './search-by-advertisement-group.component.html',
  styleUrls: ['./search-by-advertisement-group.component.css']
})
export class SearchByAdvertisementGroupComponent implements OnInit {


  private groups: Object;
  constructor(private http: HttpClient, private backendServer: BackEndService) { }

  ngOnInit() {
    this.getExistingGroups();
  }

  private getExistingGroups(): any {
    return this.http.get(this.backendServer.getServer() + "edit/getExistingGroups")
    .subscribe(data => {
      this.groups = data;   
      console.log(this.groups);
    });
  }
}
