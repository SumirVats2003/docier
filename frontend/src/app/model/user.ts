export class User {
  constructor(
    public name: string,
    public email: string,
  ) { }

  static fromJSON(jsonObject: any) {
    let user = null;

    if (jsonObject) {
      user = new User('', '');
      user.name = jsonObject.name ?? '';
      user.email = jsonObject.email ?? '';
    }

    return user;
  }
}
