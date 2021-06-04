package org.classic.spring.web.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSourceUtils;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Component("noticesDao")
public class NoticesDao {

	private NamedParameterJdbcTemplate jdbcTemplate;

	@Autowired
	private void setDataSource(DataSource dataSource) {
		this.jdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
	}

	public List<Notice> getNotices() {

		return jdbcTemplate.query("select * from notices", new RowMapper<Notice>() {

			@Override
			public Notice mapRow(ResultSet rs, int rowNum) throws SQLException {
				Notice notice = new Notice();

				notice.setId(rs.getInt("id"));
				notice.setName(rs.getString("name"));
				notice.setEmail(rs.getString("email"));
				notice.setText(rs.getString("text"));

				return notice;
			}

		});
	}

	public Notice getNotice(int id) {

		MapSqlParameterSource params = new MapSqlParameterSource();
		params.addValue("id", id);

		return jdbcTemplate.queryForObject("select * from notices where id=:id", params, new RowMapper<Notice>() {

			@Override
			public Notice mapRow(ResultSet rs, int rowNum) throws SQLException {
				Notice notice = new Notice();

				notice.setId(rs.getInt("id"));
				notice.setName(rs.getString("name"));
				notice.setEmail(rs.getString("email"));
				notice.setText(rs.getString("text"));

				return notice;
			}

		});
	}

	public boolean delete(int id) {

		MapSqlParameterSource params = new MapSqlParameterSource();
		params.addValue("id", id);

		return jdbcTemplate.update("delete from notices where id=:id", params) == 1;

	}

	public boolean createNotice(Notice notice) {

		BeanPropertySqlParameterSource params = new BeanPropertySqlParameterSource(notice);

		return jdbcTemplate.update("insert into notices (name, email, text) values (:name, :email, :text)",
				params) == 1;
	}

	public boolean updateNotice(Notice notice) {

		BeanPropertySqlParameterSource params = new BeanPropertySqlParameterSource(notice);

		return jdbcTemplate.update("update notices set name=:name, email=:email, text=:text where id=:id", params) == 1;
	}

	public int[] createBatch(List<Notice> notices) {

		SqlParameterSource[] params = SqlParameterSourceUtils.createBatch(notices.toArray());

		return jdbcTemplate
				.batchUpdate("insert into notices (id, name, email, text) values (:id, :name, :email, :text)", params);

	}

}
