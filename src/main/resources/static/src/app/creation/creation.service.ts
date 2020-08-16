import {Injectable} from "@angular/core";
import {Observable} from "rxjs";
import {HttpClient} from "@angular/common/http";
import {environment} from "../../environments/environment";
import {Creation} from "./creation-i";

@Injectable()
export class CreationService {

  private readonly baseUrl;

  constructor(private http: HttpClient) {
    this.baseUrl = environment.baseUrl;
  }

  public searchCreations(searchCriteria:string): Observable<Array<Creation>> {
    return this.http.get<Array<Creation>>(this.baseUrl + `/api/creations/search?q=${searchCriteria}`);
  }
}
