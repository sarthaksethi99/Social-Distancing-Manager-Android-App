package a.com;

public class Details {
    private String district;
    private String state;


    public Details(String state, String districts) {
        this.state = state;
        this.district = districts;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getDistricts() {
        return district;
    }

    public void setDistricts(String districts) {
        this.district = districts;
    }
}
