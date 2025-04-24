
package Company;

import javax.swing.*;
import java.awt.*;

public class DashboardPanel extends JPanel {

    public DashboardPanel() {
        // Layout principal: 1 fila, 3 columnas con espacios
        this.setLayout(new GridLayout(1, 3, 20, 0));
        this.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // Tarjetas
        add(createCard("OUR BRANDS",
                new String[]{"src\\sources\\marcas\\huawei-logo.jpg", "src\\sources\\marcas\\Apple_logo.jpg", "src\\sources\\marcas\\samsung-logo.jpg"},
                "Consulta en tiempo real la disponibilidad de nuestros modelos. Mantenemos un stock actualizado para que siempre encuentres lo que necesitas, desde los últimos lanzamientos hasta opciones económicas y confiables."));

        add(createCard("CATALOG",
                null,
                "Explora nuestra selección de móviles. Trabajamos con las marcas más reconocidas del mercado para ofrecer variedad, calidad y tecnología de punta para cada tipo de cliente."));

        add(createCard("ABOUT US",
                null,
                "Somos tu proveedor de confianza en tecnología móvil. Ofrecemos atención personalizada, asesoría comercial y logística eficiente para que tu negocio siempre esté conectado y actualizado."));
    }

    private JPanel createCard(String title, String[] imagePaths, String description) {
        // Panel de tarjeta con BorderLayout
        JPanel card = new JPanel(new BorderLayout());
        card.setBackground(Color.WHITE);
        card.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY, 1, true));

        // Título arriba
        JLabel lblTitle = new JLabel(title, SwingConstants.CENTER);
        lblTitle.setFont(lblTitle.getFont().deriveFont(Font.BOLD, 16f));
        lblTitle.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));
        card.add(lblTitle, BorderLayout.NORTH);

        // Centro: imagen(es) o sólo texto
        JPanel center = new JPanel();
        center.setOpaque(false);
        center.setLayout(new BoxLayout(center, BoxLayout.Y_AXIS));

        if (imagePaths != null) {
            // si hay varias imágenes, las mostramos horiz. centradas
            JPanel icons = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
            icons.setOpaque(false);
            for (String path : imagePaths) {
                ImageIcon icon = new ImageIcon(path);
                // escalamos a 64×64
                Image img = icon.getImage().getScaledInstance(64, 64, Image.SCALE_SMOOTH);
                icons.add(new JLabel(new ImageIcon(img)));
            }
            center.add(icons);
        }

        // Área de descripción scrollable
        JTextArea txt = new JTextArea(description);
        txt.setLineWrap(true);
        txt.setWrapStyleWord(true);
        txt.setEditable(false);
        txt.setBackground(new Color(240, 240, 240));
        txt.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        JScrollPane scroll = new JScrollPane(txt,
                JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
                JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scroll.setPreferredSize(new Dimension(200, 120));
        center.add(scroll);

        card.add(center, BorderLayout.CENTER);

        // Botón abajo a la derecha
        JButton btn = new JButton("See more");
        JPanel south = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        south.setOpaque(false);
        south.add(btn);
        card.add(south, BorderLayout.SOUTH);

        return card;
    }
}
