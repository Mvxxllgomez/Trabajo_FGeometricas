import java.util.ArrayList;
import java.util.Scanner;

public class SistemaCLI {

    private final Scanner scanner;
    private final ArrayList<Figura> figuras = new ArrayList<>();

    public SistemaCLI(Scanner scanner) {
        this.scanner = scanner;
    }

    public void ejecutar() {
        int opcion;
        do {
            System.out.println("\nSISTEMA DE FIGURAS GEOMETRICAS");
            System.out.println("1. Registrar figura");
            System.out.println("2. Buscar figura");
            System.out.println("3. Consultar posicion");
            System.out.println("4. Salir");

            System.out.print("Seleccione una opcion: ");
            opcion = scanner.nextInt();

            switch (opcion) {
                case 1:
                    registrarFigura();
                    break;
                case 2:
                    buscarFigura();
                    break;
                case 3:
                    consultarPosicion();
                    break;

                case 4:
                    verFigurasRegistradas();
                    break;
                case 5:
                    System.out.println("Saliendo del sistema...");
                    break;
                default:
                    System.out.println("Opcion no valida.");
                    break;
            }
        } while (opcion != 5);
    }

    private void registrarFigura() {
        System.out.println("REGISTRO DE FIGURAS");
        scanner.nextLine();
        System.out.print("Ingrese el nombre de la figura: ");
        String nombre = scanner.nextLine().trim();

        System.out.print("Ingrese el numero de lados (0 circulo, 3 triangulo, 4 rectangulo): ");
        int lados = scanner.nextInt();

        Figura figura;
        if (lados == 0) {
            System.out.print("Ingrese el radio del circulo: ");
            double radio = scanner.nextDouble();
            figura = new Circulo(nombre, radio);
        } else if (lados == 3) {
            System.out.print("Ingrese el primer lado: ");
            double ladoA = scanner.nextDouble();
            System.out.print("Ingrese el segundo lado: ");
            double ladoB = scanner.nextDouble();
            System.out.print("Ingrese el tercer lado: ");
            double ladoC = scanner.nextDouble();
            figura = new Triangulo(nombre, ladoA, ladoB, ladoC);
        } else if (lados == 4) {
            System.out.print("Ingrese la base: ");
            double base = scanner.nextDouble();
            System.out.print("Ingrese la altura: ");
            double altura = scanner.nextDouble();
            figura = new Rectangulo(nombre, base, altura);
        } else {
            System.out.println("Numero de lados no valido.");
            return;
        }

        figura.calcularArea();
        figura.calcularPerimetro();
        figuras.add(figura);
        System.out.println("Figura registrada correctamente.");
    }

    private void buscarFigura() {
        scanner.nextLine(); // Consume el salto de linea que dejo nextInt().
        System.out.print("Ingrese el nombre de la figura a buscar: ");
        String nombre = scanner.nextLine().trim();

        for (int i = 0; i < figuras.size(); i++) {
            Figura figura = figuras.get(i);
            if (figura.getNombre().equalsIgnoreCase(nombre)) {
                mostrarFigura(figura);
                return;
            }
        }
        System.out.println("Figura no encontrada.");
    }

    private void consultarPosicion() {
        System.out.print("Ingrese la posicion de la figura (inicia en 0): ");
        int posicion = scanner.nextInt();

        if (posicion >= 0 && posicion < figuras.size()) {
            mostrarFigura(figuras.get(posicion));
        } else {
            System.out.println("Posicion no valida.");
        }
    }

    private void mostrarFigura(Figura figura) {
        System.out.println("Figura: " + figura.getNombre());
        System.out.println("Numero de lados: " + figura.getLados());
        System.out.println("Perimetro: " + figura.getPerimetro());
        System.out.println("Area: " + figura.getArea());
    }

    private void verFigurasRegistradas() {
        if (figuras.isEmpty()) {
            System.out.println("No hay figuras registradas.");
        } else {
            System.out.println("Figuras registradas:");
            for (int i = 0; i < figuras.size(); i++) {
                Figura figura = figuras.get(i);
                System.out.println((i + 1) + ". " + figura.getNombre() + " - Lados: " + figura.getLados() +
                        ", Perimetro: " + figura.getPerimetro() + ", Area: " + figura.getArea());
            }
        }
    }
}
