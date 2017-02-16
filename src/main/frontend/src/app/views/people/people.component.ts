import {Component, AfterViewInit, OnInit} from '@angular/core';
import {PeopleService, Person} from '../../services/people.service';

@Component({
  selector: 'peopleComponent',
  templateUrl: './people.template.html',
  styleUrls: ['./people.template.scss']
})
export class PeopleComponent implements AfterViewInit, OnInit {

  public people: Person[];

  constructor(private peopleService: PeopleService) {
  }

  public ngOnInit(): void {
    this.peopleService.getPeople()
      .subscribe((people: Person[]) => this.people = people);
  }

  public ngAfterViewInit(): void {
  }

}
