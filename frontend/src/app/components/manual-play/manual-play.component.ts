import { Component, signal, inject, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { HttpErrorResponse } from '@angular/common/http';
import { GameService } from '../../services/game.service';
import { decodeQuest } from '../../utils/quest-decoder';
import {
  Game,
  Purchase,
  Quest,
  QuestResult,
  ShopItem,
} from "../../models/game.model";

type Tab = 'quests' | 'shop';
type ActionResult = { type: 'quest'; data: QuestResult } | { type: 'purchase'; data: Purchase };

@Component({
  selector: 'app-manual-play',
  templateUrl: './manual-play.component.html',
  styleUrl: './manual-play.component.scss'
})
export class ManualPlayComponent implements OnInit {
  private gameService = inject(GameService);
  private router = inject(Router);

  game = signal<Game | null>(null);
  quests = signal<Quest[]>([]);
  shopItems = signal<ShopItem[]>([]);
  activeTab = signal<Tab>('quests');
  loading = signal(false);
  actionLoading = signal<string | null>(null);
  lastResult = signal<ActionResult | null>(null);
  error = signal<string | null>(null);
  gameOver = signal(false);

  ngOnInit(): void {
    this.startGame();
  }

  startGame(): void {
    this.loading.set(true);

    this.gameService.startGame().subscribe({
      next: (game) => {
        this.game.set(game);
        this.loading.set(false);
        this.loadQuests(game.gameId);
        this.loadShop(game.gameId);
      },
      error: (err: HttpErrorResponse) => {
        this.error.set(err.error?.message ?? 'Failed to start game.');
        this.loading.set(false);
      }
    });
  }

  loadQuests(gameId: string): void {
    this.gameService.getQuests(gameId).subscribe({
      next: (q) => this.quests.set(q.map(decodeQuest)),
      error: () => this.quests.set([])
    });
  }

loadShop(gameId: string): void {
    this.gameService.getShopItems(gameId).subscribe({
      next: (s) => this.shopItems.set(s),
      error: () => this.shopItems.set([])
    });
  }

  takeQuest(questId: string): void {
    const game = this.game();
    if (!game) return;

    this.beginAction(questId);
    this.gameService.solveQuest(game.gameId, questId).subscribe({
      next: (result) => {
        this.lastResult.set({ type: 'quest', data: result });
        this.updateGame({ lives: result.lives, gold: result.gold, score: result.score, highScore: result.highScore, turn: result.turn });
        this.actionLoading.set(null);

        if (result.lives <= 0) {
          this.gameOver.set(true);
          return;
        }
        this.loadQuests(game.gameId);
      },
      error: (err: HttpErrorResponse) => {
        this.error.set(err.error?.message ?? 'Failed to take quest.');
        this.actionLoading.set(null);
      }
    });
  }

  buyItem(itemId: string): void {
    const game = this.game();
    if (!game) return;
    this.beginAction(itemId);
    this.gameService.buyItem(game.gameId, itemId).subscribe({
      next: (result) => {
        this.lastResult.set({ type: 'purchase', data: result });
        this.updateGame({ gold: result.gold, lives: result.lives, level: result.level, turn: result.turn });
        this.actionLoading.set(null);
        this.loadShop(game.gameId);
      },
      error: (err: HttpErrorResponse) => {
        this.error.set(err.error?.message ?? 'Failed to buy item.');
        this.actionLoading.set(null);
      }
    });
  }

  private updateGame(patch: Partial<Game>): void {
    this.game.update(prev => prev ? { ...prev, ...patch } : prev);
  }

  private beginAction(id: string): void {
    this.actionLoading.set(id);
    this.lastResult.set(null);
    this.error.set(null);
  }

  setTab(tab: Tab): void {
    this.activeTab.set(tab);
  }

  goHome(): void {
    this.router.navigate(['/']);
  }

  private readonly probabilityRank: Record<string, number> = {
    'Walk in the park':   1,
    'Piece of cake':      2,
    'Sure thing':         3,
    'Quite likely':       4,
    'Hmmm....':           5,
    'Risky':              6,
    'Rather detrimental': 7,
    'Suicide mission':    8,
    'Impossible':         9,
  };

  probabilityClass(prob: string): string {
    const rank = this.probabilityRank[prob] ?? 5;
    if (rank <= 3) return 'prob-high';
    if (rank <= 6) return 'prob-med';
    return 'prob-low';
  }
}
