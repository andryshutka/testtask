import {BrowserModule} from '@angular/platform-browser';
import {NgModule} from '@angular/core';

import {AppRoutingModule} from './app-routing.module';
import {AppComponent} from './app.component';
import {HeaderComponent} from "./header/header.component";
import {MatToolbarModule} from "@angular/material/toolbar";
import {CreationListComponent} from "./creation/list/creation-list.component";
import {HttpClientModule} from "@angular/common/http";
import {CreationService} from "./creation/creation.service";
import {CreationItemComponent} from "./creation/item/creation-item.component";
import {MatListModule} from "@angular/material/list";
import {MatIconModule} from "@angular/material/icon";
import {MatInputModule} from "@angular/material/input";
import {FormsModule} from "@angular/forms";
import {BrowserAnimationsModule} from "@angular/platform-browser/animations";
import {TextMaskModule} from "angular2-text-mask";
import {MatButtonModule} from "@angular/material/button";
import {MatChipsModule} from "@angular/material/chips";
import {ToasterModule} from "angular2-toaster";

@NgModule({
    declarations: [
        AppComponent,
        HeaderComponent,
        CreationListComponent,
        CreationItemComponent,
    ],
    imports: [
        BrowserModule,
        AppRoutingModule,
        MatToolbarModule,
        MatListModule,
        MatIconModule,
        MatInputModule,
        MatChipsModule,
        MatButtonModule,
        HttpClientModule,
        FormsModule,
        BrowserAnimationsModule,
        TextMaskModule,
        ToasterModule.forRoot()
    ],
    providers: [
        CreationService,
    ],
    bootstrap: [AppComponent]
})
export class AppModule {
}
