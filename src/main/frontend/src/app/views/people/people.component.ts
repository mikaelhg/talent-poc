import {Component, AfterViewInit} from "@angular/core";

declare const jQuery: any;

@Component({
  selector: 'peopleComponent',
  templateUrl: './people.template.html'
})
export class PeopleComponent implements AfterViewInit {

  public ngAfterViewInit(): void {

    jQuery("#sparkline1").sparkline([34, 43, 43, 35, 44, 32, 44, 48], {
      type: 'line',
      width: '100%',
      height: '50',
      lineColor: '#1ab394',
      fillColor: "transparent"
    });


  }

}
