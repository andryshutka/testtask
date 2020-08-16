import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {CreationListComponent} from "./creation/list/creation-list.component";


const routes: Routes = [
  {path: '' , component: CreationListComponent},
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
