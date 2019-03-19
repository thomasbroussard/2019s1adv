package fr.epita.quiz.services.dataaccess;

public interface BiOperation<T,U> {

	void trigger(T first,U second);
	
}
