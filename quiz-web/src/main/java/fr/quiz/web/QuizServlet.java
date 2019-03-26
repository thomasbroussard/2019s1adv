package fr.quiz.web;

import java.io.IOException;
import java.util.List;

import javax.inject.Inject;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.AutowiredAnnotationBeanPostProcessor;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import com.fasterxml.jackson.databind.ObjectMapper;

import fr.epita.quiz.datamodel.Question;
import fr.epita.quiz.services.dataaccess.QuestionJPADAO;
import fr.epita.quiz.services.dataaccess.QuizDataService;

@WebServlet(urlPatterns="/quiz")
public class QuizServlet extends HttpServlet {

	@Inject
	QuizDataService quizDataService;
	
	@Inject
	QuestionJPADAO questionDAO;
	
	
	private static final long serialVersionUID = 1L;

	
	
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		Question criteria = new Question();
		criteria.setContent("");
		List<Question> list = questionDAO.search(criteria);
		
		ObjectMapper objectMapper = new ObjectMapper();
		String listAsJSON = objectMapper.writer().writeValueAsString(list);
		resp.getWriter().println(listAsJSON);
	}

	
	



	@Override
	public void init(ServletConfig config) throws ServletException {
		SpringBeanAutowiringSupport.processInjectionBasedOnServletContext(this, config.getServletContext());
	}
	
	
	
	

}
