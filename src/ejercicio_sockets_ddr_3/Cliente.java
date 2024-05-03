package ejercicio_sockets_ddr_3;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class Cliente implements Runnable { //este no es observable, solo lanza un hilo

    private int puerto;
    private String mensaje;

    public Cliente(int puerto, String mensaje){
        this.puerto = puerto;
        this.mensaje = mensaje;
    }

    @Override
    public void run() {
        final String HOST = "127.0.0.1"; //ip del servidor, por ahora solo es local
        DataOutputStream out;

        try {
            Socket sc = new Socket(HOST, puerto); //objeto Socket que viene en la biblioteca de java.net, es el socket del cliente
            out = new DataOutputStream(sc.getOutputStream()); //creamos un flujo de salida para enviar datos al servidor

            out.writeUTF(mensaje); //enviamos un mensaje al servidor y ya, el server tiene que notificar si hay cambio, el nos avisa

            sc.close(); //cerramos el socket cliente
        } catch (
                IOException e) {
            throw new RuntimeException(e);
        }
    }
}
