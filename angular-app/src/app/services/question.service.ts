import { Injectable } from '@angular/core';
import { Question } from '../datamodel/question';

@Injectable({
  providedIn: 'root'
})
export class QuestionService {
 

  questionList : Question[] = [
    new Question('What is Java'),
    new Question('What is a REST service?')];

  constructor() { }

  getQuestions() : Question[]{
    return this.questionList;
  } 

  save(question: Question): void {
    this.questionList.push(question);
  }
}
