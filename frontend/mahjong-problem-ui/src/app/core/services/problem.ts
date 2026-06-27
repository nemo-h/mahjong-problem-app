import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root',
})
export class ProblemService {

  private baseUrl = 'http://localhost:8080/api/problems';

  constructor(private http: HttpClient) {}

  getProblems(): Observable<any[]> {
    return this.http.get<any[]>(this.baseUrl);
  }

  getProblem(id: number): Observable<any> {
    return this.http.get<any>(`${this.baseUrl}/${id}`);
  }

  getAnswer(id: number): Observable<any> {
    return this.http.get<any>(`${this.baseUrl}/${id}/answer`);
  }

  createProblem(request: any): Observable<any> {
    return this.http.post<any>(this.baseUrl, request);
  }

  deleteProblem(id: number): Observable<any> {
    return this.http.delete<any>(`${this.baseUrl}/${id}`);
  }

}