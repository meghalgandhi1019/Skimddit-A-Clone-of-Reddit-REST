package VoteDAO;

import java.sql.ResultSet;
import java.sql.SQLException;


import org.skife.jdbi.v2.StatementContext;
import org.skife.jdbi.v2.tweak.ResultSetMapper;


public class voteMapper implements ResultSetMapper<voteUser> {

	public voteUser map(int index, ResultSet r,StatementContext ctx)throws SQLException
    {return new voteUser(r.getString("username"));}
}
