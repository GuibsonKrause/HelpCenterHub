import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Router } from '@angular/router';
import { environment } from '../../../../environment';
import { BehaviorSubject, Observable } from 'rxjs';

@Injectable({
  providedIn: 'root',
})
export class AuthService {
  private authUrl = `${environment.apiUrl}/home`;
  private loggedInSubject = new BehaviorSubject<boolean>(false);

  constructor(private http: HttpClient, private router: Router) {
    this.checkInitialLoginStatus();
  }

  getUserInfo(): Observable<any> {
    return this.http.get(`${environment.apiUrl}/userinfo`);
  }

  loginWithGoogle(): void {
    window.location.href = this.authUrl;
  }

  isLoggedIn() {
    return this.loggedInSubject.asObservable();
  }

  private checkInitialLoginStatus() {
    const token = localStorage.getItem('authToken');
    if (token) {
      // Potentially validate the token against your backend
      this.loggedInSubject.next(true);
    }
  }

  logout() {
    localStorage.removeItem('authToken');
    this.loggedInSubject.next(false);
    this.router.navigate(['/']); // Redirect to a public route 
  }
}
