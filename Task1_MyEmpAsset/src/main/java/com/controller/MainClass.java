package com.controller;
import com.config.HibernateConfig;
import com.enums.Role;
import com.exception.ResourceNotFoundException;
import com.model.User;
import com.service.UserService;
import org.hibernate.Session;
import java.util.List;
import java.util.Scanner;
public class MainClass {
    public static void main(String[] args) {
        Session session = HibernateConfig.getSessionFactory().openSession();
        Scanner sc = new Scanner(System.in);
        UserService userService = new UserService(session);
        while(true) {
            System.out.println("1. Add User");
            System.out.println("2. Delete User by ID");
            System.out.println("3. Fetch all Users");
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
                    userService.insert(user);
                    System.out.println("User Added");
                    break;
                case 2:
                    System.out.println("Enter User ID");
                    int id = sc.nextInt();
                    try {
                        userService.deleteRecord(id);
                        System.out.println("User deleted");
                    }
                    catch(ResourceNotFoundException e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case 3:
                    System.out.println("===== USERS =====");
                    List<User> list = userService.getAllUsers();
                    list.forEach(System.out::println);
                    break;
                case 4:
                    System.out.println("Enter User ID");
                    id = sc.nextInt();
                    try {
                        user = userService.getById(id);
                        sc.nextLine();
                        System.out.println("Enter New Username");
                        user.setUsername(sc.nextLine());
                        System.out.println("Enter New Password");
                        user.setPassword(sc.nextLine());
                        System.out.println("Enter New Email");
                        user.setEmail(sc.nextLine());
                        System.out.println("Enter New Role");
                        user.setRole(Role.valueOf(sc.next().toUpperCase()));
                        userService.update(user);
                        System.out.println("User Updated");
                    }
                    catch(ResourceNotFoundException e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                default:
                    System.out.println("Invalid Option");
            }
        }
        sc.close();
        session.close();
    }
}
