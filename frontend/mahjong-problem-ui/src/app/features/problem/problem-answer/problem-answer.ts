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
    console.log('回答画面 ngOnInit 実行');

    this.route.paramMap.subscribe(params => {
      const id = Number(params.get('id'));
      this.problemId.set(id);

      console.log('取得したID:', id);

      this.problemService.getAnswer(id).subscribe({
        next: (data) => {
          console.log('回答データ取得成功', data);
          this.answer.set(data);
        },
        error: (error) => {
          console.error('回答取得失敗', error);
        }
      });
    });
  }
}