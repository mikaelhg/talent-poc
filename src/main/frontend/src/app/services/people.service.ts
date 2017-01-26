import {Injectable} from "@angular/core";
import {Observable} from "rxjs";

export class Person {

  public name: string = "Sauli Niinistö";

  public title: string = "President of Finland";

  public description: string =
    `A lawyer by education, Niinistö was Minister of Finance from 1996 to 2003 
    and the National Coalition Party (NCP) candidate in the 2006 presidential election. 
    He served as the Speaker of the Parliament of Finland from 2007 to 2011 and has been 
    the Honorary President of the European People's Party since 2002.`;

  public locations: string[] = ['Helsinki'];

  public roles: string[] = ['Architect', 'Developer'];

  public skills: string[] = ['Java', 'Cloud', 'Mobile', 'Full Stack'];

  public interests: string[] = ['IoT', 'Kotlin'];

  public picture: string = 'https://pbs.twimg.com/profile_images/673420359874428928/EZztR797.jpg';

}

@Injectable()
export class PeopleService {

  public getPerson(id: number): Observable<Person> {
    return Observable.of(new Person());
  }

}
