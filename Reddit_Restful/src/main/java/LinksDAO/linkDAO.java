package LinksDAO;

import java.util.Iterator;

import org.skife.jdbi.v2.sqlobject.Bind;
import org.skife.jdbi.v2.sqlobject.GetGeneratedKeys;
import org.skife.jdbi.v2.sqlobject.SqlQuery;
import org.skife.jdbi.v2.sqlobject.SqlUpdate;
import org.skife.jdbi.v2.sqlobject.customizers.Mapper;
import org.skife.jdbi.v2.tweak.ResultSetMapper;

import UserDAO.userMapper;

public interface linkDAO {
	@Mapper(linkMapper.class)
	@SqlQuery("select * from links order by votes desc")
	Iterator<linkData> getAllLinks();
	
	@Mapper(linkMapper.class)
	@SqlQuery("select * from links where id= :id")
	linkData getLinkById(@Bind("id") int id);
	
	@GetGeneratedKeys
    @SqlUpdate("insert into links(name,url,username,votes) values(:name,:url,:username,:votes)")
    int createLink(@Bind("name") String name, @Bind("url") String url,@Bind("username") String username,@Bind("votes") int votes);
	
	@SqlUpdate("update links set id = :id,name = :name, url = :url, username = :username, votes = :votes  where id= :id")
	void updateLink(@Bind("id") int id, @Bind("name") String name, @Bind("url") String url,@Bind("username") String username,@Bind("votes") int votes);
	
	@SqlUpdate("delete from links where id= :id")
	void deleteLink(@Bind("id") int id);
}
