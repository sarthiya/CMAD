package com.cisco.cmad.dao;


import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import com.cisco.cmad.webapp.Users;
import com.cisco.cmad.webapp.HibernateUtil;

public class UserDao {


	public Users getUser( Integer id) {
		Session ses = HibernateUtil.currentSession();
		try {
			Criteria crit =  ses.createCriteria(Users.class);
			crit.add(Restrictions.idEq(id));
			Users u = (Users)crit.uniqueResult();
			return u;
		} finally {
			HibernateUtil.closeSession();
		}
	}

	public List<Users> getUsers() {
		Session ses = HibernateUtil.currentSession();
		try {
			return ses.createCriteria(Users.class).list();
		} finally {
			HibernateUtil.closeSession();
		}
	}
	
	
}



