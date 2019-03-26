package fr.epita.quiz.resources;

import java.util.Arrays;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import fr.epita.quiz.datamodel.Question;

@Path("/questions")
public class QuestionResource {

	
	@GET
	public String getAllQuestions() throws JsonProcessingException{
		Question question = new Question();
		question.setContent("What is Maven?");
		ObjectMapper objectMapper = new ObjectMapper();
		String listAsJSON = objectMapper.writer().writeValueAsString(Arrays.asList(question));
		return listAsJSON;
	}
	
	
}
