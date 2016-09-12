package UserDAO;
import java.sql.ResultSet;
import java.sql.SQLException;


import org.skife.jdbi.v2.StatementContext;
import org.skife.jdbi.v2.tweak.ResultSetMapper;

public class userMapper implements ResultSetMapper<userData>
{

	public userData map(int index, ResultSet r,StatementContext ctx)throws SQLException
    {
		
		return new userData(r.getString("username"),r.getString("password"));}

}
