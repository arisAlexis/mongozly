package org.mongozly.server.rest.nio;

import javax.ws.rs.*;
import static javax.ws.rs.core.MediaType.*;
import javax.ws.rs.core.Response;


/**
 *
 * @author aris
 */
@Path("users")
public class UsersEndpoint {

    private final UserDAO userDAO;
    
    public UsersEndpoint () {
        userDAO = new UserDAO(MongoBean.getInstance().getMorphia(),MongoBean.getInstance().getConnection(),"mongozly");
    }


    @GET @Path("{userName}")
    @Produces(APPLICATION_JSON)
    public Response getUser(@PathParam("userName") String userName) {
        User user=userDAO.findUser(userName);
        if (user==null)
            return Response.status(Response.Status.NOT_FOUND).build();
        return Response.ok(user).build();        
    }

    @POST
    @Consumes(APPLICATION_JSON)
    public void addUser(User user) {  
        userDAO.addUser(user);
    }   
    
    /**
     * delete user
     * @param userName 
     */

    @DELETE @Path("{userName}")
    public void deleteUser(@PathParam("userName") String userName) {                
      
    }
    
    /**
     * get users by city with optional paginator
     * @param city
     * @param startAt
     * @param limit
     * @return 
     */

    @GET @Path("/byCity/{city}")
    @Produces(APPLICATION_JSON) 
    public String getUsersByCity(@PathParam("city") String city,@QueryParam("startAt") int startAt,@QueryParam("limit") int limit) {        
        return null;
    }
    
    /**
     * get all users with paginator
     * @param startAt
     * @param limit
     * @return 
     */
    
    
    @GET
    @Produces(APPLICATION_JSON)
    public String getUsers(@QueryParam("startAt") int startAt,@QueryParam("limit") int limit) {
        return "all users";
    }
    
}
