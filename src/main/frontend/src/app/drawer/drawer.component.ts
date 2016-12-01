import {Component, ElementRef} from "@angular/core";
import {MaterialComponent} from "../core";

@Component({
  selector: 'app-drawer',
  templateUrl: './drawer.component.html',
  styleUrls: ['./drawer.component.css']
})
export class DrawerComponent extends MaterialComponent {

  constructor(_element: ElementRef) {
    super(_element);
  }

}

