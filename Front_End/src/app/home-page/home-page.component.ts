import { Component } from '@angular/core';
import { Router, RouterLink, RouterLinkActive, RouterModule, RouterOutlet } from '@angular/router';
import { routes } from '../app.routes';

@Component({
  selector: 'app-home-page',
  standalone: true,
  imports: [RouterOutlet,RouterLink,RouterLinkActive],
  templateUrl: './home-page.component.html',
  styleUrl: './home-page.component.css'
})

export class HomePageComponent {
  
}
