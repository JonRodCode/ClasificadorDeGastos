package model;

import java.util.HashMap;
import java.util.List;
import org.bson.types.ObjectId;

public class Especificaciones {

    private HashMap<String, List<String>> fuenteDelGasto;
    private HashMap<String, List<String>> categorias;
    private HashMap<String, List<String>> determinaciones;
    private HashMap<String, List<GastoBasico>> excepcionesGlobales;
    private List<ObjectId> GastosConCuotasPendientes;

    public HashMap<String, List<String>> getFuenteDelGasto() {
        return fuenteDelGasto;
    }

    public void setFuenteDelGasto(HashMap<String, List<String>> fuenteDelGasto) {
        this.fuenteDelGasto = fuenteDelGasto;
    }

    public HashMap<String, List<String>> getCategorias() {
        return categorias;
    }

    public void setCategorias(HashMap<String, List<String>> categorias) {
        this.categorias = categorias;
    }

    public HashMap<String, List<String>> getDeterminaciones() {
        return determinaciones;
    }

    public void setDeterminaciones(HashMap<String, List<String>> determinaciones) {
        this.determinaciones = determinaciones;
    }

    public HashMap<String, List<GastoBasico>> getExcepcionesGlobales() {
        return excepcionesGlobales;
    }

    public void setExcepcionesGlobales(HashMap<String, List<GastoBasico>> excepcionesGlobales) {
        this.excepcionesGlobales = excepcionesGlobales;
    }

    public List<ObjectId> getGastosConCuotasPendientes() {
        return GastosConCuotasPendientes;
    }

    public void setGastosConCuotasPendientes(List<ObjectId> gastosConCuotasPendientes) {
        GastosConCuotasPendientes = gastosConCuotasPendientes;
    }
}
