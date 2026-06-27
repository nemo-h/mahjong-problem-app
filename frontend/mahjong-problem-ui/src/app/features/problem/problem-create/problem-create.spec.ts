import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ProblemCreate } from './problem-create';

describe('ProblemCreate', () => {
  let component: ProblemCreate;
  let fixture: ComponentFixture<ProblemCreate>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [ProblemCreate],
    }).compileComponents();

    fixture = TestBed.createComponent(ProblemCreate);
    component = fixture.componentInstance;
    await fixture.whenStable();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
