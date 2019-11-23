package com.phraseUp.phraseUpServer.dao;

import com.phraseUp.phraseUpServer.model.User;
import org.springframework.beans.factory.annotation.Autowired;
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
	public int insertUser(UUID id, User user) {
		final String sql = "INSERT INTO users (id, name) VALUES (?, ?);";
		return jdbcTemplate.update(sql, id, user.getName());
	}

	@Override
	public int deleteUserById(UUID id) {
		final String sql = "DELETE FROM users WHERE id = ?;";
		return jdbcTemplate.update(sql, id);
	}

	@Override
	public int updateUserById(UUID id, User user) {
		final String sql = "UPDATE users SET id = ?, name = ? WHERE id = ?";
		return jdbcTemplate.update(sql, user.getId(), user.getName(), id);
	}

	@Override
	public Optional<User> selectUserById(UUID id) {
		final String sql = "SELECT id, name FROM users WHERE id = ?";
		User user = jdbcTemplate.queryForObject(sql, new Object[]{id}, ((resultSet, i) -> {
					UUID userId = UUID.fromString(resultSet.getString("id"));
					String name = resultSet.getString("name");
					return new User(userId, name);
				}));
		return Optional.ofNullable(user);
	}

	@Override
	public List<User> selectAllUsers() {
		String sql = "SELECT id, name FROM users;";
		return jdbcTemplate.query(sql, (resultSet, i) -> {
			UUID id = UUID.fromString(resultSet.getString("id"));
			String name = resultSet.getString("name");
			return new User(id, name);
		});
	}
}
