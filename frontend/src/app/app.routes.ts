import { Routes } from '@angular/router';
import { HomeComponent } from './components/home/home.component';
import { AiPlayComponent } from './components/ai-play/ai-play.component';
import { ManualPlayComponent } from './components/manual-play/manual-play.component';

export const routes: Routes = [
  { path: '', component: HomeComponent },
  { path: 'ai-play', component: AiPlayComponent },
  { path: 'manual-play', component: ManualPlayComponent },
  { path: '**', redirectTo: '' }
];
