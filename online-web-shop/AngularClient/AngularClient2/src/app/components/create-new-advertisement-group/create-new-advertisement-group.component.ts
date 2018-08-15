import { HttpClient, HttpHeaders, HttpParams } from '@angular/common/http';
import { BackEndService } from '../../services/backEndService.service';
import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-create-new-advertisement-group',
  templateUrl: './create-new-advertisement-group.component.html',
  styleUrls: ['./create-new-advertisement-group.component.css']
})
export class CreateNewAdvertisementGroupComponent implements OnInit {
  
  private success: boolean;

  private advertisementGroupName: string;
  
  constructor(private http: HttpClient, private backendServer: BackEndService) { }

  ngOnInit() {
  }


  private createAdvertisementGroup(): any {

    console.log("creating " + this.advertisementGroupName);

    this.success = true;
    const params =
      {
        params: new HttpParams()
          .set('advertisementGroupName', this.advertisementGroupName)
      };

    this.http.post(this.backendServer.getServer() + "edit/createAdvertisementGroup", params)

      .subscribe(
      (val) => {

      },
      response => { },
      () => { });


  }
}
