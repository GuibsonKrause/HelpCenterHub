import { Component } from '@angular/core';
import { MatToolbarModule } from '@angular/material/toolbar';
import { MatButtonModule } from '@angular/material/button';
import { Router } from '@angular/router';
import { AuthService } from '../shared/services/auth.service';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css'],
  standalone: true,
  imports: [MatToolbarModule, MatButtonModule]
})
export class HeaderComponent {
  constructor(private router: Router, private authService: AuthService) { }

  redirectToTickets(): void {
    this.router.navigate(['/ticketlist']);
  }

  onLogout(): void {
    this.authService.logout();
  }
}
