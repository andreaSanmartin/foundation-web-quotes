import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { mailSender } from '../models/mailSender';
import { environment } from '../../environments/environment';

@Injectable({
  providedIn: 'root'
})
export class MailsenderService {

  constructor(private http: HttpClient) { }

  enviarMail(toEmail: string, body: string, subjetct: string): Observable<mailSender> {
    const url = `${environment.apiUrl}/enviarMail?toEmail=${toEmail}&body=${body}&subjetct=${subjetct}`;
    return this.http.request<mailSender>('get', url);
  }

}
