package world.test.ajcom.helloworld;

public class Service {
    public String serviceType;
    public Float price;
    public String username;
    public String serviceID;

    public Service ()
    {

    }

    public Service(String serviceType, Float price, String username, String serviceID) {
        this.serviceType = serviceType;
        this.price = price;
        this.username = username;
        this.serviceID = serviceID;
    }

    public String getServiceType() {
        return serviceType;
    }

    public Float getPrice() {
        return price;
    }

    public String getUsername() {
        return username;
    }

    public String getServiceID() {
        return serviceID;
    }
}
