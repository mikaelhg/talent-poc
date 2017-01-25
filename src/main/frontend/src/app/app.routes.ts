import {Routes} from "@angular/router";
import {LoginComponent} from "./views/login/login.component";
import {RegisterComponent} from "./views/register/register.component";
import {BlankLayout} from "./components/common/layouts/blank.component";
import {BasicLayout} from "./components/common/layouts/basic.component";
import {TeamsComponent} from "./views/teams/teams.component";
import {ProjectsComponent} from "./views/projects/projects.component";
import {PeopleComponent} from "./views/people/people.component";
import {PersonDetailComponent} from "./views/people/person-detail.component";

export const ROUTES: Routes = [
  {path: '', redirectTo: 'projects', pathMatch: 'full'},

  {
    path: '', component: BasicLayout,
    children: [
      {path: 'projects', component: ProjectsComponent},
      {path: 'projects/:id', component: ProjectsComponent},
      {path: 'people', component: PeopleComponent},
      {path: 'people/:id', component: PersonDetailComponent},
      {path: 'teams', component: TeamsComponent},
      {path: 'teams/:id', component: TeamsComponent},
      {path: 'mycv', component: PeopleComponent}
    ]
  },
  {
    path: '', component: BlankLayout,
    children: [
      {path: 'login', component: LoginComponent},
      {path: 'register', component: RegisterComponent}
    ]
  },

  {path: '**', component: ProjectsComponent}
];
