import { Component } from '@angular/core';
import { smoothlyMenu } from '../../../app.helpers';

declare const jQuery: JQueryStatic;

@Component({
    selector: 'topnavbar',
    templateUrl: './topnavbar.template.html'
})
export class TopnavbarComponent {

    public toggleNavigation(): void {
        jQuery("body").toggleClass("mini-navbar");
        smoothlyMenu();
    }

}
