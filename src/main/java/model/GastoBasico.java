package model;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import enums.TipoExcepcion;
import enums.TipoImporte;
import java.time.LocalDate;


@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "tipo")
@JsonSubTypes({
        @JsonSubTypes.Type(value = GastoBasico.class, name = "basico"),
        @JsonSubTypes.Type(value = GastoTarjeta.class, name = "debito"),
        @JsonSubTypes.Type(value = GastoCreditoCuotas.class, name = "credito"),
        @JsonSubTypes.Type(value = GastoPrestamo.class, name = "prestamo")
})
public class GastoBasico {

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    private Long id;
    private String persona;
    private String detalle;
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

    public String getDetalle() {
        return detalle;
    }

    public void setDetalle(String detalle) {
        this.detalle = detalle;
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
