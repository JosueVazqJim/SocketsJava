package ejercicio_sockets_ddr_4;

import javax.swing.*;
import java.awt.event.*;
import java.util.Observable;
import java.util.Observer;

public class Frm1 extends JDialog implements Observer { //es un observador, es a quien se le notifica
    private JPanel contentPane;
    private JButton buttonEnviar;
    private JTextField textTextoEnviar;
    private JTextArea textArea1;

    public Frm1() {
        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(buttonEnviar);
        Servidor s = new Servidor(5000); //creamos un servidor en el puerto 5000
        s.addObserver(this); //agregamos el observador que indica que cuando hay cmabios se le avise a este objeto observer
        Thread t = new Thread(s); //creamos un hilo para el servidor
        t.start(); //iniciamos el hilo

        buttonEnviar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onOK();
            }
        });


        // call onCancel() when cross is clicked
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);

    }

    private void onOK() {
        String mensaje = "1: "+this.textTextoEnviar.getText()+"\n";

        this.textArea1.append(mensaje);

        Cliente c = new Cliente("192.168.100.15", 5000, mensaje); //creamos un cliente en el puerto 6000
        Thread t = new Thread(c); //creamos un hilo para el cliente
        t.start(); //iniciamos el hilo
    }


    public static void main(String[] args) {
        Frm1 dialog = new Frm1();
        dialog.pack();
        dialog.setVisible(true);
        System.exit(0);
    }

    @Override
    public void update(Observable o, Object arg) { //el o seria el server  y el arg seria el mensaje
        this.textArea1.append((String) arg);
    }
}
