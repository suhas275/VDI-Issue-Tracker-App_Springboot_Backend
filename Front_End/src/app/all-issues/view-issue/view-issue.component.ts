import { Component, OnInit } from '@angular/core';
import { Router, RouterLink, RouterLinkActive, RouterOutlet } from '@angular/router';
import { ActivatedRoute } from '@angular/router';
import { User } from '../../services/user';
import { UserservieService } from '../../services/user-service.service';
import { CommonModule } from '@angular/common';
import swal from 'sweetalert';
import { FormBuilder, FormGroup, FormsModule } from '@angular/forms';

@Component({
  selector: 'app-view-issue',
  standalone: true,
  imports: [CommonModule,RouterOutlet, RouterLinkActive, RouterLink,FormsModule],
  templateUrl: './view-issue.component.html',
  styleUrl: './view-issue.component.css'
})
export class ViewIssueComponent implements OnInit{
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
      this.userservieService.viewIssueById(+id).subscribe((data: User) => {
        this.User = data;
      }
    );
    }  
    }
  )
    
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
  
  // id: number | undefined;
  // user: User | undefined;

  // constructor(private route: ActivatedRoute, private userserviceService: UserservieService) { }

  // ngOnInit(): void {
  //   this.route.paramMap.subscribe(params => {
  //     const id = params.get('id');

  //     if (id) {
  //       this.userserviceService.getIssueById(+id).subscribe((data: User) => {
  //         this.user = data;
  //       });
  //     }
  //   });
  // }
}