package com.phraseUp.phraseUpServer.service;

import com.phraseUp.phraseUpServer.model.LogInData;
import com.phraseUp.phraseUpServer.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository("userDao")
public class UserDataAccessService implements UserDao {

	private final JdbcTemplate jdbcTemplate;

	@Autowired
	public UserDataAccessService(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	@Override
	public int insertUser(UUID id, LogInData log) {
		final String sql = "INSERT INTO users (id, username, password) VALUES (?, ?, ?);";
		return jdbcTemplate.update(sql, id, log.getUsername(), log.getPassword());
	}

	@Override
	public int deleteUserById(UUID id) {
		final String sql = "DELETE FROM users WHERE id = ?;";
		return jdbcTemplate.update(sql, id);
	}

	@Override
	public int updateUserById(UUID id, User user) {
		final String sql = "UPDATE users SET id = ?, username = ? WHERE id = ?";
		return jdbcTemplate.update(sql, user.getId(), user.getUsername(), id);
	}

	@Override
	public Optional<User> selectUserByUsername(String username) {
		final String sql = "SELECT id, username FROM users WHERE username = ?";

		User user = null;
		try {
			user = jdbcTemplate.queryForObject(sql, new Object[]{username}, (((resultSet, i) -> {
				UUID userId = UUID.fromString(resultSet.getString("id"));
				String name = resultSet.getString("username");
				return new User(userId, name);
			})));
		} catch (EmptyResultDataAccessException ignored) {
		}
		return Optional.ofNullable(user);
	}

	@Override
	public Optional<User> selectUserById(UUID id) {
		final String sql = "SELECT id, username FROM users WHERE id = ?";

		User user = null;
		try {
			user = jdbcTemplate.queryForObject(sql, new Object[]{id}, ((resultSet, i) -> {
				UUID userId = UUID.fromString(resultSet.getString("id"));
				String name = resultSet.getString("username");
				return new User(userId, name);
			}));
		} catch (EmptyResultDataAccessException e) {
			System.out.println(e.getMessage());
		}
		return Optional.ofNullable(user);
	}

	@Override
	public List<User> selectAllUsers() {
		String sql = "SELECT id, username FROM users;";
		return jdbcTemplate.query(sql, (resultSet, i) -> {
			UUID id = UUID.fromString(resultSet.getString("id"));
			String name = resultSet.getString("username");
			return new User(id, name);
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
