Dogukan Celik
20200808071

```ts
// reservations.component.ts
import { Component } from '@angular/core';
import { ReservationService } from '../reservation.service';

@Component({
  selector: 'app-reservations',
  templateUrl: './reservations.component.html',
  styleUrls: ['./reservations.component.css']
})

export class ReservationsComponent {
  title = 'Last Reservations';
  lastReservations: any[] = [];
  noteInput: string = '';
  note: string = '';
  isNoteExist: boolean = false;
  
  constructor(private reservationService: ReservationService) { }
  
  ngOnInit(): void {
    this.reservationService.getLastReservations().then(
      (data) => {
        this.lastReservations = data;
      }
    );
  }

  addNote(): void {
    console.log('Variable changed:', this.note);
    this.note = this.noteInput;
    
    if(this.note.length != 0)
      this.isNoteExist = true;
  }
}
```

```html
<!-- reservations.component.html -->
<div class="container">
  <h3 class="my-3"> {{title}} </h3>
</div>

<ul *ngIf="lastReservations" class="list-group">
  <li class="list-group-item list-headings">
    <span>Customer ID</span>
    <span>Room ID</span>
    <span>First Name</span>
    <span>Last Name</span>
    <span>E-mail</span>
    <span>Phone</span>
    <span>Room Type</span>
    <span>Payment Amount</span>
    <span>Date Arrival</span>
    <span>Date Departure</span>
    <span>Status</span>
    <span></span>
  </li>

  <li *ngFor="let e of lastReservations" class="list-group-item">
    <div class="props">
      <span>{{e.customerId}}</span>
      <span>{{e.room}}</span>
      <span>{{e.customer.firstName}}</span>
      <span>{{e.customer.lastName}}</span>
      <span>{{e.customer.email}}</span>
      <span>{{e.customer.phone}}</span>
      <span>{{e.roomType}}</span>
      <span>{{e.paymentAmount}}</span>
      <span>{{e.dateArrival}}</span>
      <span>{{e.dateDeparture.substring(0, 10)}}</span>
      <span>{{e.status}}</span>
      <span>
        <input [(ngModel)]="noteInput" class="form-control" type="text" placeholder="Note...">
        <button (click)="addNote()" class="form-control btn btn-primary mt-1">
          <i class="fa-solid fa-note-sticky"></i> Save
        </button>
      </span>
    </div>
    <p *ngIf="isNoteExist" class="text-center">
      <b>Note:</b> {{note}}
    </p>
  </li>
</ul>
```

