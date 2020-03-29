package ee.blakcat.pacyorky.models;

public enum District {
    NONE ("not located"),
    TARTU ("Tartu");

   public String name;
    District(String name) {
        this.name=name;
    }

    public static District getDistrict (String location) {
        location=location.toLowerCase();
        if (location.contains("tartu")) return District.TARTU;
        else return District.NONE;
    }
}
