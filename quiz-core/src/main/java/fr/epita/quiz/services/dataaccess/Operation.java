package fr.epita.quiz.services.dataaccess;

public interface Operation<T> {

	void trigger(T data);
}
