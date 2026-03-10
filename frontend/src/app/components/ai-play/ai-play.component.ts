import { Component, signal, inject } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { HttpErrorResponse } from '@angular/common/http';
import { Router } from '@angular/router';
import { GameService } from "../../services/game.service";
import { Game } from "../../models/game.model";

@Component({
  selector: "app-game",
  imports: [FormsModule],
  templateUrl: "./ai-play.component.html",
  styleUrl: "./ai-play.component.scss",
})
export class AiPlayComponent {
  private gameService = inject(GameService);
  private router = inject(Router);

  goHome(): void {
    this.router.navigate(["/"]);
  }

  targetScore = signal(1000);
  loading = signal(false);
  result = signal<Game | null>(null);
  errorMessage = signal<string | null>(null);

  playGame(): void {
    this.loading.set(true);
    this.result.set(null);
    this.errorMessage.set(null);

    this.gameService.playGame(this.targetScore()).subscribe({
      next: (data) => {
        this.result.set(data);
        this.loading.set(false);
      },
      error: (err: HttpErrorResponse) => {
        const msg =
          err.error?.message ?? err.message ?? "An unexpected error occurred.";
        this.errorMessage.set(msg);
        this.loading.set(false);
      },
    });
  }

  updateTargetScore(value: string): void {
    const number = parseInt(value, 10);
    if (!isNaN(number)) {
      this.targetScore.set(number);
    }
  }
}
