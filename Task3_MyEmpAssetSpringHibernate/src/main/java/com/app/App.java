package com.app;

import com.app.config.AppConfig;
import com.app.dao_impl.UserDaoImpl;
import com.app.enums.Role;
import com.app.model.User;
import org.hibernate.SessionFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import java.util.List;
import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
        SessionFactory sessionFactory = context.getBean(SessionFactory.class);
        UserDaoImpl userDao = new UserDaoImpl(sessionFactory);
        Scanner sc = new Scanner(System.in);
        System.out.println("===== LOGIN =====");
        System.out.println("Enter Username");
        String username = sc.next();
        System.out.println("Enter Password");
        String password = sc.next();
        if(username.equals("admin") && password.equals("admin123")) {
            while(true) {
                System.out.println("1. Add User");
                System.out.println("2. Delete User");
                System.out.println("3. Fetch All Users");
                System.out.println("4. Update User");
                System.out.println("0. Exit");
                int op = sc.nextInt();
                if(op == 0)
                    break;
                switch(op) {
                    case 1:
                        User user = new User();
                        sc.nextLine();
                        System.out.println("Enter Username");
                        user.setUsername(sc.nextLine());
                        System.out.println("Enter Password");
                        user.setPassword(sc.nextLine());
                        System.out.println("Enter Email");
                        user.setEmail(sc.nextLine());
                        System.out.println("Enter Role");
                        user.setRole(Role.valueOf(sc.next().toUpperCase()));
                        userDao.insert(user);
                        break;
                    case 2:
                        System.out.println("Enter User ID");
                        int id = sc.nextInt();
                        userDao.delete(id);
                        break;
                    case 3:
                        List<User> users = userDao.getAllUsers();
                        users.forEach(System.out::println);
                        break;
                    case 4:
                        System.out.println("Enter User ID");
                        id = sc.nextInt();
                        User updateUser = userDao.getById(id);
                        sc.nextLine();
                        System.out.println("Enter Username");
                        updateUser.setUsername(sc.nextLine());
                        System.out.println("Enter Password");
                        updateUser.setPassword(sc.nextLine());
                        System.out.println("Enter Email");
                        updateUser.setEmail(sc.nextLine());
                        System.out.println("Enter Role");
                        updateUser.setRole(Role.valueOf(sc.next().toUpperCase()));
                        userDao.update(updateUser);
                        break;
                }
            }
        }
        else {
            System.out.println("Invalid Credentials");
        }
    }
}