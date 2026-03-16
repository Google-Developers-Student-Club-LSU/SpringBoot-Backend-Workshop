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
- `DELETE /card/{id}`

### Auth Flow
1. User registers an account
2. User logs in
3. Server returns an `auth` cookie
4. Client stores and sends the cookie on future requests
5. Protected endpoints use that cookie to identify the user
6. User logs out to delete the session

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

### Request Body

~~~json
{
  "name": "dina",
  "password": "mypassword123"
}
~~~

### Success Response
- `201 Created`

### Example

~~~bash
curl -X POST http://localhost:8080/user/register \
  -H "Content-Type: application/json" \
  -d '{
    "name": "dina",
    "password": "mypassword123"
  }'
~~~

---

## 2. Login User

**POST** `/user/login`

Authenticates a user and creates a session.

### Purpose
Use this when a user wants to access protected features such as creating decks or cards.

### Request Body

~~~json
{
  "name": "dina",
  "password": "mypassword123"
}
~~~

### Success Response
- `200 OK`

### What Happens
- the backend checks the username and password
- if valid, it creates a session
- it sends back an `auth` cookie
- that cookie is used for future protected requests

### Example

~~~bash
curl -i -X POST http://localhost:8080/user/login \
  -H "Content-Type: application/json" \
  -d '{
    "name": "dina",
    "password": "mypassword123"
  }'
~~~

---

## 3. Logout User

**GET** `/user/logout`

Deletes the current session.

### Purpose
Use this when the user wants to sign out.

### Success Response
- `200 OK`

### Example

~~~bash
curl -X GET http://localhost:8080/user/logout \
  --cookie "auth=<session-token>"
~~~

---

## 4. Create Deck

**POST** `/deck`

Creates a new deck for the authenticated user.

### Purpose
Use this when a user wants to organize flashcards under a topic or subject.

### Request Body

~~~json
{
  "name": "Java Basics",
  "description": "Cards for Java interview prep"
}
~~~

### Success Response
- `200 OK`

Returns the created deck ID.

### Example

~~~bash
curl -X POST http://localhost:8080/deck \
  -H "Content-Type: application/json" \
  --cookie "auth=<session-token>" \
  -d '{
    "name": "Java Basics",
    "description": "Cards for Java interview prep"
  }'
~~~

---

## 5. Get All Decks

**GET** `/deck`

Returns all decks that belong to the authenticated user.

### Purpose
Use this to show a user's deck list on the frontend.

### Success Response
- `200 OK`

### Example

~~~bash
curl -X GET http://localhost:8080/deck \
  --cookie "auth=<session-token>"
~~~

---

## 6. Delete Deck

**DELETE** `/deck/delete/{id}`

Deletes a deck by its ID.

### Purpose
Use this when the user wants to remove an entire deck.

### Path Parameter
- `id` = deck UUID

### Success Response
- `200 OK`

### Example

~~~bash
curl -X DELETE http://localhost:8080/deck/delete/<deck-id> \
  --cookie "auth=<session-token>"
~~~

---

## 7. Create Card

**POST** `/card/{id}`

Creates a new card inside a deck.

### Purpose
Use this when the user wants to add a flashcard to a specific deck.

### Path Parameter
- `id` = deck UUID

### Request Body

~~~json
{
  "question": "What is dependency injection?",
  "answer": "A design pattern where dependencies are provided from outside the class."
}
~~~

### Success Response
- `200 OK`

### Example

~~~bash
curl -X POST http://localhost:8080/card/<deck-id> \
  -H "Content-Type: application/json" \
  --cookie "auth=<session-token>" \
  -d '{
    "question": "What is dependency injection?",
    "answer": "A design pattern where dependencies are provided from outside the class."
  }'
~~~

---

## 8. Get Cards In Deck

**GET** `/card/{id}/deck`

Returns all cards that belong to a specific deck.

### Purpose
Use this when the frontend opens a deck and needs to display all its flashcards.

### Path Parameter
- `id` = deck UUID

### Success Response
- `200 OK`

### Example

~~~bash
curl -X GET http://localhost:8080/card/<deck-id>/deck \
  --cookie "auth=<session-token>"
~~~

---

## 9. Delete Card

**DELETE** `/card/{id}`

Deletes a card by its ID.

### Purpose
Use this when the user wants to remove a single flashcard.

### Path Parameter
- `id` = card UUID

### Success Response
- `200 OK`

### Example

~~~bash
curl -X DELETE http://localhost:8080/card/<card-id> \
  --cookie "auth=<session-token>"
~~~

---

# Typical Frontend Usage

A frontend app would usually use the API in this order:

## 1. Sign Up
Call:
- `POST /user/register`

## 2. Log In
Call:
- `POST /user/login`

The browser or client stores the returned `auth` cookie.

## 3. Create a Deck
Call:
- `POST /deck`

Example:
- Java Basics
- Biology Quiz 1
- Networking Terms

## 4. Add Cards to the Deck
Call:
- `POST /card/{deckId}`

Example cards:
- question: `What is JVM?`
- answer: `Java Virtual Machine`

## 5. Show Data to the User
Call:
- `GET /deck` to show all decks
- `GET /card/{deckId}/deck` to show cards in a selected deck

## 6. Delete Data When Needed
Call:
- `DELETE /deck/delete/{id}`
- `DELETE /card/{id}`

## 7. Log Out
Call:
- `GET /user/logout`

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

# Endpoint Summary

| Method | Endpoint | Purpose | Auth Required |
|--------|----------|---------|---------------|
| POST | `/user/register` | Create a new user account | No |
| POST | `/user/login` | Log in and create a session | No |
| GET | `/user/logout` | Log out and delete session | Yes |
| POST | `/deck` | Create a new deck | Yes |
| GET | `/deck` | Get all decks for the logged-in user | Yes |
| DELETE | `/deck/delete/{id}` | Delete a deck | Yes |
| POST | `/card/{id}` | Create a card in a deck | Yes |
| GET | `/card/{id}/deck` | Get all cards in a deck | Yes |
| DELETE | `/card/{id}` | Delete a card | Yes |