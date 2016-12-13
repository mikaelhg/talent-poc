import {Component, ElementRef, HostBinding} from "@angular/core";
import {MaterialComponent} from "../core";

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css']
})
export class HeaderComponent extends MaterialComponent {

  public title: string = 'Talent POC';

  @HostBinding('class.mdl-layout__header')
  public mdlLayoutHeader: boolean = true;

  @HostBinding('class.mdl-layout__header--scroll')
  public mdlJsLayoutHeaderScroll: boolean = true;

  constructor(_element: ElementRef) {
    super(_element);
  }

}
