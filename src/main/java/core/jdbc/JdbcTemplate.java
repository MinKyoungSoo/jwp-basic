package core.jdbc;

import next.mapper.RowMapper;
import org.springframework.jdbc.core.PreparedStatementSetter;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class JdbcTemplate {

    public void update(PreparedStatementSetter pstmtSetter, String sql) throws SQLException {
        try(Connection con = ConnectionManager.getConnection();
            PreparedStatement pstmt = con.prepareStatement(sql)) {
            pstmtSetter.setValues(pstmt);

            pstmt.executeUpdate();
        }
    }

    public List query(PreparedStatementSetter pstmtSetter, RowMapper rowMapper, String sql) throws SQLException {
        try(Connection con = ConnectionManager.getConnection();
            PreparedStatement pstmt = con.prepareStatement(sql)) {
            pstmtSetter.setValues(pstmt);

            List values = new ArrayList();
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                values.add(rowMapper.mapRow(rs));
            }

            return values;
        }
    }

    public Object queryForObject(PreparedStatementSetter pstmtSetter, RowMapper rowmapperM, String sql) throws SQLException {
        List list = query(pstmtSetter, rowmapperM, sql);
        return list.isEmpty() ? null : list.get(0);
    }
}
