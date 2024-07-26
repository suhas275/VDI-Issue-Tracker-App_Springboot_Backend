import { Component, OnInit } from '@angular/core';
import {
  Router,
  RouterLink,
  RouterLinkActive,
  RouterOutlet,
} from '@angular/router';
import { UserservieService } from '../services/user-service.service';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-navbar',
  standalone: true,
  imports: [CommonModule, RouterOutlet, RouterLinkActive, RouterLink],
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.css'],
})


export class NavbarComponent implements OnInit {
  isLoggedIn = false;
  constructor(
    private userservieService: UserservieService,
    private router: Router
  ) { }

  ngOnInit(): void {
    this.userservieService.isLoggedIn.subscribe((loggedIn: boolean) => {
      this.isLoggedIn = loggedIn;
    });
    // Check the login state on initialization if localStorage is available
    if (this.isLocalStorageAvailable()) {
      const token = localStorage.getItem('token');
      if (token) {
        this.isLoggedIn = true;
      }
    }
  }

  logout() {
    this.userservieService.logout();
  }
  private isLocalStorageAvailable(): boolean {
    try {
      const test = '__storage_test__';
      localStorage.setItem(test, test);
      localStorage.removeItem(test);
      return true;
    } catch (e) {
      return false;
    }
  }
}
