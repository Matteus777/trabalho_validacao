/*
 * This Java source file was generated by the Gradle 'init' task.
 */
package projeto_validacao;

import io.github.cdimascio.dotenv.Dotenv;
import java.awt.BorderLayout;
import javax.swing.JFrame;
import javax.swing.JScrollPane;

public class App {

    public static void main(String[] args) {
        Persistence.createDB();
        MainJFrame main = new MainJFrame();

    }
}
