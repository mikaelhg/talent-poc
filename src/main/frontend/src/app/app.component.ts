import {Component, NgZone, ElementRef, HostBinding} from "@angular/core";
import {MaterialComponent} from "./core";

@Component({
  selector: '#app',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss']
})
export class AppComponent extends MaterialComponent {

  @HostBinding('class.mdl-layout')
  public mdlLayout: boolean = true;

  @HostBinding('class.mdl-js-layout')
  public mdlJsLayout: boolean = true;

  constructor(private zone: NgZone, _element: ElementRef) {
    super(_element);
  }

}
