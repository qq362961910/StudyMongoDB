package enums;

public enum CoordinateType {

    HUMAN(1),

    BUILDING(2);


    private Integer value;

    public Integer getValue() {
        return value;
    }

    public CoordinateType setValue(Integer value) {
        this.value = value;
        return this;
    }

    CoordinateType(Integer value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "CoordinateType{" +
                "value='" + value + '\'' +
                "} " + super.toString();
    }
}
