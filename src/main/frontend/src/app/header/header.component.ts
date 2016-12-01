import {Component, OnInit, ElementRef} from "@angular/core";
import {MaterialComponent} from "../core";

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css']
})
export class HeaderComponent extends MaterialComponent {

  constructor(_element: ElementRef) {
    super(_element);
  }

}
