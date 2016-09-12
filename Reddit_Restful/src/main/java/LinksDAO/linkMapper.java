package LinksDAO;
import java.sql.ResultSet;
import java.sql.SQLException;



import java.util.Iterator;

import org.skife.jdbi.v2.StatementContext;
import org.skife.jdbi.v2.tweak.ResultSetMapper;

import UserDAO.userData;



public class linkMapper  implements ResultSetMapper<linkData>{
	public linkData map(int index, ResultSet r,StatementContext ctx)throws SQLException
    {
		
	
		return new linkData(r.getInt("id"), r.getString("name"),r.getString("url"),r.getString("username"),r.getInt("votes"));
		
    }
	
	

}
