# Dragons of Mugloar

A fullstack web application that plays the [Dragons of Mugloar](https://dragonsofmugloar.com) game. You can let the AI play automatically to reach a target score, or take control yourself and manually pick quests and buy items from the shop.

## Tech Stack

- **Backend** — Spring Boot 4.0.3 (Java 21)
- **Frontend** — Angular 21

---

## How to Run

### Prerequisites
- Java 21
- Node.js 18+
- Angular CLI (`npm install -g @angular/cli`)

### 1. Start the Backend

```bash
cd backend
./gradlew bootRun
```

The API will be available at `http://localhost:8080`.

### 2. Start the Frontend

```bash
cd frontend
npm install
ng serve
```

Open `http://localhost:4200` in your browser.

---

## How to Play

### AI Mode
1. Click **AI Play** on the home screen
2. Enter a target score (default 5000)
3. Click **Play Game** and wait for the result

### Manual Mode
1. Click **Play Manually** on the home screen
2. A new game starts automatically
3. Pick quests from the **Quests** tab to earn gold and score points
4. Visit the **Shop** tab to buy items that boost your dragon
5. Survive as long as possible — the game ends when you run out of lives
