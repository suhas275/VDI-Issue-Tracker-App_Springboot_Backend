import { Component } from '@angular/core';
import { UserservieService } from '../services/user-service.service';
import { User } from '../services/user';



@Component({
  selector: 'app-all-users',
  standalone: true,
  imports: [],
  templateUrl: './all-users.component.html',
  styleUrl: './all-users.component.css'
})
export class AllUsersComponent {
  users: User[] = [];

  constructor(private userserviceService: UserservieService) {}

  ngOnInit(): void {
    this.userserviceService.getAllUsers().subscribe(
      (data: User[]) => {
        this.users = data;
      },
      (error: any) => {
        console.error('Error fetching users:', error);
      }
    );
  }
}
