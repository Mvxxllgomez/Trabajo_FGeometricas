public class Circulo extends Figura{

    private double radio;

    public Circulo(double radio) {
        this("Circulo", radio);
    }

    public Circulo(String nombre, double radio) {
        super(nombre, 0);
        this.radio = radio;
    }

    public double calcularDiametro(){
        return 2 * radio;
    }

    @Override
    public double calcularArea(){
        area = Math.PI * radio * radio;
        return area;
    }

    @Override
    public double calcularPerimetro(){
        perimetro = 2 * Math.PI * radio;
        return perimetro;
    }
}
