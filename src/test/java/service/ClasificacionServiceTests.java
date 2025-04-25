package service;

import enums.TipoExcepcion;
import model.*;
import org.junit.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.*;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

@SpringBootTest
public class ClasificacionServiceTests {

    @InjectMocks
    private ClasificacionServiceImpl clasificacionService;

    @Mock
    private PedidoAClasificar pedidoAClasificar;

    @Mock
    private Especificaciones especificaciones;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testClasificarGastosConExcepciones() {
        // Arrange
        List<GastoBasico> gastos = new ArrayList<>();
        GastoBasico gasto1 = new GastoBasico();
        gasto1.setExcepcion(TipoExcepcion.Nula);
        gasto1.setPersona("Persona1");
        gasto1.setDetalle("Detalle1");
        gasto1.setFuenteDelGasto("Fuente1");
        gasto1.setCategoria("Categoria1");
        //gasto1.setTipoDeImporte("Tipo1");

        GastoBasico gasto2 = new GastoBasico();
        gasto2.setExcepcion(TipoExcepcion.Puntual);
        gasto2.setPersona("Persona2");
        gasto2.setDetalle("Detalle2");
        gasto2.setFuenteDelGasto("Fuente2");
        gasto2.setCategoria("Categoria2");
       // gasto2.setTipoDeImporte("Tipo2");

        gastos.add(gasto1);
        gastos.add(gasto2);

        Map<String, List<GastoBasico>> excepcionesGlobales = new HashMap<>();
        excepcionesGlobales.put("Excepcion1", Collections.singletonList(gasto1));

        when(pedidoAClasificar.getEspecificaciones()).thenReturn(especificaciones);
        when(pedidoAClasificar.getGastos()).thenReturn(gastos);
       // when(especificaciones.getExcepcionesGlobales()).thenReturn(excepcionesGlobales);

        // Act
        List<GastoBasico> result = clasificacionService.clasificarGastosConExcepciones(pedidoAClasificar);

        // Assert
        assertEquals(2, result.size());
        assertEquals(TipoExcepcion.Global, result.get(0).getExcepcion());
        assertEquals("Excepcion1", result.get(0).getDeterminacion());
        assertEquals(TipoExcepcion.Puntual, result.get(1).getExcepcion());
    }

    @Test
    public void testClasificarGastosGenerales() {
        // Arrange
        List<GastoBasico> gastos = new ArrayList<>();
        GastoBasico gasto1 = new GastoBasico();
        gasto1.setExcepcion(TipoExcepcion.Nula);
        gasto1.setFuenteDelGasto("Fuente1");
        gasto1.setCategoria("Categoria1");

        GastoCreditoCuotas gasto2 = new GastoCreditoCuotas();
        gasto2.setExcepcion(TipoExcepcion.Nula);
        gasto2.setNombreConsumo("Consumo1");
        gasto2.setFuenteDelGasto("Fuente2");
        gasto2.setCategoria("Categoria2");

        gastos.add(gasto1);
        gastos.add(gasto2);

        Map<String, List<String>> fuenteDelGasto = new HashMap<>();
        fuenteDelGasto.put("Fuente1", Collections.singletonList("Consumo1"));

        Map<String, List<String>> categorias = new HashMap<>();
        categorias.put("Categoria1", Collections.singletonList("Fuente1"));

        Map<String, List<String>> determinaciones = new HashMap<>();
        determinaciones.put("Determinacion1", Collections.singletonList("Categoria1"));

        when(pedidoAClasificar.getEspecificaciones()).thenReturn(especificaciones);
        when(pedidoAClasificar.getGastos()).thenReturn(gastos);
       /* when(especificaciones.getFuenteDelGasto()).thenReturn(fuenteDelGasto);
        when(especificaciones.getCategorias()).thenReturn(categorias);
        when(especificaciones.getDeterminaciones()).thenReturn(determinaciones);*/

        // Act
        List<GastoBasico> result = clasificacionService.clasificarGastosGenerales(pedidoAClasificar);

        // Assert
        assertEquals(2, result.size());
        assertEquals("Fuente1", result.get(0).getFuenteDelGasto());
        assertEquals("Categoria1", result.get(0).getCategoria());
        assertEquals("Determinacion1", result.get(0).getDeterminacion());
        assertEquals("Fuente2", result.get(1).getFuenteDelGasto());
        assertEquals("Categoria2", result.get(1).getCategoria());
    }
}