import { Injectable } from '@angular/core';
import { User } from '../../model/user';
import { environment } from '../../environment';
import axios, { AxiosInstance } from 'axios';
import { LocalStorageKeys } from '../../model/local-storage';

@Injectable({
  providedIn: 'root'
})
export class AuthService {
  private readonly baseUrl = environment.serverUrl;
  private axiosInstance: AxiosInstance;

  constructor() {
    this.axiosInstance = axios.create({
      baseURL: this.baseUrl,
      timeout: 10000,
    });
  }

  async login(user: User, password: string): Promise<string> {
    if (password && user) {
      const result = await this.axiosInstance.post('api/auth/login', {
        name: user.name,
        email: user.email,
        password
      });

      if (result.status === 200) {
        const jwt = result.data;
        localStorage.setItem(LocalStorageKeys.JWT, jwt);
        return jwt;
      }
    }
    return "";
  }

  async signup(user: User, password: string): Promise<string> {
    if (password && user) {
      const result = await this.axiosInstance.post('api/auth/signup', {
        name: user.name,
        email: user.email,
        password
      });

      if (result.status === 200) {
        if (result.data) {
          const jwt = await this.login(user, password);
          if (jwt) {
            localStorage.setItem(LocalStorageKeys.JWT, jwt);
            return jwt;
          }
        }
      }
    }
    return "";
  }
}
