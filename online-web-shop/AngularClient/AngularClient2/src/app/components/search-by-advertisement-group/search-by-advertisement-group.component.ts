import { Advertisement } from './../../models/Advertisement';
import { Router } from '@angular/router';
import { ImageDecoder } from './../../models/ImageDecoder';
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


  private groups: Object[];

  private selectedGroupId: any;
  private selectedGroupName: any;
  
  private mostExpenciveB :boolean = false;
  private lessExpenciveB :boolean = false;
  private getTheLatestB :boolean = false;
  private getTheOldestB :boolean = false;
  
  
  private selectedGroup: Object;

  advertisements: Object;
  imageDecoder: string = ImageDecoder.DECODER;

  constructor(private http: HttpClient, private backendServer: BackEndService ,private router: Router) { }

  ngOnInit() {
    this.getExistingGroups();
  }

  private getExistingGroups(): any {
    return this.http.get(this.backendServer.getServer() + "edit/getExistingGroups")
      .subscribe(data => {       
        this.groups = data;
      });
  }


  private getAdvertisementByGroup(groupId): any {

    const params =
      {
        params: new HttpParams()
          .set('groupId', groupId)
          .set('mostExpencive', String(this.mostExpenciveB))
          .set('lessExpencive', String(this.lessExpenciveB))
          .set('getTheLatest', String(this.getTheLatestB))
          .set('getTheOldest', String(this.getTheOldestB))
          
      };

    return this.http.get(this.backendServer.getServer() + "edit/getAdvertisementByGroup", params)
      .subscribe(data => {
        this.advertisements = data;

      });
  }

  
  private mostExpencive(){
    this.mostExpenciveB = true;
    this.lessExpenciveB =this.getTheLatestB =this.getTheOldestB=false;    
    this.getAdvertisementByGroup(this.getAdvertisementByGroup(this.selectedGroupId));
  }
  private lessExpencive(){
   
    this.lessExpenciveB = true;
    this.mostExpenciveB = this. getTheLatestB =this.getTheOldestB =false;
    
    this.getAdvertisementByGroup(this.getAdvertisementByGroup(this.selectedGroupId));
  }
  private getTheLatest(){
    this.getTheLatestB = true;
    this.mostExpenciveB = this.lessExpenciveB =this.getTheOldestB =false;    
    this.getAdvertisementByGroup(this.getAdvertisementByGroup(this.selectedGroupId));
  }
  private getTheOldest(){
    this.getTheOldestB = true;
    this.mostExpenciveB = this.lessExpenciveB =this. getTheLatestB = false;
    this.getAdvertisementByGroup(this.getAdvertisementByGroup(this.selectedGroupId));
  }
  

  private onGroupSelected(groupName) {  
    this.selectedGroupName = groupName;
    this.selectedGroupId = this.getGroupId(groupName);
    this.getAdvertisementByGroup(this.selectedGroupId);
  
  }

  private getGroupId(groupName): any {
    for( var group = 0 ; group < this.groups.length; group ++){
      if(groupName === this.groups[group].groupName){
        return this.groups[group].groupId;
      }
    }
  }
  private showAdvertisement(advertisement : Advertisement) {
    localStorage.removeItem('selectedAdvertisement');
    localStorage.setItem("selectedAdvertisement", JSON.stringify(advertisement));
    this.router.navigate(['/advertisementFullInformation']);    
  }
}