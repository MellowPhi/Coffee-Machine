package machine;

public class MachineSupply{
    private int water;
    private int milk;
    private int coffeeBeans;
    private int cash;
    private int disposableCups;

    public MachineSupply(int w, int m, int cb) {
        this.water = w;
        this.milk = m;
        this.coffeeBeans = cb;
        this.cash = 550;
        this.disposableCups = 9;
    }

    public int getWater() {
        return water;
    }

    public int getMilk() {
        return milk;
    }

    public int getCoffeeBeans() {
        return coffeeBeans;
    }

    public int getCash() {
        return cash;
    }

    public int getDisposableCups() {
        return disposableCups;
    }

    public void setWater(int w) {
        this.water = w;
    }

    public void setMilk(int m) {
        this.milk = m;
    }
    public void setCoffeeBeans(int cb) {
        this.coffeeBeans = cb;
    }

    public void setCash(int c) {
        this.cash = c;
    }

    public void setDisposableCups(int dc) {
        this.disposableCups = dc;
    }


}