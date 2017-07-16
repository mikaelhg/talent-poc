import { Injectable } from '@angular/core';
import { Http } from '@angular/http';

@Injectable()
export class LoginService {

  constructor(private http: Http) {
  }

  public login(username: string, password: string) {
    console.log(username, password);
  }

}
