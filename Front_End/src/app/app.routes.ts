import { Routes } from '@angular/router';
import { HomePageComponent } from './home-page/home-page.component';
import { RaiseIssueComponent } from './raise-issue/raise-issue.component';
import { AllUsersComponent } from './all-users/all-users.component';
import { LoginComponent } from './login/login.component';
import { RegisterComponent } from './register/register.component';
import { ViewIssueComponent } from './all-issues/view-issue/view-issue.component';
import { ProfileComponent } from './profile/profile.component';
import { authGuard } from './shared/authguard.guard';
import { EditIssueComponent } from './all-issues/edit-issue/edit-issue.component';
import { AllIssueComponent } from './all-issues/all-issues.component';

export const routes: Routes = [
    { path: '', pathMatch: 'full', redirectTo: 'login' },
    {path: 'login', component: LoginComponent, },
    {path:'register', component:RegisterComponent, },
    {path:'homepage', component:HomePageComponent,canActivate:[authGuard]},
    {path:'raiseissue', component:RaiseIssueComponent,canActivate:[authGuard]},
    {path:'allusers', component:AllUsersComponent, canActivate:[authGuard]},
    {path:'allissues', component:AllIssueComponent,canActivate:[authGuard] },
    {path:'viewissue', component:ViewIssueComponent, canActivate:[authGuard]},
    {path:'profile', component:ProfileComponent, canActivate:[authGuard]},
    {path:'editissue/:id', component:EditIssueComponent, canActivate:[authGuard]},
      

];

