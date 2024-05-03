package ejercicio_sockets_ddr_4;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class Cliente implements Runnable { //este no es observable, solo lanza un hilo

    private String host;
    private int puerto;
    private String mensaje;

    public Cliente(String host, int puerto, String mensaje){
        this.host = host;
        this.puerto = puerto;
        this.mensaje = mensaje;
    }

    @Override
    public void run() {
        DataOutputStream out;

        try {
            Socket sc = new Socket(host, puerto); //objeto Socket que viene en la biblioteca de java.net, es el socket del cliente
            out = new DataOutputStream(sc.getOutputStream()); //creamos un flujo de salida para enviar datos al servidor

            out.writeUTF(mensaje); //enviamos un mensaje al servidor y ya, el server tiene que notificar si hay cambio, el nos avisa

            sc.close(); //cerramos el socket cliente
        } catch (
                IOException e) {
            throw new RuntimeException(e);
        }
    }
}
