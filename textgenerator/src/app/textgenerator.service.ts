import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root',
})
export class TextGenerationService {
  private apiUrl = 'http://localhost:8085/api/text/generate'; // Update with your backend URL

  constructor(private http: HttpClient) {}

  generateText(prompt: string): Observable<any> {
    return this.http.post<any>(this.apiUrl, { prompt });
  }
}
