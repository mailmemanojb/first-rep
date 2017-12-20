package com.example.demo;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.core.RowMapper;

	public class UserRowMapper implements RowMapper<User>{
		@Bean
		@Override
		public User mapRow(ResultSet rs, int row) throws SQLException {
			User user = new User();
			user.setFirstname(rs.getString("firstname"));
			user.setLastname(rs.getString("lastname"));
			return user;
		}
	}

