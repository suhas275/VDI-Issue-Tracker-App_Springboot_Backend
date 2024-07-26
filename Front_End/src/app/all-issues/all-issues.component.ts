import { CommonModule } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { UserservieService } from '../services/user-service.service';
import {
  Router,
  RouterLink,
  RouterLinkActive,
  RouterOutlet,
} from '@angular/router';
import { User } from '../services/user';
import { FormsModule } from '@angular/forms';
import swal from 'sweetalert';

@Component({
  selector: 'app-all-issues',
  standalone: true,
  imports: [
    CommonModule,
    RouterOutlet,
    RouterLinkActive,
    RouterLink,
    FormsModule,
  ],
  templateUrl: './all-issues.component.html',
  styleUrl: './all-issues.component.css',
})


export class AllIssueComponent implements OnInit {
  users: User[] = [];
  searchText = '';
  originalUsers: User[] = []; // Store original data for filtering

  constructor(private userservieService: UserservieService,private router:Router) {}

  ngOnInit(): void {
    this.getAllIssues();
  }

  private getAllIssues() {
    this.userservieService.getAllIssues().subscribe((data) => {
      this.users = data;      
      this.originalUsers = data; // Keep a copy of the original data for resetting

    });
  }
  filterIssues() {
    if (!this.searchText) {
      this.users = this.originalUsers; // Reset to original list if search text is cleared
    } else {
      const searchTextLower = this.searchText.toLowerCase();
      this.users = this.originalUsers.filter(user =>
        user.associateId?.toString().toLowerCase().includes(searchTextLower) ||
        user.issueType?.toLowerCase().includes(searchTextLower) ||
        user.issueSummary?.toLowerCase().includes(searchTextLower) ||
        user.status?.toLowerCase().includes(searchTextLower)
      );
    }
  }
  

  editIssue(user: User) {
    this.router.navigate(['/editissue', user.id]);  
  }

  viewIssue(user: User) {
    this.router.navigate(['/viewissue', user.id]);  
  }

  deleteIssue(user: User) {
    if (confirm(`Are you sure you want to delete the issue?`)) {
      this.userservieService.deleteIssue(user.id).subscribe(() => {
        this.users = this.users.filter(u => u.id !== user.id);
        swal('Success', 'Issue deleted successfully', 'success');
      }, () => {
        swal('Error', 'Failed to delete issue', 'error');
      });
    }
  }
 
 
}
