import {BrowserModule} from "@angular/platform-browser";
import {NgModule} from "@angular/core";
import {FormsModule} from "@angular/forms";
import {HttpModule} from "@angular/http";
import {RlTagInputModule} from 'angular2-tag-input';

import {RouterModule} from "@angular/router";
import {LocationStrategy, HashLocationStrategy} from '@angular/common';

import {AppComponent} from "./app.component";
import {ROUTES} from "./app.routes";

import {LoginModule} from "./views/login/login.module";
import {RegisterModule} from "./views/register/register.module";

import {LayoutsModule} from "./components/common/layouts/layouts.module";
import {TeamsModule} from "./views/teams/teams.module";
import {ProjectsModule} from "./views/projects/projects.module";
import {PeopleModule} from "./views/people/people.module";


@NgModule({
  declarations: [
    AppComponent
  ],
  imports: [
    BrowserModule,
    FormsModule,
    HttpModule,

    RlTagInputModule,

    ProjectsModule,
    PeopleModule,
    TeamsModule,

    LoginModule,
    RegisterModule,

    LayoutsModule,
    RouterModule.forRoot(ROUTES)
  ],
  providers: [{provide: LocationStrategy, useClass: HashLocationStrategy}],
  bootstrap: [AppComponent]
})
export class AppModule {
}
