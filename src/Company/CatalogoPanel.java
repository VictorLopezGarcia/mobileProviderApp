package Company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.net.URL;
import java.util.ArrayList;

public class CatalogoPanel extends JPanel {

    private DefaultListModel<Movil> modeloLista;
    private JList<Movil> listaMoviles;
    private JTextField campoBusqueda;
    private JTextArea areaCaracteristicas;
    private JLabel labelImagen;

    public CatalogoPanel() {
        // Configura el layout principal
        this.setLayout(new BorderLayout());

        // Label imagen
        labelImagen = new JLabel();
        labelImagen.setHorizontalAlignment(SwingConstants.CENTER);
        labelImagen.setPreferredSize(new Dimension(300, 200));
        labelImagen.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Lista de móviles
        modeloLista = new DefaultListModel<>();
        listaMoviles = new JList<>(modeloLista);
        JScrollPane scrollPane = new JScrollPane(listaMoviles);

        // Área de características
        areaCaracteristicas = new JTextArea();
        areaCaracteristicas.setEditable(false);
        areaCaracteristicas.setFont(new Font("Monospaced", Font.PLAIN, 12));
        areaCaracteristicas.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        JScrollPane scrollCaracteristicas = new JScrollPane(areaCaracteristicas);
        scrollCaracteristicas.setPreferredSize(new Dimension(300, 0));
        scrollCaracteristicas.setBorder(BorderFactory.createTitledBorder("Especificaciones Técnicas"));

        // Campo de búsqueda
        campoBusqueda = new JTextField();
        JButton btnBuscar = new JButton("Buscar por marca");
        btnBuscar.addActionListener(e -> buscarPorMarca());

        // Botones
        JButton btnComprar = new JButton("Comprar");
        btnComprar.addActionListener(e -> comprarSeleccionado());

        JButton btnAgregar = new JButton("Agregar nuevo móvil");
        btnAgregar.addActionListener(e -> agregarNuevoMovil());

        // Listener de selección... ejecuta los metodos para mostrar
        listaMoviles.addListSelectionListener(e -> mostrarCaracteristicas());

        // Paneles
        JPanel panelSuperior = new JPanel(new BorderLayout());
        panelSuperior.add(campoBusqueda, BorderLayout.CENTER);
        panelSuperior.add(btnBuscar, BorderLayout.EAST);

        JPanel panelInferior = new JPanel(new GridLayout(2, 1));
        panelInferior.add(btnAgregar);
        panelInferior.add(btnComprar);

        JPanel panelPrincipal = new JPanel(new BorderLayout());
        panelPrincipal.add(panelSuperior, BorderLayout.NORTH);
        panelPrincipal.add(scrollPane, BorderLayout.CENTER);
        panelPrincipal.add(panelInferior, BorderLayout.SOUTH);

        JPanel panelDerecho = new JPanel(new BorderLayout());
        panelDerecho.setPreferredSize(new Dimension(300, 650));
        panelDerecho.add(scrollCaracteristicas, BorderLayout.CENTER);
        panelDerecho.add(labelImagen, BorderLayout.SOUTH);

        this.add(panelPrincipal, BorderLayout.CENTER);
        this.add(panelDerecho, BorderLayout.EAST);
        cargarMoviles(Catalogo.ListaMovilesModelo());
    }

    /**
     */
    private void cargarMoviles(ArrayList<Movil> lista) {
        if(lista.isEmpty()){
            JOptionPane.showMessageDialog(this, "Error actualizando catalogo.");
            return;
        }
        modeloLista.clear();
        for (Movil m : lista) {
            modeloLista.addElement(m);
        }
    }
    /**
     */
    private void buscarPorMarca() {
        String marca = campoBusqueda.getText().trim();
        if (marca.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Introduce una marca para buscar.");
            return;
        }
        cargarMoviles(Catalogo.buscarPorMarca(marca));
    }
    /**
     */
    private void comprarSeleccionado() {
        Movil seleccionado = listaMoviles.getSelectedValue();
        if (seleccionado != null) {
            boolean exito = Catalogo.comprarTelefono(seleccionado);
            String mensaje = exito ? "Compra realizada con éxito." : "No hay stock disponible.";
            JOptionPane.showMessageDialog(this, mensaje);
            listaMoviles.updateUI();
        } else {
            JOptionPane.showMessageDialog(this, "Selecciona un móvil primero.");
        }
    }
    /**
     */
    private void agregarNuevoMovil() {
        JTextField campoMarca = new JTextField();
        JTextField campoModelo = new JTextField();
        JTextField campoPrecio = new JTextField();
        JTextField campoStock = new JTextField();
        JTextField campoCaracteristicas = new JTextField();

        JPanel panel = new JPanel(new GridLayout(0, 1));
        panel.add(new JLabel("Marca:"));
        panel.add(campoMarca);
        panel.add(new JLabel("Modelo:"));
        panel.add(campoModelo);
        panel.add(new JLabel("Precio:"));
        panel.add(campoPrecio);
        panel.add(new JLabel("Stock:"));
        panel.add(campoStock);
        panel.add(new JLabel("Características (separadas por ;):"));
        panel.add(campoCaracteristicas);

        int result = JOptionPane.showConfirmDialog(this, panel, "Agregar Móvil", JOptionPane.OK_CANCEL_OPTION);
        if (result == JOptionPane.OK_OPTION) {
            try {
                String marca = campoMarca.getText().trim();
                String modelo = campoModelo.getText().trim();
                double precio = Double.parseDouble(campoPrecio.getText().trim());
                int stock = Integer.parseInt(campoStock.getText().trim());
                String[] caracteristicasArray = campoCaracteristicas.getText().split(";");
                ArrayList<String> caracteristicas = new ArrayList<>();
                for (String c : caracteristicasArray) {
                    caracteristicas.add(c.trim());
                }

                Movil nuevo = new Movil(marca, modelo, caracteristicas, precio, stock);
                Catalogo.agregarMovil(nuevo);
                JOptionPane.showMessageDialog(this, "Móvil agregado correctamente.");
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Error en los datos ingresados: " + ex.getMessage());
            }
        }
    }
    /**
     * Crea una instancia del objeto Movil que contiene un JList<Movil>
     * Comprueba
     */
    private void mostrarCaracteristicas() {
        Movil seleccionado = listaMoviles.getSelectedValue();
        
        if (seleccionado != null) {
            StringBuilder CadenaCaracteristicas = new StringBuilder();
            for (String c : seleccionado.getCaracteristicas()) {
                CadenaCaracteristicas.append("• ").append(c).append("\n");
            }
            areaCaracteristicas.setText(CadenaCaracteristicas.toString());

            mostrarImagen(seleccionado.getModelo());
            
        } else {
            areaCaracteristicas.setText("");
        }
    }

    private void mostrarImagen(String modelo) {
        String nombreArchivo = modelo.toLowerCase().replace(" ", "") + ".png";
        String ruta = "Company/imagenes/" + nombreArchivo;  // Usa siempre "/" como separador en recursos

        System.out.println("Intentando cargar: " + ruta);
        URL imageUrl = getClass().getClassLoader().getResource(ruta);
        ImageIcon icono;

        if (imageUrl != null) {
            icono = new ImageIcon(imageUrl);
        } else {
            // Fallback a imagen por defecto (debe estar también en recursos empaquetados)
            URL fallbackUrl = getClass().getClassLoader().getResource("Company/imagenes/no_available.png");
            if (fallbackUrl != null) {
                icono = new ImageIcon(fallbackUrl);
            } else {
                System.err.println("Imagen de respaldo no encontrada.");
                icono = null;
            }
        }

        if (icono != null) {
            Image imagenEscalada = icono.getImage().getScaledInstance(250, 180, Image.SCALE_SMOOTH);
            labelImagen.setIcon(new ImageIcon(imagenEscalada));
        } else {
            labelImagen.setIcon(null);
        }
    }
    
    
}
