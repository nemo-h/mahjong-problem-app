import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ProblemAnswer } from './problem-answer';

describe('ProblemAnswer', () => {
  let component: ProblemAnswer;
  let fixture: ComponentFixture<ProblemAnswer>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [ProblemAnswer],
    }).compileComponents();

    fixture = TestBed.createComponent(ProblemAnswer);
    component = fixture.componentInstance;
    await fixture.whenStable();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
