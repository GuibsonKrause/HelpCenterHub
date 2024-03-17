import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Router } from '@angular/router';
import { catchError, tap } from 'rxjs/operators';
import { of } from 'rxjs';
import { environment } from '../../../../environment';

@Injectable({
  providedIn: 'root',
})
export class AuthService {
  private authUrl = `${environment.apiUrl}/home`;

  constructor(private http: HttpClient, private router: Router) {}

  loginWithGoogle(): void {
    window.location.href = this.authUrl;
  }

  // Método para tratar a redireção de volta com o código de autorização
  handleAuthRedirect(code: string): void {
    // Endpoint do seu backend que troca o código por um token de acesso e obtém informações do usuário
    const tokenExchangeUrl = `${environment.apiUrl}/auth/google/callback?code=${code}`;

    this.http.get<any>(tokenExchangeUrl).pipe(
      tap(res => {
        // Armazenar o token JWT ou a sessão, conforme recebido do backend
        localStorage.setItem('authToken', res.token);
        this.router.navigate(['/home']); // Redirecionar para a página inicial após o login
      }),
      catchError(error => {
        console.error('Error during token exchange:', error);
        return of(null); // Manejar erros, talvez redirecionando para a página de login novamente
      })
    ).subscribe();
  }

  // Outros métodos úteis para seu serviço de autenticação, como logout, verificação de token, etc.
}
