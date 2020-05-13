# Software Engineering 2020 - Back-end - Accounts

## API - Accounts

---

---

### Crete account

REQUEST

- POST /accounts/create

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

- 404 - NOT FOUND

---

### Get account

REQUEST

- GET /accounts/create

RESPONSE

- 200 - OK

```json
{
  "id": 1,
  "username": "rodrigo75",
  "password": "5rpovgEtexhufY36Zb1PHvVBrqeeB6PEyq9XrFlhIEY=",
  "name": "Rodrigo Faria Lopes",
  "email": "rfa.lopes@campus.fct.unl.pt",
  "public": false
}
```

- 404 - NOT FOUND

---

### Delete account

REQUEST

- DELETE /accounts/delete/{id}

RESPONSE

- 200 - OK
- 404 - NOT FOUND

---

### Get account viweres

REQUEST

- GET /accounts/viweres/{id}

RESPONSE

- 200 - OK

```json
[
  {
    "id": 1,
    "viewerId": 2,
    "profileId": 1,
    "viewDate": "2020-02-27 17:23:34"
  },
  {
    "id": 2,
    "viewerId": 3,
    "profileId": 1,
    "viewDate": "2020-07-12 04:55:13"
  }
]
```

- 404 - NOT FOUND

---

### Follow account

REQUEST

- POST /accounts/follow

- BODY

```json
{
  "accountId": 1,
  "isFollowingId": 2
}
```

RESPONSE

- 200 - OK
- 404 - NOT FOUND

---

### Change account visibility

REQUEST

- GET /accounts/change/{id}

RESPONSE

- 200 - OK
- 404 - NOT FOUND

---
