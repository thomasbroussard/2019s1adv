package fr.epita.quiz.services.calculation;

public class FactorialService {
	
	
	public Integer calculate(Integer depth) {
		if (depth <= 1 ) {
			return 1;
		}else {
			return calculate(depth - 1) * depth;
		}
		
	}

}
