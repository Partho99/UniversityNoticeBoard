package org.classic.spring.web.dao;

import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Component("usersDao")
public class UsersDao {

	private NamedParameterJdbcTemplate jdbcTemplate;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	private void setDataSource(DataSource dataSource) {
		this.jdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
	}

	public boolean createUser(User user) {

		MapSqlParameterSource params = new MapSqlParameterSource();

		params.addValue("username", user.getUsername());
		params.addValue("password", passwordEncoder.encode(user.getPassword()));
		params.addValue("email", user.getEmail());
		params.addValue("enabled", user.isEnabled());
		params.addValue("authority", user.getAuthority());

//		BeanPropertySqlParameterSource params = new BeanPropertySqlParameterSource(user);

		jdbcTemplate.update(
				"insert into users (username, password, email, enabled) values (:username, :password, :email, :enabled)",
				params);

		return jdbcTemplate.update("insert into authorities (username, authority) values (:username, :authority)",
				params) == 1;
	}

	public boolean exists(String username) {
		return jdbcTemplate.queryForObject("select count(*) from users where username=:username",
				new MapSqlParameterSource("username", username), Integer.class) > 0;

	}

	public List<User> getAllUsers() {
		return jdbcTemplate.query("select * from users , authorities where users.username=authorities.username",
				BeanPropertyRowMapper.newInstance(User.class));

	}

}
