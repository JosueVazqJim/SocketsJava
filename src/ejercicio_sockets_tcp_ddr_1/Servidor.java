package ejercicio_sockets_tcp_ddr_1;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Servidor {
    public static void main(String[] args) {
        ServerSocket servidor = null; //objeto ServerSocket que viene en la blioteca de java.net , es el socket del servidor
        Socket sc = null; //objeto Socket que viene en la biblioteca de java.net, es el socket del cliente
        DataInputStream in;
        DataOutputStream out;
        final int PUERTO = 5000; //puerto de comunicación inmutable

        try {
            servidor = new ServerSocket(PUERTO); //creamos un servidor en el puerto 5000
            System.out.println("Servidor iniciado");

            while (true) { //mientras el servidor esté corriendo, siempre esta escuchando
                sc = servidor.accept(); //aceptamos un cliente, lo que hace es que se queda esperando a que un cliente se conecte

                System.out.println("Cliente conectado");
                in = new DataInputStream(sc.getInputStream()); //creamos un flujo de entrada para recibir los datos que envía el cliente
                out = new DataOutputStream(sc.getOutputStream()); //creamos un flujo de salida para enviar datos al cliente

                String mensaje = in.readUTF(); //lo que hace es quedar a la espera de que el cliente envíe un mensaje

                System.out.println(mensaje); //imprimimos el mensaje que envía el cliente
                out.writeUTF("hola mundo desde el server");

                sc.close(); //cerramos el socket cliente
                System.out.println("Cliente desconectado");
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
