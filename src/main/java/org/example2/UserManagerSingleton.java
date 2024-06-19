package org.example2;

import java.util.ArrayList;
import java.util.List;

public final class UserManagerSingleton {

    private static volatile UserManagerSingleton instance;
    private List<String> users;

    private UserManagerSingleton() {
        users = new ArrayList<>();
    }

    /*De esta manera es mas optimo ya que de entrada el metodo no usa syncronized, sino que primero entra y verifica
    * si ya existe una instancia, solo en caso de que no exista una instancia es que va crear de manera sincronizada
    * la instancia de esta clase, pero entonces se vuelve mas optimo porque ya no es necesario que sincronice los hilos
    * cada vez que quiere obtener una instancia ya previamente creada*/
    public static UserManagerSingleton getInstance() {
        if (instance == null) {
            synchronized (UserManagerSingleton.class) {
                if (instance == null) {
                    instance = new UserManagerSingleton();
                }
            }
        }
        return instance;
    }

    public void addUser(String user) {
        this.users.add(user);
        System.out.println("user added: " + user);
    }

    public List<String> getUsers() {
        return this.users;
    }

}
