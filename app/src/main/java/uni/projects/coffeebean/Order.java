package uni.projects.coffeebean;


import java.util.List;

public class Order {

    private String type;
    private String size;
    private String style;
    private List<String> additions;
    private String special;

    public Order(String type, String size, String style, List<String> additions, String special) {
        this.type = type;
        this.size = size;
        this.style = style;
        this.additions = additions;
        this.special = special;
    }

}
