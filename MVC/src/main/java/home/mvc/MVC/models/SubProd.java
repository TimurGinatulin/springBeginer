package home.mvc.MVC.models;

public class SubProd {
    private String title;
    private int cost;

    public SubProd(String title, int cost) {
        this.title = title;
        this.cost = cost;
    }

    public String getTitle() {
        return title;
    }

    public int getCost() {
        return cost;
    }
}
