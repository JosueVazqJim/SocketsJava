package ejercicio_sockets_ddr_3;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Observable;

public class Servidor extends Observable implements Runnable { //aplicamos observable y tambien lo hacemos como un hilo
    //los observables estan pendientes de cambios y si los hay, se notifica

    private int puerto;
    public Servidor(int puerto){
        this.puerto = puerto;
    }

    @Override
    public void run() {
        ServerSocket servidor = null; //objeto ServerSocket que viene en la blioteca de java.net , es el socket del servidor
        Socket sc = null; //objeto Socket que viene en la biblioteca de java.net, es el socket del cliente
        DataInputStream in;
        DataOutputStream out;

        try {
            servidor = new ServerSocket(puerto); //creamos un servidor en el puerto 5000
            System.out.println("Servidor iniciado");

            while (true) { //mientras el servidor esté corriendo, siempre esta escuchando
                sc = servidor.accept(); //aceptamos un cliente, lo que hace es que se queda esperando a que un cliente se conecte

                System.out.println("Cliente conectado");
                in = new DataInputStream(sc.getInputStream()); //creamos un flujo de entrada para recibir los datos que envía el cliente

                String mensaje = in.readUTF(); //lo que hace es quedar a la espera de que el cliente envíe un mensaje

                System.out.println(mensaje); //imprimimos el mensaje que envía el cliente

                this.setChanged(); //cambiamos el estado del observable
                this.notifyObservers(mensaje); //notificamos a los observadores que hay un cambio
                this.clearChanged(); //limpiamos el estado del observable

                sc.close(); //cerramos el socket cliente
                System.out.println("Cliente desconectado");
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
