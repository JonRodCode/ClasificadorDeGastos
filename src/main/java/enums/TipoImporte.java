package enums;

public enum TipoImporte {
    Gasto("Gasto"), Reintegro("Reintegro");

    private String code;

    TipoImporte(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }
}
