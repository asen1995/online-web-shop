import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from '../../environments/environment';
@Injectable()
export class BackEndService {

  getServer() : string {
    return environment._SERVER;
  }
}