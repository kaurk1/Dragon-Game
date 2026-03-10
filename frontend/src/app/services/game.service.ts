import { Injectable, inject } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import {
  Game,
  Purchase,
  Quest,
  QuestResult,
  ShopItem,
} from "../models/game.model";

@Injectable({ providedIn: "root" })
export class GameService {
  private http = inject(HttpClient);
  private baseUrl = "http://localhost:8080/api/game";

  playGame(targetScore: number): Observable<Game> {
    return this.http.post<Game>(`${this.baseUrl}/play`, null, {
      params: { targetScore: targetScore.toString() },
    });
  }

  startGame(): Observable<Game> {
    return this.http.post<Game>(`${this.baseUrl}/start`, null);
  }

  getQuests(gameId: string): Observable<Quest[]> {
    return this.http.get<Quest[]>(`${this.baseUrl}/${gameId}/messages`);
  }

  solveQuest(gameId: string, adId: string): Observable<QuestResult> {
    return this.http.post<QuestResult>(
      `${this.baseUrl}/${gameId}/solve/${adId}`,
      null,
    );
  }

  getShopItems(gameId: string): Observable<ShopItem[]> {
    return this.http.get<ShopItem[]>(`${this.baseUrl}/${gameId}/shop`);
  }

  buyItem(gameId: string, itemId: string): Observable<Purchase> {
    return this.http.post<Purchase>(
      `${this.baseUrl}/${gameId}/shop/buy/${itemId}`,
      null,
    );
  }
}
