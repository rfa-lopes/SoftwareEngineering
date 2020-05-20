# Software Engineering 2020 - Back-end - Authenticator

## API - Authenticator

---

---

### Login

REQUEST

- POST /auth/login

- BODY
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

RESPONSE

- 200 - OK

```json
{
    "id": 1,
    "username": "rodrigo75",
    "password": null,
    "name": "Rodrigo Faria Lopes",
    "email": "rfa.lopes@campus.fct.unl.pt",
    "public": false
}
```
COOKIE (JWT) - 
```json
AuthToken=eyJ0eXBlIjoiSldUIiwiYWxnIjoiSFM1MTIifQ.eyJqdGkiOiIxIiwic3ViIjoiQXV0aGVudGljYXRpb24iLCJleHAiOjE1ODk5NjcyMTgsImlhdCI6MTU4OTk2NjYxOH0.3h012aZ_s8rN5DzVBhcf_984TDW1dtG4nWE1PZqbNknBUDbhFrWLxqP4tVsBzZI_KG1eYxII22BPBB-WT2NR9g
```

- 401 - UNAUTHORIZED

---

### Logout

REQUEST

- POST /auth/logout

RESPONSE

- 200 - OK
- 401 - UNAUTHORIZED
---
