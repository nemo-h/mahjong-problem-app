import { Component, signal } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { Router, RouterLink } from '@angular/router';
import { ProblemService } from '../../../core/services/problem';

interface TileGroup {
  suit: string;
  tiles: string[];
}

const TILE_GROUPS: TileGroup[] = [
  { suit: '萬子', tiles: ['1m', '2m', '3m', '4m', '5m', '0m', '6m', '7m', '8m', '9m'] },
  { suit: '筒子', tiles: ['1p', '2p', '3p', '4p', '5p', '0p', '6p', '7p', '8p', '9p'] },
  { suit: '索子', tiles: ['1s', '2s', '3s', '4s', '5s', '0s', '6s', '7s', '8s', '9s'] },
  { suit: '字牌', tiles: ['1z', '2z', '3z', '4z', '5z', '6z', '7z'] }
];

@Component({
  selector: 'app-problem-create',
  standalone: true,
  imports: [CommonModule, FormsModule, RouterLink],
  templateUrl: './problem-create.html',
  styleUrl: './problem-create.css'
})
export class ProblemCreateComponent {

  tileGroups = TILE_GROUPS;

  questionText = '';
  tehai: string[] = [];
  answerTile = '';
  explanation = '';

  errorMessage = signal<string | null>(null);

  constructor(
    private problemService: ProblemService,
    private router: Router
  ) {}

  addTile(code: string): void {
    this.tehai.push(code);
  }

  removeTile(index: number): void {
    const removed = this.tehai[index];
    this.tehai.splice(index, 1);
    if (removed === this.answerTile && !this.tehai.includes(removed)) {
      this.answerTile = '';
    }
  }

  selectAnswer(index: number): void {
    const code = this.tehai[index];
    this.answerTile = this.answerTile === code ? '' : code;
  }

  createProblem(): void {
    const request = {
      questionText: this.questionText,
      tehai: this.tehai,
      answerTile: this.answerTile,
      explanation: this.explanation
    };

    console.log('登録リクエスト', request);

    this.problemService.createProblem(request).subscribe({
      next: (response) => {
        console.log('登録成功', response);
        this.router.navigate(['/problems']);
      },
      error: (error) => {
        console.error('登録失敗', error);
        this.errorMessage.set('問題登録に失敗しました');
      }
    });
  }
}
