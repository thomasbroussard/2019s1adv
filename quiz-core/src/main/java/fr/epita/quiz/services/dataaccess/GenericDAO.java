package fr.epita.quiz.services.dataaccess;

import java.util.List;

import javax.inject.Inject;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import fr.epita.quiz.datamodel.Question;

public abstract class GenericDAO<T> {
	
	@Inject
	private SessionFactory sf;
	
	public void create(T entity) {
		Session session = sf.openSession();
		Transaction tx = session.beginTransaction();
		session.save(entity);
		tx.commit();
		session.close();
		
	}
	public void delete(T entity) {
		Session session = sf.openSession();
		Transaction tx = session.beginTransaction();
		session.delete(entity);
		tx.commit();
		session.close();
	}
	public void update(T entity) {
		Session session = sf.openSession();
		Transaction tx = session.beginTransaction();
		session.update(entity);
		tx.commit();
		session.close();
	}
	
	
	
	
	public List<T> search(T criteria){
		Session session = sf.openSession();
		List<T> list = performSearch(criteria, session);
		session.close();
		return list;
		
	}
	protected abstract List<T> performSearch(T criteria, Session session);

}
