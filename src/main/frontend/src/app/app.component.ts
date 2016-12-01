import {Component, NgZone, ElementRef} from "@angular/core";
import {MaterialComponent} from "./core";

@Component({
  selector: '#app',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss']
})
export class AppComponent extends MaterialComponent {

  constructor(private zone: NgZone, _element: ElementRef) {
    super(_element);
  }

}
