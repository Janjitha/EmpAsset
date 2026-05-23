package com;

import com.config.AppConfig;
import com.dao.UserDAO;
import com.model.User;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;
import java.util.List;
import java.util.Scanner;

public class MainApp {

    public static void main(String[] args) {

        ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
        JdbcTemplate jdbcTemplate = context.getBean(JdbcTemplate.class);
        UserDAO userDAO = new UserDAO(jdbcTemplate);
        Scanner sc = new Scanner(System.in);

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
                    user.setRole(sc.nextLine());
                    userDAO.insert(user);
                    break;
                case 2:
                    System.out.println("Enter ID");
                    int id = sc.nextInt();
                    userDAO.delete(id);
                    break;
                case 3:
                    List<User> users = userDAO.getAllUsers();
                    users.forEach(System.out::println);
                    break;
                case 4:
                    User updateUser = new User();
                    System.out.println("Enter ID");
                    updateUser.setId(sc.nextInt());
                    sc.nextLine();
                    System.out.println("Enter Username");
                    updateUser.setUsername(sc.nextLine());
                    System.out.println("Enter Password");
                    updateUser.setPassword(sc.nextLine());
                    System.out.println("Enter Email");
                    updateUser.setEmail(sc.nextLine());
                    System.out.println("Enter Role");
                    updateUser.setRole(sc.nextLine());
                    userDAO.update(updateUser);
                    break;
                default:
                    System.out.println("Invalid Option");
            }
        }
    }
}