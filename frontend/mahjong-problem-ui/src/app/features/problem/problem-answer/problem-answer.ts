import { Component, OnInit, signal } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ActivatedRoute, RouterLink } from '@angular/router';
import { ProblemService } from '../../../core/services/problem';

@Component({
  selector: 'app-problem-answer',
  standalone: true,
  imports: [CommonModule, RouterLink],
  templateUrl: './problem-answer.html',
  styleUrl: './problem-answer.css'
})
export class ProblemAnswerComponent implements OnInit {

  answer = signal<any | null>(null);
  problemId = signal<number | null>(null);
  myResult = signal<{ correct: boolean; selectedTile: string } | null>(null);

  explanationParts(text: string): { type: 'text' | 'tile'; value: string }[] {
    return text
      .split(/([0-9][mpsz])/g)
      .filter(part => part.length > 0)
      .map(part => ({
        type: /^[0-9][mpsz]$/.test(part) ? 'tile' : 'text',
        value: part
      } as { type: 'text' | 'tile'; value: string }));
  }

  constructor(
    private route: ActivatedRoute,
    private problemService: ProblemService
  ) {}

  ngOnInit(): void {
    const state = history.state as { correct?: boolean; selectedTile?: string };
    if (typeof state?.correct === 'boolean' && state.selectedTile) {
      this.myResult.set({ correct: state.correct, selectedTile: state.selectedTile });
    }

    this.route.paramMap.subscribe(params => {
      const id = Number(params.get('id'));
      this.problemId.set(id);

      this.problemService.getAnswer(id).subscribe({
        next: (data) => {
          this.answer.set(data);
        },
        error: (error) => {
          console.error('回答取得失敗', error);
        }
      });
    });
  }
}