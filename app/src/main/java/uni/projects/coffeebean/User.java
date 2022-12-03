package uni.projects.coffeebean;

import java.util.ArrayList;
import java.util.List;

public class User {

    private String username;
    private String email;
    private String password;
    private List<Order> orders;

    public User( String username, String email, String password) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.orders=new ArrayList<>();
    }


    public String getUsername() {
        return username;
    }

    public User setUsername(String username) {
        this.username = username;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public User setEmail(String email) {
        this.email = email;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public User setPassword(String password) {
        this.password = password;
        return this;
    }

    public List<Order> getOrders() {
        return orders;
    }

    public User setOrders(List<Order> orders) {
        this.orders = orders;
        return this;
    }
}
