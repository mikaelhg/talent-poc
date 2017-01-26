import {NgModule} from "@angular/core";
import {BrowserModule} from "@angular/platform-browser";
import {PeopleComponent} from "./people.component";
import {PersonDetailComponent} from "./person-detail.component";
import {RouterModule} from "@angular/router";

@NgModule({
    declarations: [PeopleComponent, PersonDetailComponent],
    imports     : [BrowserModule, RouterModule],
})
export class PeopleModule {}
