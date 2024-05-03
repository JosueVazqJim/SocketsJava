package ejercicio_sockets_udp_ddr_2;

import java.io.IOException;
import java.net.*;

public class Cliente {
    public static void main(String[] args) {

        final int PUERTO_SERVIDOR = 5000; //puerto donde se conectara
        byte[] buffer = new byte[1024];

        try {
            Inet4Address direccionServidor = (Inet4Address) Inet4Address.getByName("localhost"); //indicamos la direccion del server

            DatagramSocket socketUDP = new DatagramSocket(); //el puerto se asigna automatico caundo comience a enviar cosas al server

            String mensaje = "hola mundo desde el cliente";

            buffer = mensaje.getBytes(); //lo que hace es obtener los bytes del mensaje

            DatagramPacket pregunta = new DatagramPacket(buffer, buffer.length, direccionServidor, PUERTO_SERVIDOR);

            System.out.println("Envio la información del cliente");
            socketUDP.send(pregunta); //lo que hace es enviar la pregunta al servidor

            DatagramPacket peticion = new DatagramPacket(buffer, buffer.length);

            socketUDP.receive(peticion); //lo que hace es esperar a recibir la respuesta del servidor
            System.out.println("Recibo la información del servidor");
            mensaje = new String(peticion.getData()); //lo que hace es obtener los datos del paquete

            System.out.println(mensaje); //imprimimos el mensaje que envía el servidor
            socketUDP.close(); //cerramos el socket cliente
        } catch (SocketException e) {
            throw new RuntimeException(e);
        } catch (UnknownHostException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
