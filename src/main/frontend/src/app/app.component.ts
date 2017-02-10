import {Component, AfterViewInit} from "@angular/core";
import {correctHeight, detectBody} from "./app.helpers";

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss']
})
export class AppComponent implements AfterViewInit {

  public ngAfterViewInit() {
    // Run correctHeight function on load and resize window event
    jQuery(window).bind("load resize", function () {
      correctHeight();
      detectBody();
    });

    // Correct height of wrapper after metisMenu animation.
    jQuery('.metismenu a').click(() => {
      setTimeout(() => {
        correctHeight();
      }, 300)
    });
  }

}
