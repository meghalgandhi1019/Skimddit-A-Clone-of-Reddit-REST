package UserDAO;
import org.skife.jdbi.v2.sqlobject.*;
import org.skife.jdbi.v2.sqlobject.customizers.Mapper;


public interface userDAO {
	
	
	
	@Mapper(userMapper.class)
	@SqlQuery("select * from users where id= :id")
	userData getContactById(@Bind("id") int id);
	
	@Mapper(userMapper.class)
	@SqlQuery("select * from users where username = :username and password =:password")
	userData getContactByUsername(@Bind("username") String username,@Bind("password") String password);
	
	@Mapper(userMapper.class)
	@SqlQuery("select * from users where username = :username")
	userData getContactByOnlyUsername(@Bind("username") String username);
	
	@GetGeneratedKeys
    @SqlUpdate("insert into users(username,password) values(:username,:password)")
    int createUser( @Bind("username") String username, @Bind("password") String password);
	
	@SqlUpdate("update users set id = :id, username = :username, password = :password where id= :id")
	void updateUser(@Bind("id") int id, @Bind("username") String username, @Bind("password") String password);
	
	@SqlUpdate("delete from users where id= :id")
	void deleteUser(@Bind("id") int id);
}


