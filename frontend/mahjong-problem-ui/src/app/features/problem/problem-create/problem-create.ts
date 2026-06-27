import { Component, signal } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { Router, RouterLink } from '@angular/router';
import { ProblemService } from '../../../core/services/problem';

@Component({
  selector: 'app-problem-create',
  standalone: true,
  imports: [CommonModule, FormsModule, RouterLink],
  templateUrl: './problem-create.html',
  styleUrl: './problem-create.css'
})
export class ProblemCreateComponent {

  questionText = '';
  tehaiText = '';
  answerTile = '';
  explanation = '';

  errorMessage = signal<string | null>(null);

  constructor(
    private problemService: ProblemService,
    private router: Router
  ) {}

  createProblem(): void {
    const request = {
      questionText: this.questionText,
      tehai: this.tehaiText.split(',').map(tile => tile.trim()),
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