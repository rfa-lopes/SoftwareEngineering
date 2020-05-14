\*# Software Engineering 2020 - Back-end - Authenticator

## API - Authenticator

---

---

### Login

REQUEST

- POST /auth/login

RESPONSE

- 200 - OK

```json
{
  "username": "rodrigo75",
  "password": "123456"
}
```

OR

```json
{
  "email": "rfa.lopes@campus.fct.unl.pt",
  "password": "123456"
}
```

- 401 - UNAUTHORIZED

---

### Logout

REQUEST

- POST /auth/logout

RESPONSE

- 200 - OK

---
