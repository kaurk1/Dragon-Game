
export interface Game {
  gameId: string;
  lives: number;
  gold: number;
  level: number;
  score: number;
  highScore: number;
  turn: number;
  reachedTarget: boolean;
}

export interface Quest {
  adId: string;
  message: string;
  reward: number;
  expiresIn: number;
  encrypted: number | null;
  probability: string;
}

export interface QuestResult {
  success: boolean;
  lives: number;
  gold: number;
  score: number;
  highScore: number;
  turn: number;
  message: string;
}

export interface ShopItem {
  id: string;
  name: string;
  cost: number;
}

export interface Purchase {
  shoppingSuccess: boolean;
  gold: number;
  lives: number;
  level: number;
  turn: number;
}