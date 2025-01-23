package enums;


// Este enum lo usamos en validarExcepcionesGlobales, ya que son campos de una excepcion(gasto)
// y no son parte de las posibles caracteristicas que un usario puede marcar en una excepcion.
public enum CamposAOmitir {
    Monto("monto"), Marcado("marcado");

    private String code;

    CamposAOmitir(String code) {
            this.code = code;
        }

        public String getCode() {
            return code;
        }
}

