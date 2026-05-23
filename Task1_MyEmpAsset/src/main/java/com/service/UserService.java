package com.service;
import com.exception.ResourceNotFoundException;
import com.model.User;
import org.hibernate.Session;
import org.hibernate.Transaction;
import java.util.List;
public class UserService {
    Session session;
    public UserService(Session session) {
        this.session = session;
    }
    public void insert(User user) {
        Transaction tx = session.beginTransaction();
        session.persist(user);
        tx.commit();
    }
    public void deleteRecord(int id) throws ResourceNotFoundException {
        User user = session.find(User.class,id);
        if(user == null)
            throw new ResourceNotFoundException("User ID Invalid");
        Transaction tx = session.beginTransaction();
        session.remove(user);
        tx.commit();
    }
    public List<User> getAllUsers() {
        return session.createQuery("from User",User.class).list();
    }
    public User getById(int id) throws ResourceNotFoundException {
        User user = session.find(User.class,id);
        if(user == null)
            throw new ResourceNotFoundException("User ID Invalid");
        return user;
    }
    public void update(User user) {
        Transaction tx = session.beginTransaction();
        session.merge(user);
        tx.commit();
    }
}
