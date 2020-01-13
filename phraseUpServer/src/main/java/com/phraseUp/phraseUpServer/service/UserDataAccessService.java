package com.phraseUp.phraseUpServer.service;

import com.phraseUp.phraseUpServer.model.Language;
import com.phraseUp.phraseUpServer.model.LogInData;
import com.phraseUp.phraseUpServer.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository("userDao")
public class UserDataAccessService implements UserDao {

	private final JdbcTemplate jdbcTemplate;

	@Autowired
	public UserDataAccessService(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	@Override
	public int insertUser(User user) {
		final String sql = "INSERT INTO users (username, password, language) VALUES (?, ?, ?);";
		return jdbcTemplate.update(sql, user.getUsername(), user.getPassword(), user.getLanguage().toString());
	}

	@Override
	public int deleteUserByUsername(String username) {
		final String sql = "DELETE FROM users WHERE username = ?;";
		return jdbcTemplate.update(sql, username);
	}

	@Override
	public int updateUserByUsername(String username, User user) {
		final String sql = "UPDATE users SET username = ?, password = ? WHERE username = ?";
		return jdbcTemplate.update(sql, user.getUsername(), user.getPassword(), username);
	}

	@Override
	public Optional<User> selectUserByUsername(String username) {
		final String sql = "SELECT * FROM users WHERE username = ?";

		User user = null;
		try {
			user = jdbcTemplate.queryForObject(sql, new Object[]{username}, (((resultSet, i) -> {
				String name = resultSet.getString("username");
				String password = resultSet.getString("password");
				Language language = Language.fromString(resultSet.getString("language"));
				return new User(name, password, language);
			})));
		} catch (EmptyResultDataAccessException ignored) {
		}
		return Optional.ofNullable(user);
	}

	@Override
	public List<User> selectAllUsers() {
		String sql = "SELECT * FROM users;";
		return jdbcTemplate.query(sql, (resultSet, i) -> {
			String name = resultSet.getString("username");
			String password = resultSet.getString("password");
			Language language = Language.fromString(resultSet.getString("language"));
			return new User(name, password, language);
		});
	}

	@Override
	public Boolean checkLogs(LogInData log) {
		final String sql = "SELECT username, password FROM users WHERE username = ? AND password = ?";
		String username = log.getUsername();
		String password = log.getPassword();

		List<LogInData> correspondingLogs = jdbcTemplate.query(sql, new Object[]{username, password},
				(resultSet, i) -> {
					String tmpUsername = resultSet.getString("username");
					String tmpPassword = resultSet.getString("password");
					return new LogInData(tmpUsername, tmpPassword);
				});

		return !correspondingLogs.isEmpty();
	}
}
