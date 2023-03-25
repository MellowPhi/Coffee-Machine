package machine;


public class CoffeeMachine {

    public static void main(String[] args) {
        CoffeeMachineApplication application = new CoffeeMachineApplication(new MachineSupply(400, 540, 120));
        application.execute();

    }

}