import {Component, AfterViewInit, OnInit} from "@angular/core";
import {Router, ActivatedRoute, Params} from "@angular/router";
import {PeopleService, Person} from "../../services/people.service";

@Component({
  selector: 'personDetailComponent',
  templateUrl: './person-detail.template.html'
})
export class PersonDetailComponent implements AfterViewInit, OnInit {

  private person: Person;

  constructor(private route: ActivatedRoute, private router: Router, private service: PeopleService) {
  }

  public ngOnInit(): void {
    this.route.params
      .switchMap((params: Params) => this.service.getPerson(+params['id']))
      .subscribe((person: Person) => this.person = person);
  }

  public ngAfterViewInit(): void {

  }

}
