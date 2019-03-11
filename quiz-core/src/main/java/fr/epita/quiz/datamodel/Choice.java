package fr.epita.quiz.datamodel;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="CHOICES")
public class Choice {

	@Id
	Integer id;
	
	@ManyToOne
	Question question;
	
}
