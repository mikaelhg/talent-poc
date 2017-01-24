import {Component, OnDestroy, AfterViewInit} from '@angular/core';

declare const jQuery: JQueryStatic;

@Component({
    selector: 'blank',
    templateUrl: './blank.template.html'
})
export class blankComponent implements AfterViewInit, OnDestroy {

    public ngAfterViewInit() {
        jQuery('body').addClass('gray-bg');
    }

    public ngOnDestroy() {
        jQuery('body').removeClass('gray-bg');
    }
}
