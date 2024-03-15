import { TestBed, ComponentFixture } from '@angular/core/testing';
import { FormsModule } from '@angular/forms';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { NoopAnimationsModule } from '@angular/platform-browser/animations';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';
import { TicketListComponent } from './ticket-list.component';
import { TicketService } from '../../shared/services/ticket.service';
import { MatDialog } from '@angular/material/dialog';
import { MatPaginatorModule } from '@angular/material/paginator';
import { Ticket, TicketsResponse } from '../../shared/models/ticket.model';

describe('TicketListComponent', () => {
  let component: TicketListComponent;
  let fixture: ComponentFixture<TicketListComponent>;
  let mockTicketService: jasmine.SpyObj<TicketService>;
  let mockDialog: jasmine.SpyObj<MatDialog>;
  let mockRoute: any;

  beforeEach(async () => {
    mockTicketService = jasmine.createSpyObj('TicketService', ['getTicketsByUserIdAndPage', 'closeTicket']);
    mockDialog = jasmine.createSpyObj('MatDialog', ['open']);
    mockRoute = { paramMap: of(new Map([['id', '123']])) };

    await TestBed.configureTestingModule({
      imports: [FormsModule, HttpClientTestingModule, NoopAnimationsModule, MatPaginatorModule, TicketListComponent],
      providers: [
        { provide: ActivatedRoute, useValue: mockRoute },
        { provide: TicketService, useValue: mockTicketService },
        { provide: MatDialog, useValue: mockDialog }
      ],
    }).compileComponents();

    fixture = TestBed.createComponent(TicketListComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });

  it('should load tickets on init', () => {
    const mockTickets: TicketsResponse = {
      content: [{ id: 1, userId: 1, subject: 'Test', description: 'Test Desc', status: 'OPEN', createdAt: '2023-01-01', closedAt: null }],
      pageable: {
        pageNumber: 0,
        pageSize: 12,
        sort: { empty: true, sorted: false, unsorted: true },
        offset: 0,
        unpaged: false,
        paged: true
      },
      totalPages: 1,
      totalElements: 1,
      last: true,
      first: true,
      size: 12,
      number: 0,
      sort: { empty: true, sorted: false, unsorted: true },
      numberOfElements: 1,
      empty: false
    };

    mockTicketService.getTicketsByUserIdAndPage.and.returnValue(of(mockTickets));

    component.ngOnInit();

    expect(mockTicketService.getTicketsByUserIdAndPage).toHaveBeenCalledWith(component.userId, component.filter, component.currentPage, component.pageSize);
    expect(component.tickets).toEqual(mockTickets.content);
    expect(component.totalTickets).toEqual(mockTickets.totalElements);
  });

});
