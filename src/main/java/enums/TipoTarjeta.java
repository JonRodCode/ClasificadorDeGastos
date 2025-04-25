package enums;

public enum TipoTarjeta {
    Titular("Titular"), Extensión("Extensión");

    private String code;

    TipoTarjeta(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }
}
