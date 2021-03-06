import { AdvertisementFullInformationComponent } from './components/advertisement-full-information/advertisement-full-information.component';
import { MyAdvertisementsComponent } from './components/my-advertisements/my-advertisements.component';
import { SearchByAdvertisementGroupComponent } from './components/search-by-advertisement-group/search-by-advertisement-group.component';
import { CreateNewAdvertisementGroupComponent } from './components/create-new-advertisement-group/create-new-advertisement-group.component';
import { AdminComponent } from './components/admin/admin.component';
import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { RegisterLoginComponent } from '../app/components/registerLogin/registerLogin.component';
import { HomeComponent } from '../app/components/home/home.component';
import { AddAdvertisementComponent } from '../app/components/addAdvertisement/addAdvertisement.component';
import { ProfileComponent } from '../app/components/profile/profile.component'
import { AppComponent } from '../app/app.component';
const routes: Routes = [

  { path: '', redirectTo: '/login', pathMatch: 'full' },
  { path: 'login', component: RegisterLoginComponent },
  { path: 'app', component: AppComponent },
  { path: 'home', component: HomeComponent },
  { path: 'addAdvertisement', component: AddAdvertisementComponent },
  { path: 'admin', component: AdminComponent },
  { path: 'profile', component: ProfileComponent },
  { path: 'newAdvertisementGroup', component: CreateNewAdvertisementGroupComponent },  
  { path: 'searchByAdvertisementGroup', component: SearchByAdvertisementGroupComponent },  
  { path: 'myAdvertisement', component: MyAdvertisementsComponent },  
  { path: 'advertisementFullInformation', component: AdvertisementFullInformationComponent },    
  { path: '**', component: RegisterLoginComponent }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }