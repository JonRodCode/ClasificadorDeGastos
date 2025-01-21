package model;

import java.util.List;

public class PedidoAClasificar {

    private Especificaciones especificaciones;
    private List<GastoBasico> gastos;

    public Especificaciones getEspecificaciones() {
        return especificaciones;
    }

    public void setEspecificaciones(Especificaciones especificaciones) {
        this.especificaciones = especificaciones;
    }

    public List<GastoBasico> getGastos() {
        return gastos;
    }

    public void setGastos(List<GastoBasico> gastos) {
        this.gastos = gastos;
    }
}
