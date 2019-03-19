package fr.epita.quiz.services.dataaccess;

import javax.inject.Inject;
import javax.transaction.Transactional;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import fr.epita.quiz.datamodel.Choice;
import fr.epita.quiz.datamodel.Question;

public class QuizDataService {
	
	@Inject
	QuestionJPADAO questionDAO;
	
	@Inject
	ChoiceJPADAO choiceDAO;
	
	@Inject
	SessionFactory sf;
	
	@Transactional(Transactional.TxType.REQUIRED)
	public void createQuestionAndChoices(Question question, Choice... choices) {
		questionDAO.create(question);
		for (Choice choice : choices) {
			choice.setQuestion(question);
			choiceDAO.create(choice);
		}
		
	
		
		
	}

}
