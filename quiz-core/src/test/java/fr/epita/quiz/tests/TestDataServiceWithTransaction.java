package fr.epita.quiz.tests;

import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import javax.inject.Inject;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import fr.epita.quiz.datamodel.Choice;
import fr.epita.quiz.datamodel.Question;
import fr.epita.quiz.services.dataaccess.ChoiceJPADAO;
import fr.epita.quiz.services.dataaccess.QuizDataService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/applicationContext.xml")
public class TestDataServiceWithTransaction {
	
	@Inject
	QuizDataService quizDS;
	
	@Inject
	ChoiceJPADAO choiceDAO;
	
	@Test
	public void testQuestionWithChoicesInsertion() {
		
		//given
		Question question = new Question();
		question.setContent("What is maven ?");
		Choice choice1 = new Choice();
		String firstChoiceText = "it is a dependency injection framework";
		choice1.setText(firstChoiceText);
		choice1.setValid(false);
		Choice choice2 = new Choice();
		choice2.setText("it is including a dependency management tool");
		choice2.setValid(true);
		
		//when
		quizDS.createQuestionAndChoices(question, choice1, choice2);
		
		//then
		Choice criteria = new Choice();
		criteria.setQuestion(question);
		List<Choice> searchResults = choiceDAO.search(criteria);
		Assert.assertTrue(!searchResults.isEmpty());
		
		
		//Other way to use stream without lambdas
//		searchResults.stream().filter(new Predicate<Choice>() {
//
//			@Override
//			public boolean test(Choice t) {
//				return t.getQuestion().equals(firstChoiceText);
//			}
//		});
		
		List<Choice> subList = searchResults.stream()
				.filter(c -> c.getText().equals(firstChoiceText))
				.collect(Collectors.toList());
		
		Assert.assertEquals(1, subList.size());
		
	}

	
	
}
