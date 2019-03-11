package fr.epita.quiz.tests;

import java.sql.Connection;

import javax.inject.Inject;
import javax.inject.Named;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import fr.epita.quiz.datamodel.Question;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/applicationContext.xml")
public class TestJPAWithHibernate {

	
	@Inject
	@Named("sessionFactory")
	SessionFactory sessionFactory;
	

	@Test
	public void testHibernateSessionFactory() throws Exception {

		Assert.assertNotNull(sessionFactory);
		
		//given
		Session session = sessionFactory.openSession();
		Question question = new Question();
		String expected = "What is Hibernate?";
		question.setContent(expected);
		
		//when
		session.save(question);
		
		
		//then
		Question retrievedQuestion = session.get(Question.class, question.getId());
		Assert.assertEquals(expected, retrievedQuestion.getContent());
		
		session.close();
		
	}
	
	

}
