package enums;

public enum LbsExtensionType {

    A("a"),

    B("b");

    private String value;

    public String getValue() {
        return value;
    }

    public LbsExtensionType setValue(String value) {
        this.value = value;
        return this;
    }

    LbsExtensionType(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "LbsExtensionType{" +
                "value='" + value + '\'' +
                "} " + super.toString();
    }
}
