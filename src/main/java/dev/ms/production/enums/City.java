package dev.ms.production.enums;

public enum City {
    ZAGREB("Zagreb", "10000"),
    RIJEKA("Rijeka", "51000"),
    SPLIT("Split", "21000"),
    OSIJEK("Osijek", "31000");

    private String cityName;
    private String postalCode;

    City(String cityName, String postalCode) {
        this.cityName = cityName;
        this.postalCode = postalCode;
    }

    public String getCityName() {
        return cityName;
    }

    public String getPostalCode() {
        return postalCode;
    }
}
