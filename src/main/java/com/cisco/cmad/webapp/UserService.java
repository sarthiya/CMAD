package com.cisco.cmad.webapp;

import java.util.List;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import com.cisco.cmad.dao.UserDao;
import com.cisco.cmad.webapp.Users;

@Path("/user")
public class UserService {

	UserDao dao = new UserDao();
	
	public void setUserDao(UserDao dao){
		this.dao = dao;
	}
	@GET
	@Path("/{param}")
	@Produces({MediaType.APPLICATION_JSON})
	public Users getUser(@PathParam("param") Integer id) {
		return dao.getUser(id);
	}
	
	@GET
	@Produces({MediaType.APPLICATION_JSON})
	public List<Users> getUsers() {
		return dao.getUsers();
	}
	
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	//public void createUser(@FormParam("name") String name,@FormParam("age") Integer age,@FormParam("emailId") String emailId){
	public void createUser(Users u){
		System.out.println("Creating user: "+u.getName()+" Age: "+u.getAge());
		Session ses = HibernateUtil.currentSession();
		try {
			Transaction tx = ses.beginTransaction();
			ses.save(u);
			tx.commit();
		}finally{
			HibernateUtil.closeSession();
		}
	}
	
	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	//@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	//public void updateUser(@FormParam("id") Integer id, @FormParam("name") String name,@FormParam("age") Integer age,@FormParam("emailId") String emailId){
	public void updateUser(Users u){
		System.out.println("Updating user: "+u.getName());
		Session ses = HibernateUtil.currentSession();
		try {
			Transaction tx = ses.beginTransaction();
			ses.update(u);
			tx.commit();
		}finally{
			HibernateUtil.closeSession();
		}
	}
	
	@DELETE
	@Path("/{param}")
	@Produces({MediaType.APPLICATION_JSON})
	public boolean deleteUser(@PathParam("param") Integer id) {
		System.out.println("Deleting user: "+id);
		Session ses = HibernateUtil.currentSession();
		try {
			Transaction tx = ses.beginTransaction();
			Users u = (Users) ses.load(Users.class, id);
			ses.delete(u);
			tx.commit();
			return true;
		} finally {
			HibernateUtil.closeSession();
		}
	}
	
}


