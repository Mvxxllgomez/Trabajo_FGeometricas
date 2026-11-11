import javax.swing.SwingUtilities;

public class Main {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            SistemaGUI sistema = new SistemaGUI();
            sistema.setVisible(true);
        });
    }
}
