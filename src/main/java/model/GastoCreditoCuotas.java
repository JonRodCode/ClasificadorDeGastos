package model;

public class GastoCreditoCuotas extends GastoTarjeta{

    private int cuotaActual;
    private int totalDeCuotas;
    private String nombreConsumo;

    public String getNombreConsumo() {
        return nombreConsumo;
    }

    public void setNombreConsumo(String nombreConsumo) {
        this.nombreConsumo = nombreConsumo;
    }
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
}
