package fr.epita.quiz.resources;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import fr.epita.quiz.datamodel.Question;
import fr.epita.quiz.services.dataaccess.QuestionJPADAO;

@Path("/questions")
public class QuestionResource {

	@Inject
	QuestionJPADAO questionDAO;
	
	
	@GET
	@Path("/")
	@Produces(value = { MediaType.APPLICATION_JSON })
	public Response findAllQuestions(@QueryParam("s") String inputString) {
		Question criteria = new Question();
		criteria.setContent(inputString);
		List<Question> searchResults = questionDAO.search(criteria);
		
		return Response.ok(searchResults).build();
	}
	
	@POST
	@Path("/")
	@Consumes(value = { MediaType.APPLICATION_JSON })
	public Response createQuestion(Question question) throws URISyntaxException {
		questionDAO.create(question);
		return Response.created(URI.create("questions/" + String.valueOf(question.getId()))).build();
		
	}
	
	
	
}
