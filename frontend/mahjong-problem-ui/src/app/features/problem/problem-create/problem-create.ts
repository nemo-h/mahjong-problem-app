import { Component, OnInit, signal } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { ActivatedRoute, Router, RouterLink } from '@angular/router';
import { ProblemService } from '../../../core/services/problem';
import { SourceService } from '../../../core/services/source';

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
export class ProblemCreateComponent implements OnInit {

  tileGroups = TILE_GROUPS;
  sources = signal<any[]>([]);

  questionText = '';
  tehai: string[] = [];
  answerTile = '';
  doraTile = '';
  sourceId: number | null = null;
  sourceNumber: number | null = null;
  ba: string | null = null;
  kaze: string | null = null;
  jun: number | null = null;
  explanation = '';

  errorMessage = signal<string | null>(null);
  editingId: number | null = null;

  get isEditMode(): boolean {
    return this.editingId !== null;
  }

  constructor(
    private problemService: ProblemService,
    private sourceService: SourceService,
    private router: Router,
    private route: ActivatedRoute
  ) {}

  ngOnInit(): void {
    this.sourceService.getSources().subscribe({
      next: (sources) => {
        this.sources.set(sources);
      },
      error: (error) => {
        console.error('引用元取得失敗', error);
      }
    });

    const idParam = this.route.snapshot.paramMap.get('id');
    if (idParam) {
      this.editingId = Number(idParam);
      this.loadProblemForEdit(this.editingId);
    }
  }

  private loadProblemForEdit(id: number): void {
    this.problemService.getProblem(id).subscribe({
      next: (problem) => {
        this.questionText = problem.questionText;
        this.tehai = problem.tehai ?? [];
        this.doraTile = problem.doraTile ?? '';
        this.sourceId = problem.sourceId ?? null;
        this.sourceNumber = problem.sourceNumber ?? null;
        this.ba = problem.ba ?? null;
        this.kaze = problem.kaze ?? null;
        this.jun = problem.jun ?? null;
      },
      error: (error) => {
        console.error('編集対象の問題取得失敗', error);
        this.errorMessage.set('問題の取得に失敗しました');
      }
    });

    this.problemService.getAnswer(id).subscribe({
      next: (answer) => {
        this.answerTile = answer.answerTile ?? '';
        this.explanation = answer.explanation ?? '';
      },
      error: (error) => {
        console.error('編集対象の回答取得失敗', error);
      }
    });
  }

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

  selectDora(code: string): void {
    this.doraTile = this.doraTile === code ? '' : code;
  }

  createProblem(): void {
    const request = {
      questionText: this.questionText,
      tehai: this.tehai,
      answerTile: this.answerTile,
      doraTile: this.doraTile,
      sourceId: this.sourceId,
      sourceNumber: this.sourceNumber,
      ba: this.ba,
      kaze: this.kaze,
      jun: this.jun,
      explanation: this.explanation
    };

    if (this.isEditMode) {
      this.problemService.updateProblem(this.editingId!, request).subscribe({
        next: () => {
          this.router.navigate(['/problems', this.editingId]);
        },
        error: (error) => {
          console.error('更新失敗', error);
          this.errorMessage.set('問題更新に失敗しました');
        }
      });
      return;
    }

    this.problemService.createProblem(request).subscribe({
      next: () => {
        this.router.navigate(['/problems']);
      },
      error: (error) => {
        console.error('登録失敗', error);
        this.errorMessage.set('問題登録に失敗しました');
      }
    });
  }
}
