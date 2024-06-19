package org.example1;

import java.util.ArrayList;
import java.util.List;

/*
Suponiendo que tenga una clase principal que quiera administrar a los usuarios, pero como solo debe haber
una sola entidad encargada de administrar a todos los usuarios se plantea usar singleton, ya que no importa
desde donde lo consuma siempre debe haber solo una entidad que los conozca a todos y los regule
* */
public final class UserManagerSingleton {

    /*
    Hay que ver que la variable que contiene la instancia global tambien esta declarada como "volatile".
    Eso significa que la variable se crea en la memoria principal (RAM) y no en las cache que suelen ser mas rapidos
    tanto para lectura como para escritura. Pero como el valor esta en la memoria principal, nos asegura que el valor
    este disponible para los multiples hilos y que depronto no vaya a quedar creada en un solo hilo y luego desde otro
    lo vuelva a crear. Sino que aqui le estamos dando como una visibilidad mas global.
    Es verdad que se ve un poco afectada la optimizacion al crear y leer, pero como el patron Singleton nos segura que solo
    se va a crear una intancia, entonces por ese lado no nos deberiamos preocupar ya que solo se vera afectada es la primera
    vez que entra a crear la instancia, de ahi en adelante no deberia haber mas problema
    * */
    private static volatile UserManagerSingleton instance;
    private List<String> users;

    private UserManagerSingleton() {
        users = new ArrayList<>();
    }

    /*Poner cuidado que aqui se esta usando synchronized, para poder sincronizar a los hilos en caso de trabajar con
    * multihilo. Ejemplo, Si no se sincroniza entonce el Hilo A puede ingresar por primera vez y como aun no se ha creado una
    * intancia, este hilo la habra de crear. Pero a su vez el hilo B va a entrar y crear tambien una instancia
    * lo cual no es correcto porque ya no estariamos asegurando una unica instancia a nivel global en el codigo.
    * Pero esta forma de sincronizar los hilos puede ser un poco ineficiente a comparacion de la que voy a colocar en el ejemplo 2*/
    public static synchronized UserManagerSingleton getInstance() {
        if (instance == null) {
            instance = new UserManagerSingleton();
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
