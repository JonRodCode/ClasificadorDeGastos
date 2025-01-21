package model;

import enums.TipoExcepcion;
import enums.TipoImporte;
import java.time.LocalDate;
import java.util.List;

public class GastoBasico {

    private String persona;
    private String detalleConsumo;
    private String fuenteDelGasto;
    private String categoria;
    private String determinacion;
    private double monto;
    private TipoImporte tipoDeImporte;
    private TipoExcepcion excepcion;
    private LocalDate fecha;
    private boolean marcado;

    public String getPersona() {
        return persona;
    }

    public void setPersona(String persona) {
        this.persona = persona;
    }

    public String getDetalleConsumo() {
        return detalleConsumo;
    }

    public void setDetalleConsumo(String detalleConsumo) {
        this.detalleConsumo = detalleConsumo;
    }

    public String getFuenteDelGasto() {
        return fuenteDelGasto;
    }

    public void setFuenteDelGasto(String fuenteDelGasto) {
        this.fuenteDelGasto = fuenteDelGasto;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public String getDeterminacion() {
        return determinacion;
    }

    public void setDeterminacion(String determinacion) {
        this.determinacion = determinacion;
    }

    public double getMonto() {
        return monto;
    }

    public void setMonto(double monto) {
        this.monto = monto;
    }

    public TipoImporte getTipoDeImporte() {
        return tipoDeImporte;
    }

    public void setTipoDeImporte(TipoImporte tipoDeImporte) {
        this.tipoDeImporte = tipoDeImporte;
    }

    public TipoExcepcion getExcepcion() {
        return excepcion;
    }

    public void setExcepcion(TipoExcepcion excepcion) {
        this.excepcion = excepcion;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public boolean isMarcado() {
        return marcado;
    }

    public void setMarcado(boolean marcado) {
        this.marcado = marcado;
    }
}
