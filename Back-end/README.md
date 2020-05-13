# Software Engineering 2020 - Back-end

## API

---

### Accounts

#### Crete account
* POST /accounts/create

* BODY
```json
{
	"username":"rodrigo75",
	"password":"123456",
	"name":"Rodrigo Faria Lopes",
	"email":"rfa.lopes@campus.fct.unl.pt"
}
```

* Curl command
```cmd
curl XPOST 'localhost:8080/accounts/create' \
--header 'Content-Type: application/json' \
--data-raw '{"username":"rodrigo75","password":"123456","name":"Rodrigo Faria Lopes","email":"rfa.lopes@campus.fct.unl.pt"}'
```

---

RESPONSE
* 200 - OK (ID)
```json
1
```

* 404 - NOT FOUND

---
---

#### Get account
* get /accounts/create

* Curl command
```cmd
curl XPOST 'localhost:8080/accounts/create' \
--header 'Content-Type: application/json' \
--data-raw '{"username":"rodrigo75","password":"123456","name":"Rodrigo Faria Lopes","email":"rfa.lopes@campus.fct.unl.pt"}'
```

---

RESPONSE
* 200 - OK (ID)
```json
1
```

* 404 - NOT FOUND

#### Delete account
TODO

#### Get account viweres
TODO
