package com.example.demo;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;



@Configuration
@EnableBatchProcessing
public class BatchConfiguration {
	@Autowired
	public JobBuilderFactory jobBuilder;
	@Autowired
	public StepBuilderFactory stepBuilder;
	
	@Autowired
	private Writer writer;
	
	@Autowired
	private Reader reader;
	
	/*@Autowired
	private Processor processor;*/
	/*@Autowired
	public DataSource dataSource;*/
	/*@Bean
	public JdbcCursorItemReader<User> reader(){
		JdbcCursorItemReader<User> reader = new JdbcCursorItemReader<User>();
		reader.setDataSource(dataSource);
		reader.setSql("select firstname,lastname from people");
		reader.setRowMapper(new UserRowMapper());
		return reader;
	}*/
	/*@Bean
	public JdbcCursorItemReader<User> reader(){
		JdbcCursorItemReader<User> reader = new JdbcCursorItemReader<User>();
		//reader.setDataSource(createDataSource());
		reader.setDataSource(dataSource);
		reader.setSql("select firstname,lastname from people");
		reader.setRowMapper(new UserRowMapper());
		return reader;
		
	}
	
	public class UserRowMapper implements RowMapper<User>{
		@Override
		public User mapRow(ResultSet rs, int row) throws SQLException {
			User user = new User();
			user.setFirstname(rs.getString("firstname"));
			user.setLastname(rs.getString("lastname"));
			return user;
		}
	}*/
	
	/*public ItemReader reader(){
		Reader reader = new Reader();
		return reader;
	}*/
	
	/*public ItemWriter<User> writer(){
		Writer writer = new Writer();
		return writer.writer();
	}*/
	
	public Processor processor(){
		return new Processor();
	}
	
	/*@Bean
	public FlatFileItemWriter<User> writer(){
		FlatFileItemWriter<User> writer = new FlatFileItemWriter<User>();
		String exportFilePath = "C:\\eclipse\\users.dat";
		writer.setResource(new FileSystemResource(exportFilePath));
		writer.setLineAggregator(new DelimitedLineAggregator<User>() {{
			setDelimiter("|");
			setFieldExtractor(new BeanWrapperFieldExtractor<User>() {{
				setNames(new String[] {"firstname", "lastname"});				
			}});
		}});				
		writer.setLineSeparator("|".concat("\r\n"));
		return writer;
	}*/
	/*@Bean
	public FlatFileItemWriter<User> writer(){
		FlatFileItemWriter<User> writer = new FlatFileItemWriter<User>();
		String exportFilePath = "C:\\eclipse\\users.dat";
		writer.setResource(new FileSystemResource(exportFilePath));
		writer.setEncoding("UTF-8");
		writer.setLineAggregator(createUserLineAggregator());
		return writer;
		
	}
	
	
	public LineAggregator<User> createUserLineAggregator(){
		DelimitedLineAggregator<User> lineAggregator = new DelimitedLineAggregator<User>();
		lineAggregator.setDelimiter("|");
		FieldExtractor<User> extractor;
		extractor = createUserFieldExtractor();
		lineAggregator.setFieldExtractor(extractor);
		return lineAggregator;
				
	}
	
	public FieldExtractor<User> createUserFieldExtractor(){
		BeanWrapperFieldExtractor<User> fieldExtractor = new BeanWrapperFieldExtractor<User>();
		fieldExtractor.setNames(new String[] {"firstname","lastname"});
		return fieldExtractor;
	}
	
	*/
	
	@Bean
	public Step step1(){		
		return stepBuilder.get("step1").<User,User> chunk(10)
				.reader(reader.getItemReader())
				.processor(processor())
				.writer(writer.getItemWriter())
				.build();
	}
	@Bean
	public Job exportJob(){
		return jobBuilder.get("exportJob")
				.incrementer(new RunIdIncrementer())
				.flow(step1())
				.end()
				.build();
	}
}

