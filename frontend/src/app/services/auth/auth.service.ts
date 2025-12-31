import { Injectable } from '@angular/core';
import { User } from '../../model/user';
import { environment } from '../../environment';
import axios, { AxiosInstance } from 'axios';

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
      // headers: { 'Authorization': 'Bearer jwt' }
    });
  }

  async login(user: User, password: string) {
    try {
      if (password && user) {
        // bcrypt.encode (password) => encoded password string == encodedPassword
        const encodedPassword = "";

        const result = await this.axiosInstance.post('api/auth/login', {
          name: user.name,
          email: user.email,
          password: encodedPassword
        });

        if (result.status === 200) {
          const jwt = result.data;
          // save jwt to localstorage
        } else {
          // gracefully handle the unauthorization like show username or password incorrect
        }
      }
    } catch (err) {
      console.log(err);
    }
  }
}
