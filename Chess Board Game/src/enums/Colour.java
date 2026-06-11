package enums;

public enum Colour {

    BLACK("B"),
    WHITE("W");

    private String code;

    Colour(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }
}
