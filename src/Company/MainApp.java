package Company;

import javax.swing.*;

public class MainApp {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            
            JFrame frame = new JFrame("Proveedor - A Coruña");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(1000, 700);
            frame.setLocationRelativeTo(null);

            Catalogo.instanciarBd(); //inicializa los datos, leyendo del fichero moviles.txt
            
            JTabbedPane tabs = new JTabbedPane();

            tabs.addTab("Dashboard", new DashboardPanel());
            tabs.addTab("Catálogo Móviles", new CatalogoPanel());
            tabs.addTab("About Us", new AboutUsPanel());
            
            frame.add(tabs);
            frame.setVisible(true);
        });
    }
}
