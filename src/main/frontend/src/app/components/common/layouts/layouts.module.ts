import {NgModule} from '@angular/core';
import {RouterModule} from '@angular/router';
import {BrowserModule} from '@angular/platform-browser';
import {BlankLayout} from './blank.component';
import {BasicLayout} from './basic.component';
import {NavigationModule} from '../navigation/navigation.module';
import {TopnavbarModule} from '../topnavbar/topnavbar.module';
import {FooterModule} from '../footer/footer.module';

@NgModule({
  declarations: [BlankLayout, BasicLayout],
  imports: [BrowserModule, RouterModule, NavigationModule, TopnavbarModule, FooterModule],
  exports: [BlankLayout, BasicLayout]
})

export class LayoutsModule {
}
