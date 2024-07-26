import { User } from '../services/user';
import { FormsModule, NgForm } from '@angular/forms';
import { CommonModule, JsonPipe } from '@angular/common';
import { UserservieService } from '../services/user-service.service';
import { Component } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-login',
  standalone: true,
  imports: [FormsModule, CommonModule, JsonPipe],
  templateUrl: './login.component.html',
  styleUrl: './login.component.css',
})
export class LoginComponent {
  user: User = new User();

  constructor(private userservieService: UserservieService) {}

  userlogin(data: any) {
    this.userservieService.login(data);
  }
}
