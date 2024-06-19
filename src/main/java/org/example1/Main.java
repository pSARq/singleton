package org.example1;

public class Main {
    public static void main(String[] args) {
        /*En este caso la prueba lo voy a hacer con hilos, pero tambien se puede hacer normal, es decir, sin manejar hilos*/
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