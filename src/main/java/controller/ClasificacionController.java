package controller;

import enums.CamposAOmitir;
import model.Especificaciones;
import model.GastoBasico;
import model.PedidoAClasificar;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import service.ClasificacionService;
import java.lang.reflect.Field;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin(origins = "*")
@Controller
public class ClasificacionController {

    @Autowired
    ClasificacionService service;

    @PostMapping(value = "clasificacionGeneral", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<GastoBasico>> clasificacionGeneral(@RequestBody PedidoAClasificar pedidoAClasificar){
        List<GastoBasico> gastos = service.clasificarGastosGenerales(pedidoAClasificar);
        return new ResponseEntity<List<GastoBasico>>(gastos,HttpStatus.OK);
    }

    @PostMapping(value = "clasificacionExcepcional", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<GastoBasico>> clasificacionExcepcional(@RequestBody PedidoAClasificar pedidoAClasificar){

        if (validarExcepcionesGlobales(pedidoAClasificar.getEspecificaciones())){
            List<GastoBasico> gastos = service.clasificarGastosConExcepciones(pedidoAClasificar);
            return new ResponseEntity<List<GastoBasico>>(gastos,HttpStatus.OK);
        }
        else{
            return new ResponseEntity<List<GastoBasico>>(pedidoAClasificar.getGastos(),HttpStatus.CONFLICT);
        }

    }

    @PostMapping(value = "clasificacionCompleta", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<GastoBasico>> clasificacionCompleta(@RequestBody PedidoAClasificar pedidoAClasificar){

        if (validarExcepcionesGlobales(pedidoAClasificar.getEspecificaciones())){
            List<GastoBasico> gastos = service.clasificarGastosCompletos(pedidoAClasificar);
            return new ResponseEntity<List<GastoBasico>>(gastos,HttpStatus.OK);
        }
        else{
            return new ResponseEntity<List<GastoBasico>>(pedidoAClasificar.getGastos(),HttpStatus.CONFLICT);
        }
    }

    private boolean validarExcepcionesGlobales(Especificaciones especificaciones){
        HashMap<String, List<GastoBasico>> excepcionesGlobales = especificaciones.getExcepcionesGlobales();

        if (excepcionesGlobales == null || excepcionesGlobales.isEmpty())
            return true;

        for (Map.Entry<String, List<GastoBasico>> excepcion : excepcionesGlobales.entrySet()) {

            // Esta validacion es para ver que la excepcion no tenga unicamente valor en el atributo "categoria"
            for (GastoBasico gasto : excepcion.getValue()){
                int contador = 0;  // Cantidad de atributos con valor
                boolean categoriaTieneValor = false;

                for (Field field : gasto.getClass().getDeclaredFields()) {
                    field.setAccessible(true); // Permite acceder a atributos privados
                    try {
                        Object value = field.get(gasto); //Obtenemos el valor, o sea la excepcion (Gasto)
                        String nameKey = field.getName();
                        if (value != null && !(value instanceof String && ((String) value).isBlank()) &&
                                //obviamos estos atributos de la excepcion
                                !nameKey.equals(CamposAOmitir.Monto.name()) && !nameKey.equals(CamposAOmitir.Marcado.name())) {
                            contador++;
                            if (field.getName().equals("categoria")) {
                                categoriaTieneValor = true;
                            }
                        }
                    }
                    // Esta en la firma, si o si hay que usar este try catch
                    catch (IllegalAccessException e) {
                        e.printStackTrace();
                    }
                }
                // Si el campo categoria tiene valor, pero es el unico campo con valor, no pasa la validacion.
                if (categoriaTieneValor && contador <= 1){
                    return false;
                }
            }
        }
        return true;
    }

}
