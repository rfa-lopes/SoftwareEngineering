# Software Engineering 2020 - Back-end - Register

## API - Register

---

---

### Crete account

REQUEST

- POST /register/account

- BODY

```json
{
  "username": "rodrigo75",
  "password": "123456",
  "name": "Rodrigo Faria Lopes",
  "email": "rfa.lopes@campus.fct.unl.pt"
}
```

RESPONSE

- 200 - OK (ID)

```json
1
```

- 404 - CONFLICT

---
