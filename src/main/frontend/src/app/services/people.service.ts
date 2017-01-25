import {Injectable} from "@angular/core";
import {Observable} from "rxjs";

export class Person {

}

@Injectable()
export class PeopleService {

  public getPerson(id: number): Observable<Person> {
    return Observable.of(new Person());
  }

}
