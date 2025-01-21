package service;

import model.GastoBasico;
import model.PedidoAClasificar;

import java.util.List;

public interface ClasificacionService {

    List<GastoBasico> clasificarGastosCompletos(PedidoAClasificar pedidoAClasificar);
    List<GastoBasico> clasificarGastosGenerales(PedidoAClasificar pedidoAClasificar);
    List<GastoBasico> clasificarGastosConExcepciones(PedidoAClasificar pedidoAClasificar);

}
