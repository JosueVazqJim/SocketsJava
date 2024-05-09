package sockets.filetransfer.files;

import javax.imageio.IIOException;
import java.io.*;
import java.net.Socket;
import java.nio.file.Files;
import java.util.List;

public class ClientConnection {

    private Socket socket;
    private DataInputStream in;
    private DataOutputStream out;

    public ClientConnection(Socket socket) {
        try {
            this.socket = socket;
            in = new DataInputStream(new BufferedInputStream(socket.getInputStream()));
            out = new DataOutputStream(socket.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void sendFile() throws IOException {
        sendMenu();
        int index = getSelectedFileIndex();
        sendSelectedFile(index);
    }

    private void sendSelectedFile(int index) throws IOException {
        File[] fileList = new File(Server.FILES_PATH).listFiles();
        File selectedFile = fileList[index];
        List<String> filesLines = Files.readAllLines(selectedFile.toPath());
        String filecontent  = String.join("\n", filesLines);
        out.writeUTF(filecontent);
    }

    private int getSelectedFileIndex() throws IOException {
        String input = in.readUTF();
        return Integer.parseInt(input)-1;
    }

    private void sendMenu() throws IOException {
        String menu = "** Files **\n";
        File[] fileList = new File(Server.FILES_PATH).listFiles();
        out.writeUTF("" + fileList.length);

        for (int i = 0; i < fileList.length; i++) {
            menu += String.format("* %d - %s\n", i+1, fileList[i].getName());
        }

        out.writeUTF(menu);
    }
}
