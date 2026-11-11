public class Triangulo extends Figura{
    private final double ladoA;
    private final double ladoB;
    private final double ladoC;

    public Triangulo(String nombre, double ladoA, double ladoB, double ladoC) {
        super(nombre, 3);
        this.ladoA = ladoA;
        this.ladoB = ladoB;
        this.ladoC = ladoC;
    }

    @Override
    public double calcularArea() {
        double semiperimetro = calcularPerimetro() / 2;
        area = Math.sqrt(semiperimetro * (semiperimetro - ladoA)
                * (semiperimetro - ladoB) * (semiperimetro - ladoC));
        return area;
    }

    @Override
    public double calcularPerimetro() {
        perimetro = ladoA + ladoB + ladoC;
        return perimetro;
    }
}
