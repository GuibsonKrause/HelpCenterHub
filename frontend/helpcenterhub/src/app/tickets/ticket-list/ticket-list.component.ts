import { HttpClientModule } from '@angular/common/http';
import { FormsModule } from '@angular/forms';
import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { CommonModule } from '@angular/common';
import { Ticket } from '../../shared/models/ticket.model';
import { TicketService } from '../../shared/services/ticket.service';
import { ChangeDetectorRef } from '@angular/core';
import { HeaderComponent } from '../../header-component/header-component.component';
// Importações do Angular Material
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';
import { MatButtonModule } from '@angular/material/button';
import { MatCardModule } from '@angular/material/card';
import { MatToolbarModule } from '@angular/material/toolbar';
import { MatIconModule } from '@angular/material/icon';
import { MatChipsModule } from '@angular/material/chips';

@Component({
  standalone: true,
  imports: [
    CommonModule,
    FormsModule,
    HttpClientModule,
    HeaderComponent,
    MatFormFieldModule,
    MatInputModule,
    MatButtonModule,
    MatCardModule,
    MatToolbarModule,
    MatIconModule,
    MatChipsModule,
  ],
  selector: 'app-ticket-list',
  templateUrl: './ticket-list.component.html',
  styleUrls: ['./ticket-list.component.css']
})
export class TicketListComponent implements OnInit {
  tickets: Ticket[] = [];
  userId: number = 2;
  filter: string = '';

  constructor(private route: ActivatedRoute, private ticketService: TicketService, private cd: ChangeDetectorRef) { }

  ngOnInit() {
    this.route.paramMap.subscribe(params => {
      this.loadTickets(this.userId);
    });
  }

  loadTickets(userId: number, filter?: string) {
    this.tickets = [];
    this.ticketService.getTicketsByUserId(userId, filter)
      .subscribe(response => {
        if (response.content) {
          this.tickets = response.content.map(ticketData => ({
            id: ticketData.id,
            userId: ticketData.userId,
            subject: ticketData.subject,
            description: ticketData.description,
            status: ticketData.status,
            createdAt: ticketData.createdAt,
            closedAt: ticketData.closedAt
          }));
          this.cd.detectChanges();
        } else {
          console.error('Tickets data not found in response');
        }
      });
  }

  onFilterChange(event: Event) {
    const filterValue = (event.target as HTMLInputElement).value;
    this.filter = filterValue;
    this.loadTickets(this.userId, this.filter);
  }
}
