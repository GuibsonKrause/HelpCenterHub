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
import { MatPaginatorModule } from '@angular/material/paginator'; // Importação do MatPaginatorModule

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
    MatPaginatorModule, // Adicione o MatPaginatorModule às importações
  ],
  selector: 'app-ticket-list',
  templateUrl: './ticket-list.component.html',
  styleUrls: ['./ticket-list.component.css']
})
export class TicketListComponent implements OnInit {
  tickets: Ticket[] = [];
  userId: number = 1;
  filter: string = '';
  totalTickets = 0; // Total de tickets para paginação
  pageSize = 12; // Tickets por página
  currentPage = 0; // Página atual

  constructor(private route: ActivatedRoute, private ticketService: TicketService, private cd: ChangeDetectorRef) { }

  ngOnInit() {
    this.route.paramMap.subscribe(params => {
      // Inicialize a carga de tickets com a primeira página
      this.loadTickets(this.userId, this.filter, this.currentPage, this.pageSize);
    });
  }

  loadTickets(userId: number, filter: string, page: number, size: number) {
    // Atualize seu serviço para suportar paginação
    this.ticketService.getTicketsByUserIdAndPage(userId, filter, page, size).subscribe(response => {
      this.tickets = response.content;
      this.totalTickets = response.totalElements; // Atualize com o total de tickets retornados pelo backend
      this.cd.detectChanges();
    });
  }

  onFilterChange(event: Event) {
    const filterValue = (event.target as HTMLInputElement).value;
    this.filter = filterValue;
    this.loadTickets(this.userId, this.filter, this.currentPage, this.pageSize);
  }

  onPageChange(event: any) {
    this.currentPage = event.pageIndex;
    this.pageSize = event.pageSize;
    this.loadTickets(this.userId, this.filter, this.currentPage, this.pageSize);
  }
}
