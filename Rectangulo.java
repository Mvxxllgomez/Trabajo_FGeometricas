public class Rectangulo extends Figura{

    private final double base;
    private final double altura;

    public Rectangulo(String nombre, double base, double altura) {
        super(nombre, 4);
        this.base = base;
        this.altura = altura;
    }

    @Override
    public double calcularArea(){
        area = base * altura;
        return area;
    }

    @Override
    public double calcularPerimetro(){
        perimetro = 2 * (base + altura);
        return perimetro;
    }
}
