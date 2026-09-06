import { Component, OnInit, signal } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ActivatedRoute, Router, RouterLink } from '@angular/router';
import { ProblemService } from '../../../core/services/problem';
import { getClientId } from '../../../core/util/client-id';

@Component({
  selector: 'app-problem-detail',
  standalone: true,
  imports: [CommonModule, RouterLink],
  templateUrl: './problem-detail.html',
  styleUrl: './problem-detail.css'
})
export class ProblemDetailComponent implements OnInit {

  problem = signal<any | null>(null);
  hoveredIndex = signal<number | null>(null);
  submitting = signal<boolean>(false);

  constructor(
    private route: ActivatedRoute,
    private router: Router,
    private problemService: ProblemService
  ) {}

  ngOnInit(): void {
    console.log('詳細画面 ngOnInit 実行');

    this.route.paramMap.subscribe(params => {
      const id = Number(params.get('id'));
      console.log('取得したID:', id);

      this.problemService.getProblem(id).subscribe({
        next: (data) => {
          console.log('詳細データ取得成功', data);
          this.problem.set(data);
          console.log('problem代入後', this.problem());
        },
        error: (error) => {
          console.error('詳細取得失敗', error);
        }
      });
    });
  }

  onTileEnter(index: number): void {
    this.hoveredIndex.set(index);
  }

  onTileLeave(index: number): void {
    if (this.hoveredIndex() === index) {
      this.hoveredIndex.set(null);
    }
  }

  onTileClick(tile: string): void {
    const p = this.problem();
    if (!p || this.submitting()) return;

    this.submitting.set(true);
    const clientId = getClientId();

    this.problemService.submitAnswer(p.id, { selectedTile: tile, clientId }).subscribe({
      next: (result) => {
        this.router.navigate(['/problems', p.id, 'answer'], {
          state: { correct: result.correct, selectedTile: tile }
        });
      },
      error: (error) => {
        console.error('回答送信失敗', error);
        this.submitting.set(false);
      }
    });
  }

  deleteProblem(): void {
    const p = this.problem();

    if (!p) return;

    if (!window.confirm('削除しますか？')) return;

    this.problemService.deleteProblem(p.id).subscribe({
      next: () => {
        this.router.navigate(['/problems']);
      },
      error: (error) => {
        console.error(error);
      }
    });
  }

}