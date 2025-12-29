import { Routes } from '@angular/router';

export const routes: Routes = [
  {
    path: '',
    redirectTo: '/login',
    pathMatch: 'full',
  },
  {
    path: 'login',
    loadComponent: () =>
      import('./login/login.component').then((module) => module.LoginComponent),
  },
  {
    path: 'signup',
    loadComponent: () =>
      import('./signup/signup.component').then(
        (module) => module.SignupComponent
      ),
  },
  {
    path: 'home',
    loadComponent: () =>
      import('./home/home.component').then((module) => module.HomeComponent),
  },
];
