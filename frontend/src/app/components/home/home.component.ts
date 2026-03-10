import { Component } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrl: './home.component.scss'
})
export class HomeComponent {
  constructor(private router: Router) {}

  navigateToAiPlay(): void {
    this.router.navigate(['/ai-play']);
  }

  navigateToManualPlay(): void {
    this.router.navigate(['/manual-play']);
  }
}
