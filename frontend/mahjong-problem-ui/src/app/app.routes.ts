import { Routes } from '@angular/router';
import { ProblemListComponent } from './features/problem/problem-list/problem-list';
import { ProblemDetailComponent } from './features/problem/problem-detail/problem-detail';
import { ProblemCreateComponent } from './features/problem/problem-create/problem-create';
import { ProblemAnswerComponent } from './features/problem/problem-answer/problem-answer';

export const routes: Routes = [
  { path: '', redirectTo: 'problems', pathMatch: 'full' },
  { path: 'problems', component: ProblemListComponent },
  { path: 'problems/new', component: ProblemCreateComponent },
  { path: 'problems/:id', component: ProblemDetailComponent },
  { path: 'problems/:id/answer', component: ProblemAnswerComponent }
];