package fr.epita.quiz.datamodel;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="QUESTIONS")
public class Question {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="Q_ID")
	private Integer id;
	
	@Column(name="Q_CONTENT")
	private String content;

	public Question() {

	}
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
	
	
	
}
