# Software Engineering 2020 - Back-end - Messages

## API - Publications

---

---

### Send message

REQUEST

- POST /messages/sendmessage

- BODY

```json
{
  "fromUserId": 2,
  "toUserId": 1,
  "messageText": "Olá amizade!"
}
```

RESPONSE

- 200 - OK (ID)

```json
5
```

- 404 - NOT FOUND

---

### Delete message

REQUEST

- DELETE /messages/deletemessage/{id}

RESPONSE

- 200 - OK (ID)
- 404 - NOT FOUND

---

### Get all message

REQUEST

- GET /messages//allmessages/{fromIid}/{toId}

RESPONSE

- 200 - OK (ID)

```json
[
  {
    "id": 4,
    "fromUserId": 1,
    "toUserId": 2,
    "messageText": "Olá!",
    "sendedDate": "2020-05-13 16:49:50",
    "receivedDate": "2020-05-13 16:51:55"
  },
  {
    "id": 5,
    "fromUserId": 2,
    "toUserId": 1,
    "messageText": "Olá amizade",
    "sendedDate": "2020-05-13 16:55:06",
    "receivedDate": null
  }
]
```

- 404 - NOT FOUND

---
