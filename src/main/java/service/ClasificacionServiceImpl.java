package service;

import enums.TipoExcepcion;
import model.*;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Objects;

@Service
public class ClasificacionServiceImpl implements ClasificacionService {

    //Estos metodos van a clasificar los gastos recibidos segun las especificaciones adjuntas.
    @Override
    public List<GastoBasico> clasificarGastosCompletos(PedidoAClasificar pedidoAClasificar) {

        //Usamos los metodos de clasificacion con excepciones y generales para realizar la clasificacion completa
        PedidoAClasificar pedido = new PedidoAClasificar();
        pedido.setEspecificaciones(pedidoAClasificar.getEspecificaciones());

        pedido.setGastos(clasificarGastosConExcepciones(pedidoAClasificar));
        pedido.setGastos(clasificarGastosGenerales(pedido));

        return pedido.getGastos();
    }

    @Override
    public List<GastoBasico> clasificarGastosConExcepciones(PedidoAClasificar pedidoAClasificar) {

        Especificaciones especificaciones = pedidoAClasificar.getEspecificaciones();
        List<GastoBasico> gastos = pedidoAClasificar.getGastos();

        // Si las especificaciones estan vacias, simplemente devolvemos la lista de gastos.
        if (especificaciones.getExcepcionesGlobales().isEmpty() || especificaciones.getExcepcionesGlobales() == null){
            return gastos;
        }

        // Aca clasificamos los gastos con excepciones globales

        for (GastoBasico gasto : gastos) {
            if (!gasto.getExcepcion().equals(TipoExcepcion.Puntual)) {

                for (Map.Entry<String, List<GastoBasico>> determinacion : especificaciones.getExcepcionesGlobales().entrySet()) {

                    //Si el gasto coincide con una de las excepciones lo clasificamos en el grupo de la excepcion
                    if (hayCoincidencia(gasto, determinacion.getValue())) {
                        gasto.setDeterminacion(determinacion.getKey());
                        gasto.setExcepcion(TipoExcepcion.Global);

                        // Pequeño detalle, si la categoria esta vacia lo clasificamos normalmente
                        if (gasto.getCategoria() == null || gasto.getCategoria().isEmpty()) {
                            gasto.setCategoria(clasificacionPorCategoria(especificaciones, gasto));
                        }
                        break;
                    }
                }
            }
        }
        return gastos;
    }

    @Override
    public List<GastoBasico> clasificarGastosGenerales(PedidoAClasificar pedidoAClasificar) {

        Especificaciones especificaciones = pedidoAClasificar.getEspecificaciones();
        List<GastoBasico> gastos = pedidoAClasificar.getGastos();

        // Empezamos con la clasificacion general
        for (GastoBasico gasto : gastos) {
            // Vamos a clasificar los gastos que no esten marcados como Excepcion
            if (gasto.getExcepcion().equals(TipoExcepcion.Nula)) {

                // La 1ra clasificacion es la fuenteDelGasto segun el nombre del consumo, aplica solo a las tarjetas
                if (gasto instanceof GastoTarjeta) {
                    for (Map.Entry<String, List<String>> fuenteDelGasto : especificaciones.getFuenteDelGasto().entrySet()) {

                        if (fuenteDelGasto.getValue().contains(((GastoTarjeta) gasto).getNombreConsumo())) {
                            gasto.setFuenteDelGasto(fuenteDelGasto.getKey());
                            break;
                        }
                    }
                }

                // La 2da clasificacion es la de categoria, aplica a todos los gastos.
                // Esta en una funcion fuera del scope porque tambien es usada por la clasificacion por excepciones
                gasto.setCategoria(clasificacionPorCategoria(especificaciones, gasto));

                // La 3ra clasificacion es la determinacion de gasto equitativo, igualitario o personal
                for (Map.Entry<String, List<String>> determinacion : especificaciones.getDeterminaciones().entrySet()) {
                    if (determinacion.getValue().contains(gasto.getCategoria())) {
                        gasto.setDeterminacion(determinacion.getKey());
                        break;
                    }
                }
            }
        }
        // Al llegar a este final ya esta todo calificado.
        return gastos;
    }

    private boolean hayCoincidencia(GastoBasico gasto, List<GastoBasico> excepciones) {

        for (GastoBasico excepcion : excepciones) {

            // En este if vamos a ver si la excepcion no tiene diferencias de sus datos con la del gasto
            // Obviamos si el dato es null o si esta vacio, lo que importa es si es distinto a eso

            if ((Objects.equals(gasto.getPersona(), excepcion.getPersona()) || excepcion.getPersona() == null || excepcion.getPersona().isEmpty()) &&
                    (Objects.equals(gasto.getDetalleConsumo(), excepcion.getDetalleConsumo()) || excepcion.getDetalleConsumo() == null || excepcion.getDetalleConsumo().isEmpty()) &&
                    (Objects.equals(gasto.getFuenteDelGasto(), excepcion.getFuenteDelGasto()) || excepcion.getFuenteDelGasto() == null || excepcion.getFuenteDelGasto().isEmpty()) &&
                    (Objects.equals(gasto.getCategoria(), excepcion.getCategoria()) || excepcion.getCategoria() == null || excepcion.getCategoria().isEmpty()) &&
                    (Objects.equals(gasto.getTipoDeImporte(), excepcion.getTipoDeImporte()) || excepcion.getTipoDeImporte() == null) &&
                    (Objects.equals(gasto.getFecha(), excepcion.getFecha()) || excepcion.getFecha() == null)) {

                // Corroboramos si es un GastoTarjeta
                if (gasto instanceof GastoTarjeta && excepcion instanceof GastoTarjeta) {
                    GastoTarjeta gTarjet = (GastoTarjeta) gasto;
                    GastoTarjeta excepTarjet = (GastoTarjeta) excepcion;
                    if ((Objects.equals(gTarjet.getTarjeta(), excepTarjet.getTarjeta()) || excepTarjet.getTarjeta() == null || excepTarjet.getTarjeta().isEmpty()) &&
                            (Objects.equals(gTarjet.getTipoTarjeta(), excepTarjet.getTipoTarjeta()) || excepTarjet.getTipoTarjeta() == null) &&
                            (Objects.equals(gTarjet.getaNombreDe(), excepTarjet.getaNombreDe()) || excepTarjet.getaNombreDe() == null || excepTarjet.getaNombreDe().isEmpty()) &&
                            (Objects.equals(gTarjet.getBanco(), excepTarjet.getBanco()) || excepTarjet.getBanco() == null || excepTarjet.getBanco().isEmpty()) &&
                            (gTarjet.getNumFinalTarjeta() == excepTarjet.getNumFinalTarjeta() || excepTarjet.getNumFinalTarjeta() == 0) &&
                            (Objects.equals(gTarjet.getNombreConsumo(), excepTarjet.getNombreConsumo()) || excepTarjet.getNombreConsumo() == null || excepTarjet.getNombreConsumo().isEmpty())) {

                        return true;
                    }
                }
                // Si no es un GastoTarjeta, corroboramos si es un GastoPrestamo
                else if (gasto instanceof GastoPrestamo && excepcion instanceof GastoPrestamo) {
                    GastoPrestamo gPrestamo = (GastoPrestamo) gasto;
                    GastoPrestamo excepPrestamo = (GastoPrestamo) excepcion;

                    if (Objects.equals(gPrestamo.getPrestamoDe(), excepPrestamo.getPrestamoDe()) ||
                            excepPrestamo.getPrestamoDe().isEmpty() ||
                            excepPrestamo.getPrestamoDe() == null) {
                        return true;
                    }
                }
                // Si no es ni GastoTarjeta ni GastoPrestamo, pero pasó las validaciones anteriores es un GastoBasico que matcheó con algo
                else
                {
                    return true;
                }
            }
        }
        return false;
    }

    private String clasificacionPorCategoria(Especificaciones especificaciones, GastoBasico gasto) {

        // Este metodo es usado en los metodos de clasificacion general y con excepciones
        for (Map.Entry<String, List<String>> categoria : especificaciones.getCategorias().entrySet()) {
            if (categoria.getValue().contains(gasto.getFuenteDelGasto())) {
                gasto.setCategoria(categoria.getKey());
                break;
            }
        }
        return gasto.getCategoria();
    }
}