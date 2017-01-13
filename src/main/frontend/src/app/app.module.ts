import {BrowserModule} from "@angular/platform-browser";
import {NgModule} from "@angular/core";
import {FormsModule} from "@angular/forms";
import {HttpModule} from "@angular/http";
import {MaterialModule} from "@angular/material";
import {AppComponent} from "./app.component";
import {DrawerComponent} from "./drawer/drawer.component";
import {HeaderComponent} from "./header/header.component";
import {RlTagInputModule} from 'angular2-tag-input';

import 'hammerjs';

@NgModule({
  declarations: [
    AppComponent,
    DrawerComponent,
    HeaderComponent
  ],
  imports: [
    BrowserModule,
    FormsModule,
    HttpModule,
    MaterialModule.forRoot(),
    RlTagInputModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule {
}
