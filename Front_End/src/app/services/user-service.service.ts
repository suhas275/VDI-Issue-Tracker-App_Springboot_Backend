
import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { User } from './user';
import { BehaviorSubject, Observable } from 'rxjs';
import { Router } from '@angular/router';
import swal from 'sweetalert';
 
@Injectable({
  providedIn: 'root'
})
export class UserservieService {
 
  
  constructor(private httpClient: HttpClient, private router: Router,private http: HttpClient) { }
  // profile page 
  // private currentUserSubject = new BehaviorSubject<User | null>(null);
  // setCurrentUser(user: User) {
  //   this.currentUserSubject.next(user);
  // }

  // getCurrentUser(): Observable<User | null> {
  //   return this.currentUserSubject.asObservable();
  // }



  

  // default associateId in raiseissue page
  private registeredAssociateId: number | null = null;
  setRegisteredAssociateId(associateId: number) {
    this.registeredAssociateId = associateId;
  }
  getRegisteredAssociateId(): number | null {
    return this.registeredAssociateId;
  }

  private loggedIn = new BehaviorSubject<boolean>(false);
  registerUserser: any;
 
  get isLoggedIn() {
    return this.loggedIn.asObservable();
  }
  
  login(data: any) {
    this.httpClient.post("http://localhost:8585/auth/login", data).subscribe((result: any) => {
      if (result.success) {
        // debugger
        swal('Success', 'sucessfull', 'success');
        localStorage.setItem("token", result.token);
        localStorage.setItem("user", JSON.stringify(result.user));
        this.loggedIn.next(true);  // Set the loggedIn state to true
        swal('Success', 'sucessfull', 'success');
 
        this.router.navigate(['/homepage']);
      } else {
        // alert("Invalid username or password");
        swal('Error', 'Invalid username or password', 'error');
 
      }
    });
  }
  registerUser(user: User): Observable<any> {
    return this.httpClient.post<any>('http://localhost:8585/auth/addUser', user);
  }
 
 
  logout() {
    localStorage.removeItem("token");
    localStorage.removeItem("user");
    this.loggedIn.next(false); 
    this.router.navigate(['/login']);

    
  } 
  //Raise Issue SERVICE
  raiseIssue(user: User): Observable<any> {
    return this.httpClient.post<any>('http://localhost:8585/add', user);
  }
  // All Issue
  getAllIssues(): Observable<User[]> {
    return this.http.get<User[]>('http://localhost:8585/all'); 
  }  
  
  //  Delete an Issue 
  deleteIssue(id: number): Observable<void> {
    return this.httpClient.delete<void>(`http://localhost:8585/delete/${id}`);
  }
  // Get an Issue by ID
  getIssueById(id: number): Observable<User> {
    return this.httpClient.get<User>(`http://localhost:8585/issues/${id}`);
  }

  // View Issue by ID
  viewIssueById(id: number): Observable<User> {
    return this.httpClient.get<User>(`http://localhost:8585/viewissues/${id}`);
  }
 // Update an Issue
 updateIssue(user: User): Observable<User> {
  return this.httpClient.put<User>(`http://localhost:8585/update/${user.id}`, user);
}

// GET AllUsers 
getAllUsers(): Observable<User[]> {
  return this.http.get<User[]>('http://localhost:8585/allusers'); 
} 
}
 
