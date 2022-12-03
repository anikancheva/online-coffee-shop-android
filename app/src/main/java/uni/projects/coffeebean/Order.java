package uni.projects.coffeebean;

import java.util.ArrayList;

public class Order {

    private String type;
    private String size;
    private String style;
    private ArrayList<String> additions;
    private String special;

    public Order(String type, String size, String style, ArrayList<String> additions, String special) {
        this.type = type;
        this.size = size;
        this.style = style;
        this.additions = additions;
        this.special = special;
    }


}
