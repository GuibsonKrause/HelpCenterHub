import { HttpClientModule } from '@angular/common/http';
import { FormsModule } from '@angular/forms';
import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { CommonModule } from '@angular/common';
import { Ticket } from '../../shared/models/ticket.model';
import { TicketService } from '../../shared/services/ticket.service';

@Component({
  standalone: true,
  imports: [CommonModule, FormsModule, HttpClientModule],
  selector: 'app-ticket-list',
  templateUrl: './ticket-list.component.html',
  styleUrls: ['./ticket-list.component.css']
})
export class TicketListComponent implements OnInit {
  tickets: Ticket[] = [];
  userId: number = 1;
  filter: string = '';

  constructor(private route: ActivatedRoute, private ticketService: TicketService) { }

  ngOnInit() {
    this.route.paramMap.subscribe(params => {
      this.loadTickets(this.userId);
    });
  }

  loadTickets(userId: number, filter?: string) {
    this.ticketService.getTicketsByUserId(userId, filter)
      .subscribe(tickets => console.log("issoaqui"+tickets));
  }

  onFilterChange(event: Event) {
    const filterValue = (event.target as HTMLInputElement).value;
    this.filter = filterValue;
    this.loadTickets(this.userId, this.filter);
  }
}
