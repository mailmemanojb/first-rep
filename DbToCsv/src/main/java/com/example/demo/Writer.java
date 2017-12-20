package com.example.demo;

import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.file.FlatFileItemWriter;
import org.springframework.batch.item.file.transform.BeanWrapperFieldExtractor;
import org.springframework.batch.item.file.transform.DelimitedLineAggregator;
import org.springframework.batch.item.file.transform.FieldExtractor;
import org.springframework.batch.item.file.transform.LineAggregator;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.io.FileSystemResource;
import org.springframework.stereotype.Component;
@PropertySource("classpath:application.properties")
@Component
public class Writer  {
	
	@Value("${export.file.path}")
	String exportFilePath;
	
	private FlatFileItemWriter<User> itemWriter;
	
	
	public ItemWriter<User> getItemWriter() {		
		if(itemWriter == null){
			itemWriter = new FlatFileItemWriter<>();
			itemWriter.setResource(new FileSystemResource(exportFilePath));
			itemWriter.setEncoding("UTF-8");
			itemWriter.setLineAggregator(createUserLineAggregator());
		}		
		return itemWriter;
	}

	
	 
	
	/*@Bean	
	public void write() {
		FlatFileItemWriter<User> writer = new FlatFileItemWriter<User>();
		String exportFilePath = "C:\\eclipse\\sample.dat";
		writer.setResource(new FileSystemResource(exportFilePath));
		writer.setEncoding("UTF-8");
		writer.setLineAggregator(createUserLineAggregator());		
	}*/
	/*@Bean
	public FlatFileItemWriter<User> writer(){
		System.out.println("Writer");
		FlatFileItemWriter<User> writer = new FlatFileItemWriter<User>();
		
		//writer.setResource(new FileSystemResource(exportFilePath));
		writer.setResource();
		writer.setEncoding();
		
		return writer;
		
	}*/
	
	
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

/*@Bean
public FlatFileItemWriter<User> writer(){
	FlatFileItemWriter<User> writer = new FlatFileItemWriter<User>();
	String exportFilePath = "C:\\Users\\manoj.bhandari\\Desktop\\sample.dat";
	writer.setResource(new FileSystemResource(exportFilePath));
	writer.setLineAggregator(new DelimitedLineAggregator<User>() {{
		setDelimiter("|");
		setFieldExtractor(new BeanWrapperFieldExtractor<User>() {{
			setNames(new String[] {"firstname", "lastname"});
		}});
	}});
	//writer.setLineSeparator("|".concat("\r\n"));
	return writer; }*/

}
