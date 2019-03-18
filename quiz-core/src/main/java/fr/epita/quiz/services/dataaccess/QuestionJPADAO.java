package fr.epita.quiz.services.dataaccess;

import fr.epita.quiz.datamodel.Question;

public class QuestionJPADAO extends GenericDAO<Question>{
	


	@Override
	public void prepareSearch(Question criteria, QueryHolder<Question> holder) {
		holder.setQueryString("from Question as q where q.content like :content"); 
		holder.setClassName(Question.class);
		holder.putParameter("content", "%" +  criteria.getContent() + "%");
	}
	
	



}
