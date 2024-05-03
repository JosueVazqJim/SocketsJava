package ejercicio_sockets_udp_ddr_2;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.Inet4Address;
import java.net.SocketException;

public class Servidor {
    public static void main(String[] args) throws IOException {
        final int PUERTO = 5000; //puerto donde escuchara
        byte[] buffer = new byte[1024];

        try {
            System.out.println("iniciado el servidor");
            DatagramSocket socketUDP = new DatagramSocket(PUERTO); //el DatagramSocket esta en este puerto escuchando
            //este enviara datagramas, es simil al socket
            while(true){

                DatagramPacket peticion = new DatagramPacket(buffer, buffer.length); //lo que hace es crear un paquete de este volumen

                socketUDP.receive(peticion); //lo que hace es recibir la petición del cliente, un paquete que debe tener esas caracteristicas
                System.out.println("Recibo la información del cliente");

                String mensaje = new String(peticion.getData()); //lo que hace es obtener los datos del paquete
                System.out.println(mensaje); //imprimimos el mensaje que envía el cliente

                int puertoCliente = peticion.getPort(); //obtenemos el puerto del cliente
                Inet4Address direccion = (Inet4Address) peticion.getAddress(); //obtenemos la dirección del cliente

                mensaje="hola mundo desde el server";
                buffer = mensaje.getBytes(); //lo que hace es obtener los bytes del mensaje
                DatagramPacket respuesta = new DatagramPacket(buffer, buffer.length, direccion, puertoCliente); //lo que hace es crear un paquete de respuesta

                System.out.println("Envio la información del cliente");
                socketUDP.send(respuesta); //lo que hace es enviar la respuesta al cliente
            }


        } catch (SocketException e) {
            throw new RuntimeException(e);
        }
    }
}
