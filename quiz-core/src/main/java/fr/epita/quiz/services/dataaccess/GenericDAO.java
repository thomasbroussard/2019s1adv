package fr.epita.quiz.services.dataaccess;

import java.io.Serializable;
import java.util.List;
import java.util.Map.Entry;
import java.util.function.BiFunction;

import javax.inject.Inject;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

public abstract class GenericDAO<T>{


	@Inject
	protected SessionFactory sf;
	
	
	
	private void handleOperation(T entity, BiOperation<Session,T> callback) {
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
		callback.trigger(session,entity);
		
		if (!isTxExist) {
			tx.commit();	
		}
		
		session.close();
	}
	
	public void create(T entity) {
		BiOperation<Session,T> biOperation = (session, e) -> session.save(entity); 
		handleOperation(entity, biOperation);
	}
	public void delete(T entity) {
		BiOperation<Session,T> biOperation = (session, e) -> session.delete(entity);
		handleOperation(entity, biOperation);
	}
	public void update(T entity) {
		BiOperation<Session,T> biOperation = (session, e) -> session.update(entity);
		handleOperation(entity, biOperation);
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
