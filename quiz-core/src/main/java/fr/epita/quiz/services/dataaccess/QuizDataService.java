package fr.epita.quiz.services.dataaccess;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.SessionFactory;

import fr.epita.quiz.datamodel.Choice;
import fr.epita.quiz.datamodel.Question;

public class QuizDataService {
	
	private static final Logger LOGGER = LogManager.getLogger(QuizDataService.class); 
	
	@Inject
	QuestionJPADAO questionDAO;
	
	@Inject
	ChoiceJPADAO choiceDAO;
	
	@PersistenceContext
	EntityManager em;
	
	@Transactional(Transactional.TxType.REQUIRED)
	public void createQuestionAndChoices(Question question, Choice... choices) {
		
		LOGGER.info("entering the createQuestionAndChoices method");
		questionDAO.create(question);
		for (Choice choice : choices) {
			choice.setQuestion(question);
			choiceDAO.create(choice);
		}
		
	
		
		
	}

}
