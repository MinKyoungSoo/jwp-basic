package next.dao;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import core.jdbc.JdbcTemplate;
import next.mapper.RowMapper;
import next.mapper.UserRowMapper;
import next.model.User;

public class UserDao {
    
    RowMapper rowMapper = new UserRowMapper();
    
    public void insert(User user) throws SQLException {
        JdbcTemplate jdbcTemplate = new JdbcTemplate();

        jdbcTemplate.update(
                pstmt -> {
                            pstmt.setString(1, user.getUserId());
                            pstmt.setString(2, user.getPassword());
                            pstmt.setString(3, user.getName());
                            pstmt.setString(4, user.getEmail());
                }, 
                "INSERT INTO USERS VALUES (?, ?, ?, ?)");
    }

    public void update(User user) throws SQLException {
        JdbcTemplate jdbcTemplate = new JdbcTemplate();

        jdbcTemplate.update(
                pstmt -> {
                            pstmt.setString(1, user.getPassword());
                            pstmt.setString(2, user.getName());
                            pstmt.setString(3, user.getEmail());
                            pstmt.setString(4, user.getUserId());
                }, 
                "UPDATE USERS set password = ?, name = ?, email = ? WHERE userId = ?");
    }

    public List<User> findAll() throws SQLException {

        JdbcTemplate jdbcTemplate = new JdbcTemplate();

        return (List<User>)jdbcTemplate.query(
                pstmt -> {}, 
                rowMapper, 
                "SELECT userId, password, name, email FROM USERS");
    }

    public User findByUserId(String userId) throws SQLException {
        JdbcTemplate jdbcTemplate = new JdbcTemplate();

        return (User)jdbcTemplate.queryForObject(
                pstmt -> pstmt.setString(1, userId), 
                rowMapper, 
                "SELECT userId, password, name, email FROM USERS WHERE userid=?");
    }

}
