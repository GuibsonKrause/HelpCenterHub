import { TestBed, ComponentFixture } from '@angular/core/testing';
import { HeaderComponent } from './header.component';
import { AuthService } from '../shared/services/auth.service';
import { RouterTestingModule } from '@angular/router/testing';
import { BehaviorSubject, of } from 'rxjs';
import { Router } from '@angular/router';

describe('HeaderComponent', () => {
  let component: HeaderComponent;
  let fixture: ComponentFixture<HeaderComponent>;
  let authServiceMock: any;

  beforeEach(async () => {
    // Mock para AuthService
    authServiceMock = {
      getUserInfo: () => of({ name: 'Test User' }),
      logout: jasmine.createSpy('logout')
    };

    await TestBed.configureTestingModule({
      imports: [
        HeaderComponent, // Use o componente como um import devido à marcação standalone
        RouterTestingModule // Para mock de navegação
      ],
      providers: [
        { provide: AuthService, useValue: authServiceMock }
      ]
    }).compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(HeaderComponent);
    component = fixture.componentInstance;
    fixture.detectChanges(); // Inicializa ngOnInit()
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });

  it('should display user name after initialization', () => {
    expect(component.userName).toEqual('Test User');
  });

  it('should redirect to tickets page when redirectToTickets is called', () => {
    const router = TestBed.inject(Router);
    spyOn(router, 'navigate');
    component.redirectToTickets();
    expect(router.navigate).toHaveBeenCalledWith(['/ticketlist']);
  });

  it('should call logout method of AuthService when onLogout is called', () => {
    component.onLogout();
    expect(authServiceMock.logout).toHaveBeenCalled();
  });
});
