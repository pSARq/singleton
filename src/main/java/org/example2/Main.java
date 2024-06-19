package org.example2;

public class Main {
    public static void main(String[] args) {
        Thread firstThread = new Thread(() -> {
            UserManagerSingleton userManagerSingleton = UserManagerSingleton.getInstance();
            userManagerSingleton.addUser("Alice");
        });

        Thread secondThread = new Thread(() -> {
            UserManagerSingleton userManagerSingleton = UserManagerSingleton.getInstance();
            userManagerSingleton.addUser("Bob");
        });

        firstThread.start();
        secondThread.start();

        UserManagerSingleton userManagerSingleton = UserManagerSingleton.getInstance();
        System.out.println("Users = " + userManagerSingleton.getUsers());

    }
}