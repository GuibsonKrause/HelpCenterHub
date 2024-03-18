import { Component } from '@angular/core';
import { MatToolbarModule } from '@angular/material/toolbar';
import { MatButtonModule } from '@angular/material/button';
import { Router } from '@angular/router';
import { AuthService } from '../shared/services/auth.service';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css'],
  standalone: true,
  imports: [MatToolbarModule, MatButtonModule, CommonModule]
})
export class HeaderComponent {
  userName: string = '';
  constructor(private router: Router, private authService: AuthService) { }

  ngOnInit() {
    this.authService.getUserInfo().subscribe(userInfo => {
      this.userName = userInfo.name;
    });
  }

  redirectToTickets(): void {
    this.router.navigate(['/ticketlist']);
  }

  onLogout(): void {
    this.authService.logout();
  }
}
