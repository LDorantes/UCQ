package ui;
import models.Usuario;
import services.AuthService;
import java.util.Scanner;



public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Usuario: ");
        String username = scanner.nextLine();

        System.out.print("Contraseña: ");
        String password = scanner.nextLine();

        Usuario usuario = new Usuario(username, password);
        if (AuthService.validarLogin(usuario)) {
            System.out.println("✅ Login exitoso. ¡Bienvenido " + username + "!");
        } else {
            System.out.println("❌ Usuario o contraseña incorrectos.");
        }

        scanner.close();
    }
}
