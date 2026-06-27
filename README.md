








---


# Mahjong Problem App

A web application for managing and practicing Mahjong "What to discard" problems.  
Users can create, view, and solve problems to improve their decision-making skills.



## 🚀 Features

- View problem list
- View problem details
- View answers
- Create new problems
- Delete problems



## 🛠 Tech Stack

### Frontend
- Angular
- TypeScript

### Backend
- Spring Boot
- Java

### Database
- H2 Database (file-based persistence)



## 📂 Project Structure

```
mahjong-problem-app/
├ backend/
└ frontend/
```

## ⚙️ Setup Instructions

### 1. Start Backend

```bash
cd backend/demo
./gradlew bootRun
```

### 2. Start Frontend

```bash
cd frontend/mahjong-problem-ui
ng serve
```

### 3. Open in Browser

```bash
http://localhost:4200
```

## 🗄 Database (H2 Console)

```bash
http://localhost:8080/h2-console
```

### Configuration:

```bash
JDBC URL: jdbc:h2:file:./data/mahjong-db
User: sa
Password:
```

## 📡 API Endpoints

| Method | Endpoint | Description |
|--------|----------|-------------|
| GET    | /api/problems | Get all problems |
| GET    | /api/problems/{id} | Get problem details |
| GET    | /api/problems/{id}/answer | Get answer for a problem |
| POST   | /api/problems | Create a new problem |
| DELETE | /api/problems/{id} | Delete a problem |


```bash
## Get all problems
http://localhost:8080/api/problems

## Get problem details
http://localhost:8080/api/problems/1

## Get answer for a problem
http://localhost:8080/api/problems/1/answer

```






















