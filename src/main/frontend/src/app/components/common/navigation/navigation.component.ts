import {Component, AfterViewInit} from '@angular/core';
import {Router} from '@angular/router';

declare const jQuery: JQueryStatic;

@Component({
    selector: 'navigation',
    templateUrl: './navigation.template.html'
})
export class NavigationComponent implements AfterViewInit {

    constructor(private router: Router) {
    }

    public ngAfterViewInit() {
        jQuery('#side-menu').metisMenu();
    }

    public activeRoute(routename: string): boolean {
        return this.router.url.indexOf(routename) > -1;
    }

}
