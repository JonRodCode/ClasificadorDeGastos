package model;

import enums.TipoTarjeta;

import java.util.List;

public class GastoTarjeta extends GastoBasico{
    private String mesDelResumen;
    private String tarjeta; //Visa, Master, etc
    private TipoTarjeta tipoTarjeta;
    private String aNombreDe;
    private String banco;
    private int numFinalTarjeta;
    private String nombreConsumo;

    public String getMesDelResumen() {
        return mesDelResumen;
    }

    public void setMesDelResumen(String mesDelResumen) {
        this.mesDelResumen = mesDelResumen;
    }

    public String getTarjeta() {
        return tarjeta;
    }

    public void setTarjeta(String tarjeta) {
        this.tarjeta = tarjeta;
    }

    public TipoTarjeta getTipoTarjeta() {
        return tipoTarjeta;
    }

    public void setTipoTarjeta(TipoTarjeta tipoTarjeta) {
        this.tipoTarjeta = tipoTarjeta;
    }

    public String getaNombreDe() {
        return aNombreDe;
    }

    public void setaNombreDe(String aNombreDe) {
        this.aNombreDe = aNombreDe;
    }

    public String getBanco() {
        return banco;
    }

    public void setBanco(String banco) {
        this.banco = banco;
    }

    public int getNumFinalTarjeta() {
        return numFinalTarjeta;
    }

    public void setNumFinalTarjeta(int numFinalTarjeta) {
        this.numFinalTarjeta = numFinalTarjeta;
    }

    public String getNombreConsumo() {
        return nombreConsumo;
    }

    public void setNombreConsumo(String nombreConsumo) {
        this.nombreConsumo = nombreConsumo;
    }
}
