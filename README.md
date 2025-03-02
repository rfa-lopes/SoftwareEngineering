# Software Engineering 2020 - Instagram Plus Project

---

## Tecnologias

### Back-end - [Documentação](Back-end/README.md)

- [Spring-boot 2.2.7](https://start.spring.io/)
- [Maven](https://maven.apache.org/)
- [Java 8](https://www.java.com/)
- [H2 Data Base Engine](https://www.h2database.com/html/main.html)
- [JSON Web Tokens(JWT)](https://jwt.io/) (API c/ autenticação)

### Front-end - [Documentação](Front-end/README.md)

- [Bootstrap 4.5](https://getbootstrap.com/)
- [Javascript](https://www.javascript.com/)
- [CSS]()
- [HTML]()

---

## Começar

### Instalação de dependências

#### Instalar Maven [aqui](https://www.baeldung.com/install-maven-on-windows-linux-mac)

### Correr back-end localmente

#### Correr com [Maven](https://maven.apache.org/)

```cmd
cd Back-end
mvn spring-boot:run
```
#### Inicializar com dados random (opcional)

X - Número de contas a serem criadas automáticamente (máx: 20)

Y - Número de publicações por cada conta criada (máx: 10)

**NOTA:** Stories, Mensagens, Reações, Comentários, Viewers e Seguidores serão também criados automáticamente.

```cmd
curl --location --request GET 'http://localhost:8080/tests/test1/X/Y'
```

### Correr front-end localmente

#### Correr com [Python](https://docs.python.org/3/library/http.server.html) (opcional)

```cmd
python -m http.server 8000
```

---

## Base de dados

**URL:** http://localhost:8080/h2

![Data base init](Back-end/doc/DataBase/DataBaseInit.png)

### Diagrama de tabelas

![Data base init](Back-end/doc/DataBase/DataBaseDiagram.png)

---

## Utils

### Comandos Git

```bash
https://github.com/rfa-lopes/SS-TP1.git
git pull origin master
git add .
git commit -m "Initial commit"
git push
git rm -r --cached Path/to/directories
```

## Informação adicional

### Autores

- Rodrigo Lopes - rfa.lopes@campus.fct.unl.pt - 50435
- Miguel Fernandes - mia.fernandes@campus.fct.unl.pt - 50487
- Bárbara Lopes - bi.lopes@campus.fct.unl.pt - 51104
- Fábio Cunha - ff.cunha@campus.fct.unl.pt - 50613

### Professor

- João Araújo

### Turnos práticos

- P1
- P4
