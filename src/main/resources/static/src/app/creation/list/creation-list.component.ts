import {AfterViewInit, Component, ElementRef, ViewChild} from "@angular/core";
import {CreationService} from "../creation.service";
import {Creation} from "../creation-i";
import {debounceTime, distinctUntilChanged, filter, tap} from "rxjs/operators";
import {fromEvent} from "rxjs";
import {ToasterService} from "angular2-toaster";

@Component({
    selector: 'app-creations-list',
    templateUrl: './creation-list.component.html',
    styleUrls: ['./creation-list.component.scss']
})
export class CreationListComponent implements AfterViewInit {

    public creations: Creation[] = [];
    public searchCriteria: string = '';
    public requestRunning: boolean = false;

    @ViewChild('input') input: ElementRef;

    constructor(private creationService: CreationService,
                private toasterService: ToasterService) {
    }

    ngAfterViewInit() {
        fromEvent(this.input.nativeElement, 'keyup')
            .pipe(
                filter(Boolean),
                debounceTime(300),
                distinctUntilChanged(),
                tap((text) => {
                    if (this.input.nativeElement.value) {
                        this.requestRunning = true;
                        this.searchCriteria = this.input.nativeElement.value;
                        this.searchCreations();
                    } else {
                        this.creations = [];
                    }
                })
            )
            .subscribe();
    }

    private searchCreations() {
        this.creationService.searchCreations(this.searchCriteria).subscribe(creations => {
                this.creations = creations;
                this.requestRunning = false;
            },
            (_) => this.toasterService.pop('error', 'Ooops!', 'Server Error!'));
    }
}
