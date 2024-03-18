import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Router } from '@angular/router';
import { BehaviorSubject, Observable, catchError, map, of } from 'rxjs';
import { environment } from '../../../../environment';

@Injectable({
  providedIn: 'root',
})
export class AuthService {
  private authUrl = `${environment.apiUrl}/home`;
  // Inicializa como false assumindo que não sabemos se o usuário está logado até fazer uma verificação
  private loggedInSubject = new BehaviorSubject<boolean>(false);

  constructor(private http: HttpClient, private router: Router) {
    this.checkInitialLoginStatus();
  }

  getUserInfo(): Observable<any> {
    return this.http.get(`${environment.apiUrl}/userinfo`, { withCredentials: true });
  }

  loginWithGoogle(): void {
    window.location.href = this.authUrl;
  }

  checkInitialLoginStatus(): void {
    this.getUserInfo().subscribe({
      next: (userInfo) => {
        if (userInfo) {
          this.loggedInSubject.next(true);
        }
      },
      error: (error) => {
        console.error('Erro ao verificar status de login inicial', error);
        this.loggedInSubject.next(false);
      }
    });
  }

  logout(): void {
    // Neste ponto, o logout precisa ser tratado no backend para remover o cookie
    // Por exemplo, chamar um endpoint de logout que limpa o cookie
    // e então, limpar o estado no frontend
    this.loggedInSubject.next(false);
    this.router.navigate(['/']);
  }

  get isLoggedIn$(): Observable<boolean> {
    return this.loggedInSubject.asObservable();
  }
}
