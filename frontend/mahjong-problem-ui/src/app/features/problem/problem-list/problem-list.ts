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
    this.problemService.getProblems().subscribe({
      next: (data) => {
        this.problems.set(data);
      },
      error: () => {
      }
    });
  }
}