import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Ticket } from '../../shared/models/ticket.model';
import { TicketService } from '../../shared/services/ticket.service';

@Component({
  selector: 'app-ticket-list',
  templateUrl: './ticket-list.component.html',
  styleUrls: ['./ticket-list.component.css']
})
export class TicketListComponent implements OnInit {
  tickets: Ticket[] = [];
  userId: number = 1;
  filter: string = '';

  constructor(private route: ActivatedRoute, private ticketService: TicketService) {}

  ngOnInit() {
    this.route.paramMap.subscribe(params => {
      this.loadTickets(this.userId);
    });
  }

  loadTickets(userId: number, filter?: string) {
    this.ticketService.getTicketsByUserId(userId, filter)
      .subscribe(tickets => this.tickets = tickets);
  }

  onFilterChange(filterValue: string) {
    this.filter = filterValue;
    this.loadTickets(this.userId, this.filter); 
  }
}
