package fr.epita.quiz.services.dataaccess;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.query.Query;

import fr.epita.quiz.datamodel.Question;

public class QuestionJPADAO extends GenericDAO<Question>{
	



	@Override
	protected List<Question> performSearch(Question criteria, Session session) {
		Query<Question> query = session.createQuery("from Question as q where q.content like :content", Question.class);
		query.setParameter("content", "%" +  criteria.getContent() + "%");
		List<Question> list = query.list();
		return list;
	}

}
