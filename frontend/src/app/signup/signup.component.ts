import { CommonModule } from '@angular/common';
import { AfterViewInit, Component, ElementRef, ViewChild } from '@angular/core';
import { FormBuilder, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import * as bootstrap from 'bootstrap';
import { User } from '../model/user';
import { AuthService } from '../services/auth/auth.service';

@Component({
  selector: 'app-signup',
  imports: [CommonModule, ReactiveFormsModule],
  templateUrl: './signup.component.html',
  styleUrl: './signup.component.scss'
})
export class SignupComponent implements AfterViewInit {
  signupForm!: FormGroup;
  isSubmitting = false;

  @ViewChild('signupToast') toastElement!: ElementRef;
  toast!: bootstrap.Toast;

  @ViewChild('toastTitle') toastTitleElement!: ElementRef;
  @ViewChild('toastMessage') toastMessageElement!: ElementRef;
  @ViewChild('toastContainer') toastContainerElement!: ElementRef;

  constructor(
    private router: Router,
    private fb: FormBuilder,
    private authService: AuthService
  ) {
    this.signupForm = this.fb.group({
      name: ['', Validators.required],
      email: ['', [Validators.required, Validators.email]],
      password: ['', Validators.required]
    });
  }

  ngAfterViewInit() {
    this.toast = new bootstrap.Toast(this.toastElement.nativeElement, {
      delay: 5000,
      autohide: true
    });
  }

  get name() { return this.signupForm.get('name'); }
  get email() { return this.signupForm.get('email'); }
  get password() { return this.signupForm.get('password'); }

  async onSubmit() {
    this.isSubmitting = true;
    const { name, email, password } = this.signupForm.value;
    const user = new User(name, email);

    try {
      const response = await this.authService.signup(user, password)
      if (response) {
        this.isSubmitting = false;
        this.showToast('Success', 'Account created successfully! Redirecting...', 'success');
        setTimeout(() => {
          this.router.navigate(['/home']);
        }, 1000);
      }
    } catch (error) {
      this.isSubmitting = false;
      let errorMessage = 'Registration failed. Please try again.';

      if (error.response?.status === 409) {
        errorMessage = 'Email already registered. Please use a different email.';
      } else if (error.response?.status === 400) {
        errorMessage = error.response?.data || 'Invalid input data.';
      }

      this.showToast('Error', errorMessage, 'error');
    }
  }

  showToast(title: string, message: string, type: 'error' | 'success') {
    if (this.toastTitleElement && this.toastMessageElement && this.toastContainerElement) {
      this.toastTitleElement.nativeElement.textContent = title;
      this.toastMessageElement.nativeElement.textContent = message;

      this.toastContainerElement.nativeElement.classList.remove('bg-success', 'bg-danger');
      if (type === 'error') {
        this.toastContainerElement.nativeElement.classList.add('bg-danger', 'text-white');
      } else {
        this.toastContainerElement.nativeElement.classList.add('bg-success', 'text-white');
      }

      this.toast.show();
    }
  }
}
