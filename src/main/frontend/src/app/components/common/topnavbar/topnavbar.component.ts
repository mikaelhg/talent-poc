import { Component } from '@angular/core';
import { smoothlyMenu } from '../../../app.helpers';

declare const jQuery: JQueryStatic;

@Component({
    selector: 'topnavbar',
    templateUrl: './topnavbar.template.html'
})
export class TopnavbarComponent {

    private searchTerm: string;

    public toggleNavigation(): void {
        jQuery("body").toggleClass("mini-navbar");
        smoothlyMenu();
    }

    public submitSearch(): void {
      console.log(this.searchTerm);
      this.searchTerm = '';
    }

}
