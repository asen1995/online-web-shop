import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { RegisterLoginComponent } from '../app/components/registerLogin/registerLogin.component';
import { HomeComponent } from '../app/components/home/home.component';
import { AddAdvertisementComponent } from '../app/components/addAdvertisement/addAdvertisement.component';
import { ProfileComponent } from '../app/components/profile/profile.component'
const routes: Routes = [
  
  { path: '', redirectTo: '/login', pathMatch: 'full' },
  { path: 'login', component: RegisterLoginComponent },
  { path: 'home', component: HomeComponent },
  { path: 'addAdvertisement', component: AddAdvertisementComponent },
  { path: 'profile', component: ProfileComponent }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }