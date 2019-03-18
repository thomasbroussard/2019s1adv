package fr.epita.quiz.services.dataaccess;

import java.util.List;
import java.util.Map.Entry;

import javax.inject.Inject;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

public abstract class GenericDAO<T>{


	@Inject
	protected SessionFactory sf;
	
	
	public void create(T entity) {
		Session session = sf.openSession();
		Transaction tx = session.getTransaction();
		boolean isTxExist = false;
		// if the transaction is not null but not active
		if (tx != null && !tx.isActive()) {
			tx.begin();
		}else if (tx == null) { // it is not existing
			tx = session.beginTransaction(); // then we create it
		}else {
			isTxExist = true; // remember that the tx is already setup by a calling method
		}
		session.save(entity);	
		
		if (!isTxExist) {
			tx.commit();	
		}
		
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
		QueryHolder<T> holder = new QueryHolder<T>();
		prepareSearch(criteria, holder);
		Session session = sf.openSession();
		Query<T> query = session.createQuery(holder.getQueryString(), holder.getClassName());
		for(Entry<String,Object> entry : holder.getMap().entrySet()) {
			query.setParameter(entry.getKey(), entry.getValue());
		}
		return query.list();
		
	}
	
	public abstract void prepareSearch(T criteria, QueryHolder<T> holder);
	
}
