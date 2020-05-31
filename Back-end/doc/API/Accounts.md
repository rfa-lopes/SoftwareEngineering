# Software Engineering 2020 - Back-end - Accounts

## API - Accounts

---

---

### Get account

REQUEST

- GET /accounts/get/{id}

RESPONSE

- 200 - OK

```json
{
  "id": 1,
  "username": "rodrigo75",
  "password": "null",
  "name": "Rodrigo Faria Lopes",
  "email": "rfa.lopes@campus.fct.unl.pt",
  "public": false,
  "profileImage":"/9j/4AAQSkZJRgABAQAAAQABAAD/2wCEAAcHBwcHBwgI...Wh3yH0jH/9k="
}
```
- 401 - UNAUTHORIZED
- 404 - NOT FOUND

---

### Get account (Não conta como viewer)

REQUEST

- GET /accounts/getviewer/{id}

RESPONSE

- 200 - OK

```json
{
  "id": 1,
  "username": "rodrigo75",
  "password": "null",
  "name": "Rodrigo Faria Lopes",
  "email": "rfa.lopes@campus.fct.unl.pt",
  "public": false,
  "profileImage":"/9j/4AAQSkZJRgABAQAAAQABAAD/2wCEAAcHBwcHBwgI...Wh3yH0jH/9k="
}
```
- 401 - UNAUTHORIZED
- 404 - NOT FOUND

---

### Delete account

REQUEST

- DELETE /accounts/delete/{id}

RESPONSE

- 200 - OK
- 401 - UNAUTHORIZED
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
- 401 - UNAUTHORIZED
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
- 401 - UNAUTHORIZED
- 404 - NOT FOUND

---

### Unfollow account

REQUEST

- POST /accounts/unfollow

- BODY

```json
{
  "accountId": 1,
  "isFollowingId": 2
}
```

RESPONSE

- 200 - OK
- 401 - UNAUTHORIZED
- 404 - NOT FOUND

---

### Get followers

REQUEST

- GET /accounts/followers/{id}

RESPONSE

- 200 - OK

```json
[
  {
    "id": 2,
    "accountId": 1,
    "isFollowingId": 2,
    "followDate": "2020-05-13 16:22:17"
  }
]
```
- 401 - UNAUTHORIZED
- 404 - NOT FOUND

---

### Get followings

REQUEST

- GET /accounts/followings/{id}

RESPONSE

- 200 - OK

```json
[
  {
    "id": 2,
    "accountId": 2,
    "isFollowingId": 1,
    "followDate": "2020-05-13 16:22:17"
  }
]
```
- 401 - UNAUTHORIZED
- 404 - NOT FOUND

---

### Change account visibility

REQUEST

- GET /accounts/change/{id}

RESPONSE

- 200 - OK
- 401 - UNAUTHORIZED
- 404 - NOT FOUND

---
### Publications Feed

REQUEST

- GET /accounts/getfeed/{id}

RESPONSE

- 200 - OK
```json
[
    {
        "id": 29,
        "ownerId": 2,
        "publicationDate": "2020-05-31 07:42:43",
        "expireDate": null,
        "description": "Publication: 1; ownerId: 2",
        "image": "/9j/4AAQSkZJRgABAQ...4H/8Awf8A/9k="
    },
    {
        "id": 30,
        "ownerId": 2,
        "publicationDate": "2020-05-31 07:42:43",
        "expireDate": null,
        "description": "Publication: 2; ownerId: 2",
    },
    {
        "id": 31,
        "ownerId": 2,
        "publicationDate": "2020-05-31 07:42:43",
        "expireDate": null,
        "description": "Publication: 3; ownerId: 2",
        "image": "/9j/4AAQS691737r3...Xvfue9+691//9k="
    }
]
```
- 401 - UNAUTHORIZED
- 404 - NOT FOUND

---
