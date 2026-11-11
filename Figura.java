public class Figura {

    private String nombre;
    private int lados;
    protected double perimetro;
    protected double area;

    public Figura(String nombre, int lados) {
        this.nombre = nombre;
        this.lados = lados;
    }

    public double calcularArea() {
        return area;
    }

    public double calcularPerimetro() {
        return perimetro;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getLados() {
        return lados;
    }

    public void setLados(int lados) {
        this.lados = lados;
    }

    public double getPerimetro() {
        return perimetro;
    }

    public void setPerimetro(double perimetro) {
        this.perimetro = perimetro;
    }

    public double getArea() {
        return area;
    }

    public void setArea(double area) {
        this.area = area;
    }
}
