public class Store {
    public double Latitude;
    public double Longitude;
    public double price;
    public String businessType;
    public double duration;
    public String name;
    public Store(String name,double Latitude,double Longitude,double price, String businessType,double duration) {
        this.name=name;
        this.price=price;
        this.businessType=businessType;
        this.duration=duration;
        this.Longitude=Longitude;
        this.Latitude=Latitude;
    }

    public double getLatitude() {
        return Latitude;
    }

    public double getLongitude() {
        return Longitude;
    }

    public double getPrice() {
        return price;
    }

    public String getBusinessType() {
        return businessType;
    }

    public double getDuration() {
        return duration;
    }

    @Override
    public String toString() {
        return "Store{" +
                "Latitude=" + Latitude +
                ", Longitude=" + Longitude +
                ", price=" + price +
                ", businessType='" + businessType + '\'' +
                ", duration=" + duration +
                '}';
    }
}
