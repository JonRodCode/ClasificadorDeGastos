package model;

public class GastoPrestamo extends GastoBasico {

    private int cuotaActual;
    private int totalDeCuotas;
    private String prestamoDe;

    public int getCuotaActual() {
        return cuotaActual;
    }

    public void setCuotaActual(int cuotaActual) {
        this.cuotaActual = cuotaActual;
    }

    public int getTotalDeCuotas() {
        return totalDeCuotas;
    }

    public void setTotalDeCuotas(int totalDeCuotas) {
        this.totalDeCuotas = totalDeCuotas;
    }

    public String getPrestamoDe() {
        return prestamoDe;
    }

    public void setPrestamoDe(String prestamoDe) {
        this.prestamoDe = prestamoDe;
    }
}
