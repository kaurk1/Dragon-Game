import { Quest } from '../models/game.model';

function decodeBase64(s: string): string {
  const bytes = Uint8Array.from(atob(s), c => c.charCodeAt(0));
  return new TextDecoder().decode(bytes);
}

export function decodeQuest(quest: Quest): Quest {
  if (!quest.encrypted) return quest;

  return {
    ...quest,
    adId: decodeBase64(quest.adId),
    message: decodeBase64(quest.message),
    probability: decodeBase64(quest.probability),
  };
}
