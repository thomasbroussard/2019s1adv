package fr.epita.quiz.resources;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import fr.epita.quiz.datamodel.Question;

@Path("/questions")
public class QuestionResource {

	
	@GET
	@Path("/")
	@Produces(value = { MediaType.APPLICATION_JSON })
	public Response findAllQuestions(@QueryParam("s") String inputString) {
		
		List<Question> messages = new ArrayList<Question>();
		Question question = new Question();
		question.setContent("What is Java?");
		messages.add(question);
		
		return Response.ok(messages).build();
	}
	
	
}
