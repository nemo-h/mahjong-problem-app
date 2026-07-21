import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root',
})
export class SourceService {

  private baseUrl = 'http://localhost:8080/api/sources';

  constructor(private http: HttpClient) {}

  getSources(): Observable<any[]> {
    return this.http.get<any[]>(this.baseUrl);
  }

  createSource(request: any): Observable<any> {
    return this.http.post<any>(this.baseUrl, request);
  }

}
