import {NgModule} from "@angular/core";
import {BrowserModule} from "@angular/platform-browser";
import {TopnavbarComponent} from "./topnavbar.component";
import {FormsModule} from "@angular/forms";

@NgModule({
    declarations: [TopnavbarComponent],
    imports: [BrowserModule, FormsModule],
    exports: [TopnavbarComponent],
})

export class TopnavbarModule {
}
