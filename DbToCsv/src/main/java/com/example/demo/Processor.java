package com.example.demo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemProcessor;

public class Processor implements ItemProcessor<User, User> {
	private static final Logger log = LoggerFactory.getLogger(Processor.class);

	@Override
	public User process(User item) throws Exception {
		final String firstname = item.getFirstname().toUpperCase();
		final String lastname = item.getLastname().toUpperCase();
		final User name = new User(firstname, lastname);
		log.info("Converting (" + item + ") to (" + name + "");
		return name;
	}
}
