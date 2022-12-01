package uni.projects.coffeebean;

public class Flavor {

    private String name;
    private boolean checked;

    protected Flavor(String name){
        this.name=name;
    }

    public String getName() {
        return name;
    }

    public boolean isChecked() {
        return checked;
    }
}
