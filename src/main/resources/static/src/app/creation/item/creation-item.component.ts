import {Component, Input} from "@angular/core";
import {Creation} from "../creation-i";

@Component({
  selector: 'app-creation-item',
  templateUrl: './creation-item.component.html',
  styleUrls: ['./creation-item.component.scss']
})
export class CreationItemComponent {

  constructor() {
  }

  @Input() public creation: Creation;

}
