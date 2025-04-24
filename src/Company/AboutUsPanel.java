
package Company;

import javax.swing.*;
import java.awt.*;

public class AboutUsPanel extends JPanel {

    public AboutUsPanel() {
        setLayout(new BorderLayout());
        setBackground(Color.WHITE); // Fondo 

        // Panel para el logo y el nombre de la compañía
        JPanel headerPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 20));
        headerPanel.setBackground(Color.WHITE);

        // logo 
        JLabel logoLabel = new JLabel("src\\sources\\logo_company.jpg");
        logoLabel.setFont(new Font("Arial", Font.BOLD, 24));
        logoLabel.setForeground(new Color(0, 123, 255)); // Un azul similar al ejemplo

        JPanel companyInfoPanel = new JPanel(new BorderLayout());
        companyInfoPanel.setBackground(Color.WHITE);
        JLabel companyNameLabel = new JLabel("Moviles A Coruña");
        companyNameLabel.setFont(new Font("Arial", Font.BOLD, 28));
        companyNameLabel.setHorizontalAlignment(SwingConstants.CENTER);
        JLabel sloganLabel = new JLabel("Slogan: ¡Hay que vivir un poco!");
        sloganLabel.setFont(new Font("Arial", Font.ITALIC, 14));
        sloganLabel.setForeground(Color.GRAY);
        sloganLabel.setHorizontalAlignment(SwingConstants.CENTER);
        companyInfoPanel.add(companyNameLabel, BorderLayout.NORTH);
        companyInfoPanel.add(sloganLabel, BorderLayout.SOUTH);

        headerPanel.add(logoLabel);
        headerPanel.add(companyInfoPanel);

        // descripción "About Us"
        JPanel descriptionPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 30, 30));
        descriptionPanel.setBackground(new Color(80, 80, 80)); // Fondo 
        JTextArea descriptionTextArea = new JTextArea(
                "Somos tu proveedor de tecnología móvil de confianza. Ofrecemos soporte personalizado, " +
                        "consultoría de ventas y logística eficiente para mantener tu negocio conectado y actualizado."
        );
        descriptionTextArea.setForeground(Color.WHITE);
        descriptionTextArea.setBackground(new Color(80, 80, 80));
        descriptionTextArea.setFont(new Font("Arial", Font.PLAIN, 16));
        descriptionTextArea.setLineWrap(true);
        descriptionTextArea.setWrapStyleWord(true);
        descriptionTextArea.setEditable(false); // Es posible cambiar el tamaño de la ventana 
        descriptionTextArea.setPreferredSize(new Dimension(600, 100)); // dimensiones

        descriptionPanel.add(descriptionTextArea);

        // Panel inferior o footer
        JPanel footerPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        footerPanel.setBackground(Color.WHITE);
        JLabel developedByLabel = new JLabel("Proyecto desarrollado por: Ian Roca & Leo Ces Fragoso & Victor Lopez & Enmanuel Lledo");
        developedByLabel.setForeground(Color.GRAY);
        JLabel copyrightLabel = new JLabel("Todos los derechos reservados © 2025");
        copyrightLabel.setForeground(Color.GRAY);

        footerPanel.add(developedByLabel);
        footerPanel.add(copyrightLabel);

        add(headerPanel, BorderLayout.NORTH);
        add(descriptionPanel, BorderLayout.CENTER);
        add(footerPanel, BorderLayout.SOUTH);
    }

}