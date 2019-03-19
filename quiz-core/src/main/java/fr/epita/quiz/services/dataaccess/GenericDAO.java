package fr.epita.quiz.services.dataaccess;

import java.util.List;
import java.util.Map.Entry;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public abstract class GenericDAO<T>{

	private static final Logger LOGGER = LogManager.getLogger(GenericDAO.class);
	
	
//	@Inject
//	protected SessionFactory sf;
//	
	
	@PersistenceContext
	EntityManager em;
	
	
//	private void handleOperation(T entity, BiOperation<Session,T> callback) {
//		Session session = sf.openSession();
//		Transaction tx = session.getTransaction();
//		boolean isTxExist = false;
//		// if the transaction is not null but not active
//		if (tx != null && !tx.isActive()) {
//			tx.begin();
//		}else if (tx == null) { // it is not existing
//			tx = session.beginTransaction(); // then we create it
//		}else {
//			isTxExist = true; // remember that the tx is already setup by a calling method
//		}
//		callback.trigger(session,entity);
//		
//		if (!isTxExist) {
//			tx.commit();	
//		}
//		
//		session.close();
//	}
	//old way to perform operation without JTA
//	public void delete(T entity) {
//		BiOperation<Session,T> biOperation = (session, e) -> session.delete(entity);
//		handleOperation(entity, biOperation);
//	}
	
	//old way to persist data with session factory
//	@Transactional(Transactional.TxType.REQUIRED)
//	public void update(T entity) {
//		Session session = sf.openSession();
//		session.update(entity); 
//		session.close();
//	}
	
	@Transactional(Transactional.TxType.REQUIRED)
	public void create(T entity) {
		LOGGER.info("entering the create method");
		em.persist(entity); 
		LOGGER.info("exiting the create method");
	}
	
	@Transactional(Transactional.TxType.REQUIRED)
	public void delete(T entity) {
		em.remove(entity); 
	}
	
	@Transactional(Transactional.TxType.REQUIRED)
	public void update(T entity) {
		em.merge(entity);
	}
	
	public List<T> search(T criteria){
		QueryHolder<T> holder = new QueryHolder<T>();
		prepareSearch(criteria, holder);
		
		TypedQuery<T> query = em.createQuery(holder.getQueryString(), holder.getClassName());
		for(Entry<String,Object> entry : holder.getMap().entrySet()) {
			query.setParameter(entry.getKey(), entry.getValue());
		}
		return query.getResultList();
		
	}
	
	public abstract void prepareSearch(T criteria, QueryHolder<T> holder);
	
}
