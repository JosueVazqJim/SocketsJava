package ejercicio_sockets_tcp_ddr_1;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class Cliente {
    public static void main(String[] args) {
        final String HOST = "127.0.0.1"; //ip del servidor, por ahora solo es local
        final int PUERTO = 5000; //puerto de comunicación inmutable, debe de coincidir con el puerto del servidor
        DataInputStream in;
        DataOutputStream out;

        try {
            Socket sc = new Socket(HOST, PUERTO); //objeto Socket que viene en la biblioteca de java.net, es el socket del cliente
            in = new DataInputStream(sc.getInputStream()); //creamos un flujo de entrada para recibir los datos que envía el servidor
            out = new DataOutputStream(sc.getOutputStream()); //creamos un flujo de salida para enviar datos al servidor

            out.writeUTF("hola mundo desde el cliente"); //enviamos un mensaje al servidor

            String mensaje = in.readUTF(); //lo que hace es quedar a la espera de que el servidor envíe un mensaje
            System.out.println(mensaje); //imprimimos el mensaje que envía el servidor

            sc.close(); //cerramos el socket cliente


        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
