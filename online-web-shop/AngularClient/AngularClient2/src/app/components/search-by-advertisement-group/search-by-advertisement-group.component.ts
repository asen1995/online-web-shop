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


  private groups: Object[] = ['default1', 'default2'];

  private selectedGroupId: any;
  private selectedGroup: Object;

  advertisements: Object;
  imageDecoder: string = ImageDecoder.DECODER;

  constructor(private http: HttpClient, private backendServer: BackEndService) { }

  ngOnInit() {
    this.getExistingGroups();


    // const params =
    //   {
    //     params: new HttpParams()
    //       .set('username', 'asen1995')

    //   };

    // return this.http.get(this.backendServer.getServer() + "edit/getAdvertisementsByUsername", params)
    //   .subscribe(data => {
    //     this.advertisements = data;
    //     console.log(this.advertisements);
    //   });
  }

  private getExistingGroups(): any {
    return this.http.get(this.backendServer.getServer() + "edit/getExistingGroups")
      .subscribe(data => {
        this.groups = data;
        console.log(this.groups);
      });
  }


  private getAdvertisementByGroup(groupId): any {


    this.selectedGroup.groupId = 1;

    const params =
      {
        params: new HttpParams()
          .set('', this.selectedGroup.groupId);

      };

    return this.http.get(this.backendServer.getServer() + "edit/getAdvertisementByGroup", params)
      .subscribe(data => {
        this.advertisements = data;

      });
  }

  private onGroupSelected(groupId) {
    alert(groupId);
    this.getAdvertisementByGroup(groupId);
  }

}