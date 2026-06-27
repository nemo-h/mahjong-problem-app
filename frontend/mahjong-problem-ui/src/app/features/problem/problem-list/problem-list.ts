import { Component, OnInit, signal } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterLink } from '@angular/router';
import { ProblemService } from '../../../core/services/problem';

@Component({
  selector: 'app-problem-list',
  standalone: true,
  imports: [CommonModule, RouterLink],
  templateUrl: './problem-list.html',
  styleUrl: './problem-list.css'
})
export class ProblemListComponent implements OnInit {

  problems = signal<any[]>([]);

  constructor(private problemService: ProblemService) {}

  ngOnInit(): void {
    console.log('一覧画面 ngOnInit 実行');

    this.problemService.getProblems().subscribe({
      next: (data) => {
        console.log('一覧データ取得成功', data);
        this.problems.set(data);
        console.log('problems代入後', this.problems());
      },
      error: (error) => {
        console.error('一覧取得失敗', error);
      }
    });
  }
}