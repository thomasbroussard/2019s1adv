import { Component, OnInit } from '@angular/core';
import { QuestionService } from 'src/app/services/question.service';
import { Question } from 'src/app/datamodel/question';

@Component({
  selector: 'app-question-list',
  templateUrl: './question-list.component.html',
  styleUrls: ['./question-list.component.css']
})
export class QuestionListComponent implements OnInit {

  questionList : Question[];

  constructor(private questionService : QuestionService) { }

  ngOnInit() {
    this.questionList = this.questionService.getQuestions();
  }

}
