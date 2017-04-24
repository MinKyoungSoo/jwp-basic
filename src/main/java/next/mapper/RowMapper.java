package next.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by minkyoungsoo on 2017. 4. 20..
 */
public interface RowMapper {

    Object mapRow(ResultSet rs) throws SQLException;
    
}
