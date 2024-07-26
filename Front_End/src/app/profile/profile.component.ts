import { Component, OnInit } from '@angular/core';
import { UserservieService } from '../services/user-service.service';
import { User } from '../services/user';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';

@Component({
  selector: 'app-profile',
  standalone: true,
  imports: [CommonModule,FormsModule],
  templateUrl: './profile.component.html',
  styleUrl: './profile.component.css'
})
export class ProfileComponent {
  user: User | null = null;

  constructor(private userService: UserservieService) {}

  // ngOnInit() {
  //   this.userService.getCurrentUser().subscribe(user => {
  //     this.user = user;
  //   });
  // }
}
