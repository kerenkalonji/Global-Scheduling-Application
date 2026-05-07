import { Component, OnInit } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { FormBuilder, FormGroup } from '@angular/forms';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit {

  welcomeMessages: string[] = [];

  reservationPrice: number = 150.00;

  rooms: any[] = [];

  roomsearch!: FormGroup;

  currentCustomer: any;

  constructor(
    private http: HttpClient,
    private fb: FormBuilder
  ) {}

  ngOnInit(): void {

    this.roomsearch = this.fb.group({
      checkin: [''],
      checkout: ['']
    });

    this.http.get<string[]>('http://localhost:8080/welcome')
      .subscribe(data => {
        this.welcomeMessages = data;
      });

  }

  onSubmit(): void {

    this.rooms = [
      {
        roomNumber: 405,
        price: 200,
        id: 1
      },
      {
        roomNumber: 406,
        price: 220,
        id: 2
      },
      {
        roomNumber: 407,
        price: 260,
        id: 3
      }
    ];

  }

  reserveRoom(roomId: number): void {
    console.log('Reserved room:', roomId);
  }

}
