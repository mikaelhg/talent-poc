import {NgModule} from '@angular/core';
import {BrowserModule} from '@angular/platform-browser';
import {ProfileComponent} from './profile.component';
import {RouterModule} from '@angular/router';

@NgModule({
  declarations: [ProfileComponent],
  imports     : [BrowserModule, RouterModule],
})
export class ProfileModule {}
