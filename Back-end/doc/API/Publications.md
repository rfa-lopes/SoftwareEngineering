# Software Engineering 2020 - Back-end - Publications

## API - Publications
### Post Publication

REQUEST

- POST /publications/post

- BODY
```json
{
  "ownerId": 1,
  "description": "o meu arroz",
}
```

RESPONSE
- 200 - OK (ID)
```json
3
```
- 401 - UNAUTHORIZED
---

### Get All Publications from a Profile

REQUEST

- POST /publications/allpublications/{id}


RESPONSE
- 200 - OK (ID)
```json
  "id": 2,
        "ownerId": 1,
        "publicationDate": "2020-05-21 12:43:59",
        "expireDate": null,
        "description": "comer arroz"
```
- 401 - UNAUTHORIZED
- 404 - NOT FOUND
---

### Edit Publication

REQUEST

- POST /publications/editpublication/{id}

- BODY
```json
{
  "description":"nova descrição"
}
```

RESPONSE
- 200 - OK (ID)
```json
"id": 2,
        "ownerId": 1,
        "publicationDate": "2020-05-21 12:43:59",
        "expireDate": null,
        "description": "nova descrição"
```
- 401 - UNAUTHORIZED
- 404 - NOT FOUND
---

### Add Coment

REQUEST

- POST /publications/postcomment/{id}

- BODY
```json
{
 {
	"userId":1,
	"publicationId":2,
	"comment":"comment"
	
}
}
```

RESPONSE
- 200 - OK (ID)
```json
4
```
- 401 - UNAUTHORIZED
- 404 - NOT FOUND
---

### Add Like

REQUEST

- POST /publications/postlike

- BODY
```json
 {
	"userId":1,
	"publicationId":2,
  	"reactionDate":"hoje",
	"type":1
	
	
}
```

RESPONSE
- 200 - OK (ID)
```json
3
```
- 401 - UNAUTHORIZED
- 404 - NOT FOUND
---

### Get All Comments

REQUEST

- GET /publications/allComments/{id}


RESPONSE
- 200 - OK (ID)
 ```json
   {
        "id": 5,
        "userId": 1,
        "publicationId": 2,
        "comment": "comment"
    }
 ```
- 401 - UNAUTHORIZED
- 404 - NOT FOUND
---
### Get All Likes

REQUEST

- GET /publications/allLikes/{id}


RESPONSE
- 200 - OK (ID)
 ```json
   {
        "id": 5,
        "userId": 1,
        "publicationId": 2,
        "comment": "comment"
    }
 ```
- 401 - UNAUTHORIZED
- 404 - NOT FOUND
---

### Delete Comment

REQUEST

- DELETE /publications/deletecomments/{id}


RESPONSE
- 200 - OK (ID)

- 401 - UNAUTHORIZED
- 404 - NOT FOUND
---

### Delete Publication

REQUEST

- DELETE /publications/detetepublication/{id}


RESPONSE
- 200 - OK (ID)

- 401 - UNAUTHORIZED
- 404 - NOT FOUND
---

### Delete Like

REQUEST

- DELETE /publications/deletelike/{idLike}


RESPONSE
- 200 - OK (ID)

- 401 - UNAUTHORIZED
- 404 - NOT FOUND
---
