import {Component, ElementRef, HostBinding} from "@angular/core";
import {MaterialComponent} from "../core";

@Component({
  selector: 'app-drawer',
  templateUrl: './drawer.component.html',
  styleUrls: ['./drawer.component.css']
})
export class DrawerComponent extends MaterialComponent {

  @HostBinding('class.mdl-layout__drawer')
  public mdlLayoutDrawer: boolean = true;

  constructor(_element: ElementRef) {
    super(_element);
  }

}

