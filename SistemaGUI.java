import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.util.ArrayList;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;

public class SistemaGUI extends JFrame {

    private static final String CIRCULO = "Círculo";
    private static final String TRIANGULO = "Triángulo";
    private static final String RECTANGULO = "Rectángulo";

    private final ArrayList<Figura> figuras = new ArrayList<>();
    private final JTextField campoNombre = new JTextField(16);
    private final JComboBox<String> selectorTipo =
            new JComboBox<>(new String[]{CIRCULO, TRIANGULO, RECTANGULO});
    private final CardLayout disposicionDimensiones = new CardLayout();
    private final JPanel panelDimensiones = new JPanel(disposicionDimensiones);
    private final JTextField campoRadio = new JTextField(16);
    private final JTextField campoLadoA = new JTextField(16);
    private final JTextField campoLadoB = new JTextField(16);
    private final JTextField campoLadoC = new JTextField(16);
    private final JTextField campoBase = new JTextField(16);
    private final JTextField campoAltura = new JTextField(16);
    private final DefaultTableModel modeloTabla = new DefaultTableModel(
            new Object[]{"#", "Nombre", "Tipo", "Lados", "Perímetro", "Área"}, 0) {
        @Override
        public boolean isCellEditable(int row, int column) {
            return false;
        }
    };
    private final JTable tabla = new JTable(modeloTabla);

    public SistemaGUI() {
        super("Sistema de figuras geométricas");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setMinimumSize(new Dimension(760, 470));
        setLocationRelativeTo(null);
        construirInterfaz();
    }

    private void construirInterfaz() {
        setLayout(new BorderLayout(12, 12));
        JPanel contenido = new JPanel(new BorderLayout(12, 12));
        contenido.setBorder(BorderFactory.createEmptyBorder(18, 18, 18, 18));
        add(contenido, BorderLayout.CENTER);

        JLabel titulo = new JLabel("Sistema de figuras geométricas");
        titulo.setFont(titulo.getFont().deriveFont(Font.BOLD, 22f));
        contenido.add(titulo, BorderLayout.NORTH);

        tabla.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        tabla.setFillsViewportHeight(true);
        contenido.add(new JScrollPane(tabla), BorderLayout.CENTER);
        contenido.add(crearPanelRegistro(), BorderLayout.EAST);
        contenido.add(crearPanelAcciones(), BorderLayout.SOUTH);
    }

    private JPanel crearPanelRegistro() {
        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createTitledBorder("Registrar figura"),
                BorderFactory.createEmptyBorder(8, 8, 8, 8)));
        GridBagConstraints restricciones = new GridBagConstraints();
        restricciones.gridx = 0;
        restricciones.anchor = GridBagConstraints.WEST;
        restricciones.insets = new Insets(5, 4, 5, 4);

        agregarCampo(panel, restricciones, "Nombre:", campoNombre);
        agregarCampo(panel, restricciones, "Tipo:", selectorTipo);

        panelDimensiones.add(crearPanelCampos("Radio:", campoRadio), CIRCULO);
        JPanel triangulo = new JPanel(new GridBagLayout());
        GridBagConstraints trianguloRestricciones = new GridBagConstraints();
        trianguloRestricciones.gridx = 0;
        trianguloRestricciones.anchor = GridBagConstraints.WEST;
        trianguloRestricciones.insets = new Insets(3, 0, 3, 0);
        agregarCampo(triangulo, trianguloRestricciones, "Lado A:", campoLadoA);
        agregarCampo(triangulo, trianguloRestricciones, "Lado B:", campoLadoB);
        agregarCampo(triangulo, trianguloRestricciones, "Lado C:", campoLadoC);
        panelDimensiones.add(triangulo, TRIANGULO);

        JPanel rectangulo = new JPanel(new GridBagLayout());
        GridBagConstraints rectanguloRestricciones = new GridBagConstraints();
        rectanguloRestricciones.gridx = 0;
        rectanguloRestricciones.anchor = GridBagConstraints.WEST;
        rectanguloRestricciones.insets = new Insets(3, 0, 3, 0);
        agregarCampo(rectangulo, rectanguloRestricciones, "Base:", campoBase);
        agregarCampo(rectangulo, rectanguloRestricciones, "Altura:", campoAltura);
        panelDimensiones.add(rectangulo, RECTANGULO);

        restricciones.gridy++;
        restricciones.weighty = 1;
        restricciones.fill = GridBagConstraints.VERTICAL;
        panel.add(panelDimensiones, restricciones);

        JButton botonRegistrar = new JButton("Registrar");
        botonRegistrar.addActionListener(evento -> registrarFigura());
        restricciones.gridy++;
        restricciones.weighty = 0;
        restricciones.fill = GridBagConstraints.NONE;
        restricciones.anchor = GridBagConstraints.EAST;
        panel.add(botonRegistrar, restricciones);

        selectorTipo.addActionListener(evento ->
                disposicionDimensiones.show(panelDimensiones,
                        (String) selectorTipo.getSelectedItem()));
        disposicionDimensiones.show(panelDimensiones, CIRCULO);
        return panel;
    }

    private JPanel crearPanelCampos(String etiqueta, JTextField campo) {
        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints restricciones = new GridBagConstraints();
        restricciones.gridx = 0;
        restricciones.anchor = GridBagConstraints.WEST;
        restricciones.insets = new Insets(3, 0, 3, 0);
        agregarCampo(panel, restricciones, etiqueta, campo);
        return panel;
    }

    private void agregarCampo(JPanel panel, GridBagConstraints restricciones,
                              String etiqueta, java.awt.Component campo) {
        int fila = panel.getComponentCount();
        restricciones.gridy = fila;
        panel.add(new JLabel(etiqueta), restricciones);
        restricciones.gridy = fila + 1;
        restricciones.fill = GridBagConstraints.HORIZONTAL;
        panel.add(campo, restricciones);
        restricciones.fill = GridBagConstraints.NONE;
    }

    private JPanel crearPanelAcciones() {
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.LEFT, 8, 4));
        JButton botonBuscar = new JButton("Buscar por nombre");
        botonBuscar.addActionListener(evento -> buscarFigura());
        JButton botonPosicion = new JButton("Consultar posición");
        botonPosicion.addActionListener(evento -> consultarPosicion());
        JButton botonDetalle = new JButton("Ver figura seleccionada");
        botonDetalle.addActionListener(evento -> mostrarSeleccion());
        panel.add(botonBuscar);
        panel.add(botonPosicion);
        panel.add(botonDetalle);
        return panel;
    }

    private void registrarFigura() {
        String nombre = campoNombre.getText().trim();
        if (nombre.isEmpty()) {
            mostrarError("Ingrese el nombre de la figura.");
            campoNombre.requestFocusInWindow();
            return;
        }

        try {
            String tipo = (String) selectorTipo.getSelectedItem();
            Figura figura;
            if (CIRCULO.equals(tipo)) {
                figura = new Circulo(nombre, leerPositivo(campoRadio, "radio"));
            } else if (TRIANGULO.equals(tipo)) {
                double ladoA = leerPositivo(campoLadoA, "lado A");
                double ladoB = leerPositivo(campoLadoB, "lado B");
                double ladoC = leerPositivo(campoLadoC, "lado C");
                if (ladoA + ladoB <= ladoC || ladoA + ladoC <= ladoB
                        || ladoB + ladoC <= ladoA) {
                    mostrarError("Las medidas no forman un triángulo válido.");
                    return;
                }
                figura = new Triangulo(nombre, ladoA, ladoB, ladoC);
            } else {
                double base = leerPositivo(campoBase, "base");
                double altura = leerPositivo(campoAltura, "altura");
                figura = new Rectangulo(nombre, base, altura);
            }

            figura.calcularArea();
            figura.calcularPerimetro();
            figuras.add(figura);
            actualizarTabla();
            campoNombre.setText("");
            JOptionPane.showMessageDialog(this, "Figura registrada correctamente.",
                    "Registro", JOptionPane.INFORMATION_MESSAGE);
        } catch (IllegalArgumentException excepcion) {
            mostrarError(excepcion.getMessage());
        }
    }

    private double leerPositivo(JTextField campo, String nombreMedida) {
        try {
            double valor = Double.parseDouble(campo.getText().trim());
            if (!Double.isFinite(valor) || valor <= 0) {
                throw new NumberFormatException();
            }
            return valor;
        } catch (NumberFormatException excepcion) {
            throw new IllegalArgumentException(
                    "Ingrese un valor numérico positivo para " + nombreMedida + ".");
        }
    }

    private void buscarFigura() {
        if (figuras.isEmpty()) {
            mostrarError("No hay figuras registradas.");
            return;
        }
        String nombre = JOptionPane.showInputDialog(this,
                "Ingrese el nombre de la figura:", "Buscar figura",
                JOptionPane.QUESTION_MESSAGE);
        if (nombre == null) {
            return;
        }
        for (int i = 0; i < figuras.size(); i++) {
            if (figuras.get(i).getNombre().equalsIgnoreCase(nombre.trim())) {
                mostrarDetalle(figuras.get(i), i);
                return;
            }
        }
        JOptionPane.showMessageDialog(this, "Figura no encontrada.",
                "Búsqueda", JOptionPane.INFORMATION_MESSAGE);
    }

    private void consultarPosicion() {
        if (figuras.isEmpty()) {
            mostrarError("No hay figuras registradas.");
            return;
        }
        String entrada = JOptionPane.showInputDialog(this,
                "Ingrese la posición de la figura (inicia en 0):",
                "Consultar posición", JOptionPane.QUESTION_MESSAGE);
        if (entrada == null) {
            return;
        }
        try {
            int posicion = Integer.parseInt(entrada.trim());
            if (posicion < 0 || posicion >= figuras.size()) {
                mostrarError("La posición debe estar entre 0 y " + (figuras.size() - 1) + ".");
                return;
            }
            mostrarDetalle(figuras.get(posicion), posicion);
        } catch (NumberFormatException excepcion) {
            mostrarError("Ingrese una posición entera válida.");
        }
    }

    private void mostrarSeleccion() {
        int fila = tabla.getSelectedRow();
        if (fila < 0) {
            mostrarError("Seleccione una figura de la tabla.");
            return;
        }
        mostrarDetalle(figuras.get(fila), fila);
    }

    private void mostrarDetalle(Figura figura, int posicion) {
        String detalle = String.format(
                "Figura: %s%nTipo: %s%nPosición: %d%nNúmero de lados: %d%nPerímetro: %.2f%nÁrea: %.2f",
                figura.getNombre(), obtenerTipo(figura), posicion, figura.getLados(),
                figura.getPerimetro(), figura.getArea());
        JOptionPane.showMessageDialog(this, detalle, "Detalle de figura",
                JOptionPane.INFORMATION_MESSAGE);
    }

    private String obtenerTipo(Figura figura) {
        if (figura instanceof Circulo) {
            return CIRCULO;
        }
        if (figura instanceof Triangulo) {
            return TRIANGULO;
        }
        return RECTANGULO;
    }

    private void actualizarTabla() {
        modeloTabla.setRowCount(0);
        for (int i = 0; i < figuras.size(); i++) {
            Figura figura = figuras.get(i);
            modeloTabla.addRow(new Object[]{i, figura.getNombre(), obtenerTipo(figura),
                    figura.getLados(), String.format("%.2f", figura.getPerimetro()),
                    String.format("%.2f", figura.getArea())});
        }
    }

    private void mostrarError(String mensaje) {
        JOptionPane.showMessageDialog(this, mensaje, "Datos no válidos",
                JOptionPane.ERROR_MESSAGE);
    }
}
