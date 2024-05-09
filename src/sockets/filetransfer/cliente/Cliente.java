package sockets.filetransfer.cliente;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class Cliente {
    private Socket socket;
    private DataInputStream in;
    private DataOutputStream out;
    private Scanner scanner;

    public Cliente() {
        try {
            socket = new Socket("localhost", 5000);
            in = new DataInputStream(socket.getInputStream());
            out = new DataOutputStream(socket.getOutputStream());
            scanner = new Scanner(System.in);

            getFile();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void getFile() throws IOException {
        String filesList = in.readUTF();
        int maxFiles = Integer.parseInt(filesList);
        String menu = in.readUTF();
        System.out.println(menu);
        int userSeleciton = 0;
        boolean isSelecitonCorrect = false;
        while (!isSelecitonCorrect) {
            System.out.print("Selecciona el numero de un archivo: ");
            userSeleciton = scanner.nextInt();
            isSelecitonCorrect = userSeleciton > 0 && userSeleciton <= maxFiles;
        }
        out.writeUTF("" + userSeleciton);
        String fileContent = in.readUTF();
        System.out.println("-- Contenido del archivo --");
        System.out.println(fileContent);
        System.out.println("-- Fin del archivo --");
    }

    public static void main(String[] args) {
        new Cliente();
    }
}
