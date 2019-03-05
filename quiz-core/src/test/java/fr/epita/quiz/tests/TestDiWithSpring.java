package fr.epita.quiz.tests;

import java.sql.Connection;

import javax.inject.Inject;
import javax.inject.Named;
import javax.sql.DataSource;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/applicationContext.xml")
public class TestDiWithSpring {

	
	@Inject
	@Named("dataSource")
	DataSource ds;
	
	@Inject
	@Named("firstQuery")
	String text;
	
	@Test
	public void testFirstInject() throws Exception {

		Assert.assertNotNull(text);
		System.out.println(text);
		
		
		
	}
	
	@Test
	public void testDS() throws Exception {

		Assert.assertNotNull(ds);
		Connection connection = ds.getConnection();
		String schema = connection.getSchema();
		Assert.assertNotNull(schema);
		System.out.println(schema);
		connection.close();
		
		
	}


}
