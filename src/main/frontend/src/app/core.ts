import {AfterViewInit, ElementRef} from "@angular/core";

declare var componentHandler: any;

export class MaterialComponent implements AfterViewInit {

  constructor(protected _element: ElementRef) {
  }

  public ngAfterViewInit(): void {
    componentHandler.upgradeElement(this._element.nativeElement);
  }

}
