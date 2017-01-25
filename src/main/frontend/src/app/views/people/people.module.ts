import {NgModule} from "@angular/core";
import {BrowserModule} from "@angular/platform-browser";
import {PeopleComponent} from "./people.component";
import {PersonDetailComponent} from "./person-detail.component";

@NgModule({
    declarations: [PeopleComponent, PersonDetailComponent],
    imports     : [BrowserModule],
})
export class PeopleModule {}
