package fr.epita.quiz.tests;

import org.junit.Test;

import fr.epita.quiz.services.calculation.FactorialService;

public class TestFactorial {

	
	@Test
	public void testFactorialWithPositiveInteger() throws Exception {
		//Given
		int number = 5;
		
		//When
		FactorialService factorial = new FactorialService();
		Integer result = factorial.calculate(number);
		
		//Then
		if (result != 120) {
			throw new Exception("factorial returned a wrong value, expected 120, got :" +  result);
		}
		
		System.out.println("if we got there, then no issue!");

	}

}
