package fr.epita.quiz.services.dataaccess;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import fr.epita.quiz.datamodel.Question;

public class QuestionJPADAO {
	
	@Inject
	SessionFactory sf;
	
	public void create(Question question) {
		Session session = sf.openSession();
		session.save(question);
		session.flush();
		session.close();
		
	}
	public void delete(Question question) {
		Session session = sf.openSession();
		session.delete(question);
		session.flush();
		session.close();
	}
	public void update(Question question) {
		Session session = sf.openSession();
		session.update(question);
		session.flush();
		session.close();
	}
	public List<Question> search(Question question) {
		Session session = sf.openSession();
		Query<Question> query = session.createQuery("from Question as q where q.content like :content", Question.class);
		query.setParameter("content", "%" +  question.getContent() + "%");
		List<Question> list = query.list();
		session.close();
		return list;
	}

}
