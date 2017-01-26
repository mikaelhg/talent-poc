import {Component, AfterViewInit} from "@angular/core";

declare const jQuery: any;

@Component({
  selector: 'peopleComponent',
  templateUrl: './people.template.html',
  styleUrls: [ './people.template.scss' ]
})
export class PeopleComponent implements AfterViewInit {

  public ngAfterViewInit(): void {

  }

}
