public class Car {
    private String brand;
    private String model;
    private int year;
    private String pollutionIndex;
    private String fuelType; 
    private String bodyType;

    public Car(String brand, String model, int year, String pollutionIndex, String fuelType, String bodyType) {
        this.brand = brand;
        this.model = model;
        this.year = year;
        this.pollutionIndex = pollutionIndex;
        this.fuelType = fuelType;
        this.bodyType = bodyType;
    }

  
    public String getFuelType() {
        return fuelType;
    }

    public String getBodyType() {
        return bodyType;
    }


    public String getBrand() {
        return brand;
    }

    public String getModel() {
        return model;
    }

    public int getYear() {
        return year;
    }

    public String getPollutionIndex() {
        return pollutionIndex;
    }
}
