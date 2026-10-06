import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { FichaSocioeconomica } from '../models/ficha-socioeconomica';
import { Observable } from 'rxjs';
import { environment } from '../../environments/environment';

@Injectable({
  providedIn: 'root'
})
export class FichaSocioeconomicaService {
  private URL=environment.apiUrl + '/fichaSocioeconomica';
  constructor(private http:HttpClient) { }

  postFichaSocio(fichasocioeonomica:FichaSocioeconomica):Observable<any>{
    return this.http.post(`${this.URL}/addfichaSocioeconomica`,fichasocioeonomica);
  }
  getfichacedula(cedula: any): Observable<any>{
    return this.http.get<any>(this.URL+`/bycedula/${cedula}`);
  }
  getficha():Observable<any>{
    return this.http.get(`${this.URL}/listadoFichas`); 
  }
  

  getQueryUpdateFicha(query: String, ficha: FichaSocioeconomica) {
    const url = `${environment.apiUrl}/fichaSocioeconomica/${query}`;
    return this.http.request<FichaSocioeconomica>('put', url, {
      body: ficha
    });
  }
  updateFicha(ficha: FichaSocioeconomica): Observable<any> {
    const url = 'update-ficha';
    return this.getQueryUpdateFicha(url, ficha);
  }
}
