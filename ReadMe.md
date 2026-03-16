# Flashcard API

A simple REST API for a flashcard application where users can create accounts, log in with a session cookie, organize flashcards into decks, and manage cards inside those decks.

This API is meant for a frontend client such as:
- a web app
- a mobile app
- a desktop study app
- Postman or cURL for testing

With this API, a user can:
- register an account
- log in and receive an authentication cookie
- create decks to organize study topics
- add flashcards to a deck
- fetch their decks and cards
- delete decks or cards
- log out and destroy their session

---

## Base URL

`http://localhost:8080`

---

## Authentication

This API uses **cookie-based authentication**.

When a user logs in successfully, the backend returns an `auth` cookie.  
That cookie must be included in future requests to protected endpoints.

### Public Endpoints
These do not require login:
- `POST /user/register`
- `POST /user/login`

### Protected Endpoints
These require the `auth` cookie:
- `GET /user/logout`
- `POST /deck`
- `GET /deck`
- `DELETE /deck/delete/{id}`
- `POST /card/{id}`
- `GET /card/{id}/deck`
- `DELETE /card/delete/{id}`

### Auth Flow
1. User registers an account
2. User logs in
3. Server returns an `auth` cookie
4. Client stores and sends the cookie on future requests
5. Protected endpoints use that cookie to identify the user
6. User logs out to delete the session

> For browser-based frontend apps, use `credentials: "include"` so cookies are sent with requests.

---

## Main Resources

### User
Represents an account in the system.

A user can:
- create an account
- log in
- own multiple decks
- create cards under their account

### Deck
A deck is a collection of flashcards grouped by topic.

Examples:
- Java Interview Questions
- Biology Chapter 3
- Spring Boot Notes
- Chinese Vocabulary

Each deck belongs to one authenticated user.

### Card
A card is a single flashcard inside a deck.

Each card contains:
- a question
- an answer

Example:
- Question: `What is dependency injection?`
- Answer: `A pattern where dependencies are provided from outside the class.`

---

# Endpoints

## 1. Register User

**POST** `/user/register`

Creates a new user account.

### Purpose
Use this when a new user signs up for the app.

### Example Request Body

~~~json
{
  "name": "dina",
  "password": "mypassword123"
}
~~~

### Example Request (JavaScript)

~~~javascript
const response = await fetch("http://localhost:8080/user/register", {
  method: "POST",
  headers: {
    "Content-Type": "application/json"
  },
  body: JSON.stringify({
    name: "dina",
    password: "mypassword123"
  })
});

console.log(response.status); // 201
~~~

### Success Response
- `201 Created`

### Example Response
No response body.

---

## 2. Login User

**POST** `/user/login`

Authenticates a user and creates a session.

### Purpose
Use this when a user wants to access protected features such as creating decks or cards.

### Example Request Body

~~~json
{
  "name": "dina",
  "password": "mypassword123"
}
~~~

### Example Request (JavaScript)

~~~javascript
const response = await fetch("http://localhost:8080/user/login", {
  method: "POST",
  headers: {
    "Content-Type": "application/json"
  },
  credentials: "include",
  body: JSON.stringify({
    name: "dina",
    password: "mypassword123"
  })
});

const text = await response.text();
console.log(response.status); // 200
console.log(text); // "Login successful"
~~~

### Success Response
- `200 OK`

### Example Response Headers

~~~http
Set-Cookie: auth=<session-token>; HttpOnly; Path=/
~~~

### Example Response Body

~~~text
Login successful
~~~

### What Happens
- the backend checks the username and password
- if valid, it creates a session
- it sends back an `auth` cookie
- that cookie is used for future protected requests

---

## 3. Logout User

**GET** `/user/logout`

Deletes the current session.

### Purpose
Use this when the user wants to sign out.

### Example Request (JavaScript)

~~~javascript
const response = await fetch("http://localhost:8080/user/logout", {
  method: "GET",
  credentials: "include"
});

console.log(response.status); // 200
~~~

### Success Response
- `200 OK`

### Example Response
No response body.

---

## 4. Create Deck

**POST** `/deck`

Creates a new deck for the authenticated user.

### Purpose
Use this when a user wants to organize flashcards under a topic or subject.

### Example Request Body

~~~json
{
  "name": "Java Basics",
  "description": "Cards for Java interview prep"
}
~~~

### Example Request (JavaScript)

~~~javascript
const response = await fetch("http://localhost:8080/deck", {
  method: "POST",
  headers: {
    "Content-Type": "application/json"
  },
  credentials: "include",
  body: JSON.stringify({
    name: "Java Basics",
    description: "Cards for Java interview prep"
  })
});

const deckId = await response.json();
console.log(response.status); // 200
console.log(deckId);
~~~

### Success Response
- `200 OK`

### Example Response

~~~json
"550e8400-e29b-41d4-a716-446655440000"
~~~

---

## 5. Get All Decks

**GET** `/deck`

Returns all decks that belong to the authenticated user.

### Purpose
Use this to show a user's deck list on the frontend.

### Example Request (JavaScript)

~~~javascript
const response = await fetch("http://localhost:8080/deck", {
  method: "GET",
  credentials: "include"
});

const decks = await response.json();
console.log(response.status); // 200
console.log(decks);
~~~

### Success Response
- `200 OK`

### Example Response

~~~json
[
  {
    "deckId": "550e8400-e29b-41d4-a716-446655440000",
    "name": "Java Basics",
    "description": "Cards for Java interview prep",
    "userID": "aaaaaaaa-aaaa-aaaa-aaaa-aaaaaaaaaaaa"
  },
  {
    "deckId": "123e4567-e89b-12d3-a456-426614174000",
    "name": "Spring Boot",
    "description": "Spring notes",
    "userID": "aaaaaaaa-aaaa-aaaa-aaaa-aaaaaaaaaaaa"
  }
]
~~~

---

## 6. Delete Deck

**DELETE** `/deck/delete/{id}`

Deletes a deck by its ID.

### Purpose
Use this when the user wants to remove an entire deck.

### Path Parameter
- `id` = deck UUID

### Example Request (JavaScript)

~~~javascript
const deckId = "550e8400-e29b-41d4-a716-446655440000";

const response = await fetch(`http://localhost:8080/deck/delete/${deckId}`, {
  method: "DELETE",
  credentials: "include"
});

console.log(response.status); // 200
~~~

### Success Response
- `200 OK`

### Example Response
No response body.

---

## 7. Create Card

**POST** `/card/{id}`

Creates a new card inside a deck.

### Purpose
Use this when the user wants to add a flashcard to a specific deck.

### Path Parameter
- `id` = deck UUID

### Example Request Body

~~~json
{
  "question": "What is dependency injection?",
  "answer": "A design pattern where dependencies are provided from outside the class."
}
~~~

### Example Request (JavaScript)

~~~javascript
const deckId = "550e8400-e29b-41d4-a716-446655440000";

const response = await fetch(`http://localhost:8080/card/${deckId}`, {
  method: "POST",
  headers: {
    "Content-Type": "application/json"
  },
  credentials: "include",
  body: JSON.stringify({
    question: "What is dependency injection?",
    answer: "A design pattern where dependencies are provided from outside the class."
  })
});

const card = await response.json();
console.log(response.status); // 200
console.log(card);
~~~

### Success Response
- `200 OK`

### Example Response

~~~json
{
  "question": "What is dependency injection?",
  "answer": "A design pattern where dependencies are provided from outside the class."
}
~~~

---

## 8. Get Cards In Deck

**GET** `/card/{id}/deck`

Returns all cards that belong to a specific deck.

### Purpose
Use this when the frontend opens a deck and needs to display all its flashcards.

### Path Parameter
- `id` = deck UUID

### Example Request (JavaScript)

~~~javascript
const deckId = "550e8400-e29b-41d4-a716-446655440000";

const response = await fetch(`http://localhost:8080/card/${deckId}/deck`, {
  method: "GET",
  credentials: "include"
});

const cards = await response.json();
console.log(response.status); // 200
console.log(cards);
~~~

### Success Response
- `200 OK`

### Example Response

~~~json
[
  {
    "cardId": "11111111-1111-1111-1111-111111111111",
    "question": "What is IOC?",
    "answer": "Inversion of Control",
    "userId": "aaaaaaaa-aaaa-aaaa-aaaa-aaaaaaaaaaaa",
    "deckId": "550e8400-e29b-41d4-a716-446655440000"
  },
  {
    "cardId": "22222222-2222-2222-2222-222222222222",
    "question": "What is dependency injection?",
    "answer": "A pattern where dependencies are provided from outside the class.",
    "userId": "aaaaaaaa-aaaa-aaaa-aaaa-aaaaaaaaaaaa",
    "deckId": "550e8400-e29b-41d4-a716-446655440000"
  },
  {
    "cardId": "33333333-3333-3333-3333-333333333333",
    "question": "What is Spring Boot?",
    "answer": "A framework that simplifies building Spring applications.",
    "userId": "aaaaaaaa-aaaa-aaaa-aaaa-aaaaaaaaaaaa",
    "deckId": "550e8400-e29b-41d4-a716-446655440000"
  }
]
~~~

---

## 9. Delete Card

**DELETE** `/card/delete/{id}`

Deletes a card by its ID.

### Purpose
Use this when the user wants to remove a single flashcard.

### Path Parameter
- `id` = card UUID

### Example Request (JavaScript)

~~~javascript
const cardId = "11111111-1111-1111-1111-111111111111";

const response = await fetch(`http://localhost:8080/card/${cardId}`, {
  method: "DELETE",
  credentials: "include"
});

console.log(response.status); // 200
~~~

### Success Response
- `200 OK`

### Example Response
No response body.

---

# Typical Frontend Usage

A frontend app would usually use the API in this order:

## 1. Sign Up
Call:
- `POST /user/register`

Request example:

~~~javascript
await fetch("http://localhost:8080/user/register", {
  method: "POST",
  headers: {
    "Content-Type": "application/json"
  },
  body: JSON.stringify({
    name: "dina",
    password: "mypassword123"
  })
});
~~~

Response example:
- `201 Created`

## 2. Log In
Call:
- `POST /user/login`

Request example:

~~~javascript
await fetch("http://localhost:8080/user/login", {
  method: "POST",
  headers: {
    "Content-Type": "application/json"
  },
  credentials: "include",
  body: JSON.stringify({
    name: "dina",
    password: "mypassword123"
  })
});
~~~

Response example:

~~~text
Login successful
~~~

The browser or client stores the returned `auth` cookie.

## 3. Create a Deck
Call:
- `POST /deck`

Request example:

~~~javascript
await fetch("http://localhost:8080/deck", {
  method: "POST",
  headers: {
    "Content-Type": "application/json"
  },
  credentials: "include",
  body: JSON.stringify({
    name: "Spring Boot Interview",
    description: "Study deck for Spring Boot concepts"
  })
});
~~~

Response example:

~~~json
"550e8400-e29b-41d4-a716-446655440000"
~~~

## 4. Add Cards to the Deck
Call:
- `POST /card/{deckId}`

Request example:

~~~javascript
await fetch("http://localhost:8080/card/550e8400-e29b-41d4-a716-446655440000", {
  method: "POST",
  headers: {
    "Content-Type": "application/json"
  },
  credentials: "include",
  body: JSON.stringify({
    question: "What is JVM?",
    answer: "Java Virtual Machine"
  })
});
~~~

Response example:

~~~json
{
  "question": "What is JVM?",
  "answer": "Java Virtual Machine"
}
~~~

## 5. Show Data to the User
Call:
- `GET /deck` to show all decks
- `GET /card/{deckId}/deck` to show cards in a selected deck

Deck request example:

~~~javascript
await fetch("http://localhost:8080/deck", {
  method: "GET",
  credentials: "include"
});
~~~

Deck response example:

~~~json
[
  {
    "deckId": "550e8400-e29b-41d4-a716-446655440000",
    "name": "Spring Boot Interview",
    "description": "Study deck for Spring Boot concepts",
    "userID": "aaaaaaaa-aaaa-aaaa-aaaa-aaaaaaaaaaaa"
  }
]
~~~

Card request example:

~~~javascript
await fetch("http://localhost:8080/card/550e8400-e29b-41d4-a716-446655440000/deck", {
  method: "GET",
  credentials: "include"
});
~~~

Card response example:

~~~json
[
  {
    "cardId": "11111111-1111-1111-1111-111111111111",
    "question": "What is JVM?",
    "answer": "Java Virtual Machine",
    "userId": "aaaaaaaa-aaaa-aaaa-aaaa-aaaaaaaaaaaa",
    "deckId": "550e8400-e29b-41d4-a716-446655440000"
  }
]
~~~

## 6. Delete Data When Needed
Call:
- `DELETE /deck/delete/{id}`
- `DELETE /card/delete/{id}`

Request example:

~~~javascript
await fetch("http://localhost:8080/card/11111111-1111-1111-1111-111111111111", {
  method: "DELETE",
  credentials: "include"
});
~~~

Response example:
- `200 OK`
- no response body

## 7. Log Out
Call:
- `GET /user/logout`

Request example:

~~~javascript
await fetch("http://localhost:8080/user/logout", {
  method: "GET",
  credentials: "include"
});
~~~

Response example:
- `200 OK`
- no response body

---

# Example Use Case

A student using a study app might do the following:

1. Register an account
2. Log in
3. Create a deck called `Spring Boot Interview`
4. Add cards such as:
   - `What is IOC?`
   - `What is dependency injection?`
   - `Difference between @Component and @Service?`
5. Open the deck later to review all cards
6. Delete cards they no longer need
7. Log out

---

# Notes

- This API is session-based, not token-based
- Authentication depends on the `auth` cookie
- All deck and card operations require login
- Decks help group related cards together
- Cards store the actual study question and answer pairs

---

# Example Error Responses

These are common examples for frontend documentation.

## 400 Bad Request

~~~json
{
  "error": "Invalid request body"
}
~~~

## 401 Unauthorized

~~~json
{
  "error": "Unauthorized"
}
~~~

## 404 Not Found

~~~json
{
  "error": "Resource not found"
}
~~~

## 500 Internal Server Error

~~~json
{
  "error": "Something went wrong"
}
~~~

---

# Endpoint Summary

| Method | Endpoint | Example Request | Example Response | Auth Required |
|--------|----------|-----------------|------------------|---------------|
| POST | `/user/register` | `fetch("/user/register", { method: "POST", body: JSON.stringify({ name, password }) })` | No body | No |
| POST | `/user/login` | `fetch("/user/login", { method: "POST", credentials: "include", body: JSON.stringify({ name, password }) })` | `"Login successful"` | No |
| GET | `/user/logout` | `fetch("/user/logout", { method: "GET", credentials: "include" })` | No body | Yes |
| POST | `/deck` | `fetch("/deck", { method: "POST", credentials: "include", body: JSON.stringify({ name, description }) })` | `"550e8400-e29b-41d4-a716-446655440000"` | Yes |
| GET | `/deck` | `fetch("/deck", { method: "GET", credentials: "include" })` | Array of deck objects | Yes |
| DELETE | `/deck/delete/{id}` | `fetch("/deck/delete/{id}", { method: "DELETE", credentials: "include" })` | No body | Yes |
| POST | `/card/{id}` | `fetch("/card/{id}", { method: "POST", credentials: "include", body: JSON.stringify({ question, answer }) })` | Card summary object | Yes |
| GET | `/card/{id}/deck` | `fetch("/card/{id}/deck", { method: "GET", credentials: "include" })` | Array of card objects | Yes |
| DELETE | `/card/delete/{id}` | `fetch("/card/{id}", { method: "DELETE", credentials: "include" })` | No body | Yes |