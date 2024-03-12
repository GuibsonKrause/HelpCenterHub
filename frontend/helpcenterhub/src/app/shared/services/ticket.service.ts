import { Injectable } from '@angular/core';
import { HttpClient, HttpParams } from '@angular/common/http';
import { Observable } from 'rxjs';
import { TicketsResponse } from '../models/ticket.model';
import { environment } from '../../../../environment';

@Injectable({
  providedIn: 'root'
})
export class TicketService {
  private apiUrl = `${environment.apiUrl}/tickets/user/`;

  constructor(private http: HttpClient) { }

  getTicketsByUserId(userId: number, filter?: string): Observable<TicketsResponse> {
    let params = new HttpParams();
    if (filter) {
      params = params.append('filter', filter);
    }

    return this.http.get<TicketsResponse>(this.apiUrl + userId, { params });
  }
}
