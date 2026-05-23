package com.app.dao_impl;

import com.app.dao.UserDao;
import com.app.model.User;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.util.List;

public class UserDaoImpl implements UserDao {

    SessionFactory sessionFactory;
    public UserDaoImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void insert(User user) {
        Session session = sessionFactory.openSession();
        Transaction tx = session.beginTransaction();
        session.persist(user);
        tx.commit();
        session.close();
        System.out.println("User Inserted");
    }

    @Override
    public void delete(int id) {
        Session session = sessionFactory.openSession();
        Transaction tx = session.beginTransaction();
        User user = session.find(User.class,id);
        session.remove(user);
        tx.commit();
        session.close();
        System.out.println("User Deleted");
    }

    @Override
    public void update(User user) {
        Session session = sessionFactory.openSession();
        Transaction tx = session.beginTransaction();
        session.merge(user);
        tx.commit();
        session.close();
        System.out.println("User Updated");
    }

    @Override
    public List<User> getAllUsers() {
        Session session = sessionFactory.openSession();
        return session.createQuery("from User", User.class).list();
    }

    @Override
    public User getById(int id) {
        Session session = sessionFactory.openSession();
        return session.find(User.class, id);
    }
}