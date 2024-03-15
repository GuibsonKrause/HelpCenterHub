import { HttpClientModule } from '@angular/common/http';
import { FormsModule } from '@angular/forms';
import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { CommonModule } from '@angular/common';
import { Ticket } from '../../shared/models/ticket.model';
import { TicketService } from '../../shared/services/ticket.service';
import { ChangeDetectorRef } from '@angular/core';
import { HeaderComponent } from '../../header-component/header.component';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';
import { MatButtonModule } from '@angular/material/button';
import { MatCardModule } from '@angular/material/card';
import { MatToolbarModule } from '@angular/material/toolbar';
import { MatIconModule } from '@angular/material/icon';
import { MatChipsModule } from '@angular/material/chips';
import { MatPaginatorModule } from '@angular/material/paginator';
import { MatDialog } from '@angular/material/dialog';
import { TicketCreateComponent } from '../ticket-create/ticket-create.component';
import { TicketFeedbackComponent } from '../ticket-feedback/ticket-feedback.component';
import { FeedbackService } from '../../shared/services/feedback.service';

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
    MatPaginatorModule,
  ],
  selector: 'app-ticket-list',
  templateUrl: './ticket-list.component.html',
  styleUrls: ['./ticket-list.component.css']
})
export class TicketListComponent implements OnInit {
  tickets: Ticket[] = [];
  userId: number = 1;
  filter: string = '';
  totalTickets = 0;
  pageSize = 12;
  currentPage = 0;

  constructor(private route: ActivatedRoute,
    private ticketService: TicketService,
    private cd: ChangeDetectorRef,
    public dialog: MatDialog,
    private feedbackService: FeedbackService,) { }

  ngOnInit() {
    this.route.paramMap.subscribe(params => {
      this.loadTickets(this.userId, this.filter, this.currentPage, this.pageSize);
    });
  }

  loadTickets(userId: number, filter: string, page: number, size: number) {
    this.ticketService.getTicketsByUserIdAndPage(userId, filter, page, size).subscribe(response => {
      this.tickets = response.content;
      this.totalTickets = response.totalElements;
      this.cd.detectChanges();
      this.checkFeedbackExistence();
    });
  }

  onFilterChange(event: Event) {
    const filterValue = (event.target as HTMLInputElement).value;
    this.filter = filterValue;
    this.loadTickets(this.userId, this.filter, this.currentPage, this.pageSize);
  }

  openNewTicketModal(): void {
    const dialogRef = this.dialog.open(TicketCreateComponent, {
      width: '1000px',
      data: { userId: this.userId }
    });

    dialogRef.afterClosed().subscribe(result => {
      console.log('The dialog was closed', result);
      this.route.paramMap.subscribe(params => {
        this.loadTickets(this.userId, this.filter, this.currentPage, this.pageSize);
      });
    });
  }

  openTicketDetails(ticket: any): void {
    this.dialog.open(TicketCreateComponent, {
      width: '1000px',
      data: ticket
    });
  }


  onPageChange(event: any) {
    this.currentPage = event.pageIndex;
    this.pageSize = event.pageSize;
    this.loadTickets(this.userId, this.filter, this.currentPage, this.pageSize);
  }

  openFeedbackModal(ticket: Ticket) {
    const dialogRef = this.dialog.open(TicketFeedbackComponent, {
      width: '400px',
      data: { ticketId: ticket.id, userId: this.userId }
    });

    dialogRef.afterClosed().subscribe(result => {
      this.loadTickets(this.userId, this.filter, this.currentPage, this.pageSize);
    });
  }

  checkFeedbackExistence() {
    this.tickets.forEach(ticket => {
      if (ticket.status === 'CLOSED') {
        this.feedbackService.getFeedback(ticket.id).subscribe({
          next: (feedback) => {
            ticket.hasFeedback = true;
          },
          error: (err) => {
            console.info(`No feedback found for ticket ${ticket.id}, which is expected if feedback has not been provided yet.`);
            ticket.hasFeedback = !!ticket.hasFeedback;
          }
        });
      }
    });
  }

  closeTicket(ticket: Ticket) {
    if (confirm("Are you sure you want to close this ticket?")) {
      this.ticketService.closeTicket(ticket.id).subscribe({
        next: () => {
          alert('Ticket has been successfully closed');
          ticket.status = 'CLOSED';
          this.loadTickets(this.userId, this.filter, this.currentPage, this.pageSize);
        },
        error: err => {
          console.error('Error closing ticket', err);
          alert('There was an error closing the ticket');
        }
      });
    }
  }

}
