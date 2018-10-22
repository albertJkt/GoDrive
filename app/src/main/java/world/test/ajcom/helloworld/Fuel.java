package world.test.ajcom.helloworld;

public class Fuel {
    public Float amount;
    public Float price;
    public Float overallPrice;
    public String username;
    public String fuelID;

    public Fuel(){

    }

    public Fuel(Float amount, Float price, String username, String fuelID) {
        this.amount = amount;
        this.price = price;
        this.username = username;
        this.fuelID = fuelID;
    }

    public Float getAmount() {
        return amount;
    }

    public Float getPrice() {
        return price;
    }

    public Float getOverallPrice() {
        overallPrice = amount*price;
        return overallPrice;
    }

    public String getUsername() {
        return username;
    }

    public String getFuelID() {
        return fuelID;
    }
}
