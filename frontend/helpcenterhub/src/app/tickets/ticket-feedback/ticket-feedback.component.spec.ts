import { ComponentFixture, TestBed } from '@angular/core/testing';

import { TicketFeedbackComponent } from './ticket-feedback.component';

describe('TicketFeedbackComponent', () => {
  let component: TicketFeedbackComponent;
  let fixture: ComponentFixture<TicketFeedbackComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [TicketFeedbackComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(TicketFeedbackComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
