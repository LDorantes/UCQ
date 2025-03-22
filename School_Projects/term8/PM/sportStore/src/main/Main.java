package main;

import ui.LoginFrame;

public class Main {
    public static void main(String[] args) {
        // Iniciar la interfaz gráfica de login
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new LoginFrame().setVisible(true);
            }
        });
    }
}