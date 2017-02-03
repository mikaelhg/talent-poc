import {Injectable} from "@angular/core";
import {Observable} from "rxjs";

export class Person {

  public id: number;

  public name: string;

  public title: string;

  public description: string;

  public locations: string[];

  public roles: string[];

  public skills: string[];

  public interests: string[];

  public picture: string;

}

@Injectable()
export class PeopleService {

  private data = [
    {
      id: 1,
      name: 'Donald Duck',
      title: 'Technical Architect',
      description: `Donald Duck is a cartoon character created in 1934 at Walt Disney Productions.
     Donald is an anthropomorphic white duck with a yellow-orange bill, legs, and feet.
      He typically wears a sailor shirt and cap with a bow tie.
       Donald is most famous for his semi-intelligible speech and his mischievous and temperamental personality.`,
      locations: ['Helsinki'],
      roles: ['Architect', 'Developer'],
      skills: ['Java', 'Cloud', 'Mobile', 'Full Stack'],
      interests: ['IoT', 'Kotlin'],
      picture: 'http://wondersofdisney.webs.com/pals/donald/donangryface.png'
    },
    {
      id: 2,
      name: 'Mickey Mouse',
      title: 'Interaction Designer',
      description: ``,
      locations: ['Tampere'],
      roles: ['Designer', 'Frontend Developer'],
      skills: ['Angular 2', 'React.JS', 'Mobile', 'Full Stack'],
      interests: ['Mobile', 'TypeScript'],
      picture: 'http://wondersofdisney.webs.com/mickeymouse/mickfaces/micksideface.gif'
    },
    {
      id: 3,
      name: 'Scrooge McDuck',
      title: 'Project Manager',
      description: ``,
      locations: ['Helsinki'],
      roles: ['Project Manager', 'Scrum Master'],
      skills: ['Project Management', 'Scrum', 'Kanban'],
      interests: ['Prince2', 'Princess3'],
      picture: 'http://wondersofdisney.webs.com/comics/ultraheroes/scroogepenny.png'
    }
  ];

  public getPerson(id: number): Observable<Person> {
    return Observable.of(this.data[id - 1] as Person);
  }

  public getPeople(): Observable<Person[]> {
    return Observable.of(this.data as Person[]);
  }

}
