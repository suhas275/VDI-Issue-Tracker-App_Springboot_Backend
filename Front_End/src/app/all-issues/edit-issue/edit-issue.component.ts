import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router, RouterLink, RouterLinkActive, RouterOutlet } from '@angular/router';
import { UserservieService } from '../../services/user-service.service';
import swal from 'sweetalert';
import { User } from '../../services/user';
import { CommonModule } from '@angular/common';
import { FormBuilder, FormGroup, FormsModule } from '@angular/forms';
@Component({
  selector: 'app-edit-issue',
  standalone: true,
  imports: [CommonModule,FormsModule,RouterOutlet, RouterLinkActive, RouterLink],
  templateUrl: './edit-issue.component.html',
  styleUrl: './edit-issue.component.css'
})


export class EditIssueComponent implements OnInit {
  editForm: FormGroup | undefined;
  
  submitted = false;
  User: any = {
    id:'',
    associateId:'',
    issueType: '',
    // datetime: ''
   
  }
  constructor(private formBuilder: FormBuilder,private route: ActivatedRoute,private router: Router,private userservieService: UserservieService
  ) {}

  ngOnInit(): void {
    this.route.paramMap.subscribe(params=>{
       const id = params.get('id');    
    console.log(id);
    
    if (id) {
      this.userservieService.getIssueById(+id).subscribe((data: User) => {
        this.User = data;
      }
    );
    }
    })
    
  }

  onSubmit(form: any) {
    if (form.valid) {
      this.userservieService.updateIssue(this.User).subscribe(() => {
        swal('Success', 'Issue updated successfully', 'success');
        this.router.navigate(['/allissues']);
      }, () => {
        swal('Error', 'Failed to update issue', 'error');
      });
    }
  }
}

