import {Component} from '@angular/core';
import {LoginService} from '../../services/login.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.template.html',
  styleUrls: ['./login.template.scss']
})
export class LoginComponent {

  public username: string = '';

  public password: string = '';

  constructor(private loginService: LoginService) {
  }

  public tryLogin(): boolean {
    this.loginService.login(this.username, this.password);
    return false;
  }

}
