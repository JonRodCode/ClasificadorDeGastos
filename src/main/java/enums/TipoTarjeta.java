package enums;

public enum TipoTarjeta {
    Titular("Titular"), Extension("Estensión");

    private String code;

    TipoTarjeta(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }
}
