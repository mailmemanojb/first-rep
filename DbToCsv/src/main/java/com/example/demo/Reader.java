package com.example.demo;

import javax.sql.DataSource;

import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.database.JdbcCursorItemReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@PropertySource("classpath:application.properties")
@Component
public class Reader {

	@Autowired
	public DataSource dataSource;
	
	JdbcCursorItemReader<User> itemReader;
	
	public ItemReader<User> getItemReader(){
		if(itemReader == null){
			itemReader = new JdbcCursorItemReader<User>();
			itemReader.setDataSource(dataSource);
			itemReader.setSql("select * from people");
			itemReader.setRowMapper(new UserRowMapper());			
		}
		return itemReader;
	}


	/*@Bean
	@Override
	public JdbcCursorItemReader<User> read()
			throws Exception, UnexpectedInputException, ParseException, NonTransientResourceException {
		System.out.println("Reader");
		JdbcCursorItemReader<User> reader = new JdbcCursorItemReader<User>();
		reader.setDataSource(dataSource);
		reader.setSql("select * from people");
		reader.setRowMapper(new UserRowMapper());
		return reader;
		
	}*/
	
	/*public DataSource dataSource(){
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setDriverClassName("org.mariadb.jdbc.Driver");
		dataSource.setUrl("jdbc:mariadb://localhost:3306/users");
		dataSource.setUsername("root");
		dataSource.setPassword("root");
		return dataSource;
	}
	*/
	/*@Bean
	@Override
	public JdbcCursorItemReader<User> read(){
		System.out.println("Reader");
		JdbcCursorItemReader<User> reader = new JdbcCursorItemReader<User>();
		reader.setDataSource(dataSource);
		reader.setSql("select firstname,lastname from people");
		reader.setRowMapper(new UserRowMapper());
		return reader;
	}*/

	
	
}
