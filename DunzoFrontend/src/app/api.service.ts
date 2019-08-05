import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class ApiService {
  apiURL: string = 'http://10.105.24.243:8080';

  constructor(private httpClient: HttpClient) { }

  public uploadImage() {
    return this.httpClient.get(`${this.apiURL}/upload`);
  }
  
}
