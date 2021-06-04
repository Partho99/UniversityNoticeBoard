package org.classic.spring.web.test.tests;

import static org.junit.Assert.*;

import javax.sql.DataSource;

import org.classic.spring.web.dao.User;
import org.classic.spring.web.dao.UsersDao;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@ActiveProfiles("dev")
@ContextConfiguration(locations = { "classpath:org/classic/spring/web/config/dao-context.xml",
		"classpath:org/classic/spring/web/config/security-context.xml",
		"classpath:org/classic/spring/web/test/config/datasource.xml" })
@RunWith(SpringJUnit4ClassRunner.class)
public class UserDaoTest {

	@Autowired
	private UsersDao usersDao;
	@Autowired
	private DataSource dataSource;

	@Before
	public void init() {
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);

		jdbcTemplate.execute("delete from users");
		jdbcTemplate.execute("delete from authorities");
	}

	@Test
	public void testCreateUser() {

		User user = new User("shreyo", "123456", "shreyo@gmail.com", true, "ROLE_ADMIN");

		assertTrue("User creation must return TRUE", usersDao.createUser(user));
	}

}
