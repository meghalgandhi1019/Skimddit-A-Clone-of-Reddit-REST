package VoteDAO;
import org.skife.jdbi.v2.sqlobject.*;
import org.skife.jdbi.v2.sqlobject.customizers.Mapper;

import LinksDAO.linkData;
import UserDAO.userData;
import UserDAO.userMapper;
import LinksDAO.linkMapper;


public interface voteDAO {

	@Mapper(linkMapper.class)
	@SqlQuery("select * from links where id= :id")
	linkData getVotesById(@Bind("id") int id);
	
	
	@SqlUpdate("update links set votes = votes+1 where id= :id")
	void incVote(@Bind("id") int id);
	
	@SqlUpdate("update links set votes = votes-1 where id= :id")
	void decVote(@Bind("id") int id);
	
	@GetGeneratedKeys
    @SqlUpdate("insert into votes values(:linkname,:username)")
    int createvote(@Bind("linkname") String linkname, @Bind("username") String username);
	
	@GetGeneratedKeys
    @SqlUpdate("insert into votedown values(:linkname,:username)")
    int createvotedown(@Bind("linkname") String linkname, @Bind("username") String username);
	
	@Mapper(voteMapper.class)
	@SqlQuery("select username from votes where  username= :username and linkname= :linkname")
	voteUser getUserById(@Bind("username") String username,@Bind("linkname") String linkname);
	
	@Mapper(voteMapper.class)
	@SqlQuery("select username from votedown where  username= :username and linkname= :linkname")
	voteUser getUserForDownById(@Bind("username") String username,@Bind("linkname") String linkname);
	
	@SqlUpdate("delete from votedown where username= :username and linkname = :linkname")
	void deleteUserFromDown(@Bind("username") String username,@Bind("linkname") String linkname);
	
	@SqlUpdate("delete from votes where username= :username and linkname = :linkname")
	void deleteUserFromVotes(@Bind("username") String username,@Bind("linkname") String linkname);
	
}
