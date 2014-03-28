package org.mongozly.server.rest.nio;

import com.mongodb.Mongo;
import org.bson.types.ObjectId;
import org.mongodb.morphia.Key;
import org.mongodb.morphia.Morphia;
import org.mongodb.morphia.dao.BasicDAO;
import org.mongodb.morphia.query.Query;

/**
 *
 * @author aris
 */

public class UserDAO extends BasicDAO<User,ObjectId> {

    public UserDAO( Morphia morphia, Mongo mongo ,String dbName) {
        super(mongo, morphia, dbName);
    }

    public boolean addUser(User user) {
        Key<User> userKey=save(user);
        //TODO validation
        return userKey != null;         
    }

    public void deleteUser(String userName) {
        Query q = ds.createQuery(User.class).filter("userName", userName);
        deleteByQuery(q);
    }

    public User findUser(String userName) {
        Query q = ds.createQuery(User.class).filter("userName", userName);
        User u=find(q).get();
        return u;
    }

}
