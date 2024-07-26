import { CanActivateFn, Router } from '@angular/router';

import { inject } from '@angular/core';

// export const authGuard: CanActivateFn = (route, state) => {
//   const router = inject(Router);

//   const localdata = localStorage.getItem('token');

//   if (localdata != null) {
//     return true;
//   } else {
//     router.navigate(['/login']);

//     return false;
//   }
// };

export const authGuard: CanActivateFn = (route, state) => {
  const router = inject(Router);

  if (typeof window !== 'undefined' && window.localStorage) {
    const localdata = localStorage.getItem('token');    
    if (localdata != null) {
      return true;
    } else {
      router.navigate(['/login']);
      return false;
    }
  } else {
    return true; 
  }
};
