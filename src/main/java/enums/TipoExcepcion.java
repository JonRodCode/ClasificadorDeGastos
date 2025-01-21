package enums;

public enum TipoExcepcion {
    Global("Global"), Puntual("Puntual"), Nula("Nula");

    private String code;

    TipoExcepcion(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }
}
