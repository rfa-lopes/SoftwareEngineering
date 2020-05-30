# Software Engineering 2020 - Back-end - Stories

## API - Stories
### Post Stories

REQUEST

- POST /stories/poststory

- BODY
```json
{
  "ownerId": 1,
  "image":"fjgjfjejjmdmdjd"
}
```

RESPONSE
- 200 - OK 
```json
3
```
- 401 - UNAUTHORIZED
---
### Get All Stories from a Profile

REQUEST

- GET /stories/allstories/{id}


RESPONSE
- 200 - OK 
```json
[
    {
        "id": 2,
        "ownerId": 1,
        "publicationDate": "2020-05-30 16:54:15",
        "expireDate": "2020-05-31 16:54:15",
        "description": "o meu arroz",
        "image": "durtjndnsbwhwjms"
    },
    {
        "id": 5,
        "ownerId": 1,
        "publicationDate": "2020-05-30 16:54:40",
        "expireDate": "2020-05-31 16:54:40",
        "description": "o meu arroz",
        "image": "durtjndnsbwhwjms"
    }
]
```
- 401 - UNAUTHORIZED
- 404 - NOT FOUND
---

### Delete Story

REQUEST

- DELETE /stories/deletestory/{id}


RESPONSE
- 200 - OK 

- 401 - UNAUTHORIZED
- 404 - NOT FOUND
---

### Get Story

REQUEST

- GET /stories/get/{id}


RESPONSE
- 200 - OK 
```json
	{
		"id": 2,
		"ownerId": 1,
		"publicationDate": "2020-05-21 12:43:59",
		"expireDate": null,
		"description": "comer arroz",
		"image":"fjgjfjejjmdmdjd"
	}
```
- 401 - UNAUTHORIZED
- 404 - NOT FOUND
---
### Get Story Feed

REQUEST

- GET /accounts/getstoryfeed/{id}


RESPONSE
- 200 - OK 
```json
{
    "1": [
        {
            "id": 2,
            "ownerId": 1,
            "publicationDate": "2020-05-30 16:54:15",
            "expireDate": "2020-05-31 16:54:15",
            "description": "o meu arroz",
            "image": "durtjndnsbwhwjms"
        },
        {
            "id": 5,
            "ownerId": 1,
            "publicationDate": "2020-05-30 16:54:40",
            "expireDate": "2020-05-31 16:54:40",
            "description": "o meu arroz",
            "image": "durtjndnsbwhwjms"
        }
    ],
    "6": [
        {
            "id": 8,
            "ownerId": 6,
            "publicationDate": "2020-05-30 16:55:42",
            "expireDate": "2020-05-31 16:55:42",
            "description": "o meu arroz",
            "image": "durtjndnsbwhwjms"
        },
        {
            "id": 9,
            "ownerId": 6,
            "publicationDate": "2020-05-30 16:55:43",
            "expireDate": "2020-05-31 16:55:43",
            "description": "o meu arroz",
            "image": "durtjndnsbwhwjms"
        }
    ]
}
```
- 401 - UNAUTHORIZED
- 404 - NOT FOUND
---

