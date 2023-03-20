package machine;

import java.util.Map;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.HashMap;

public class CoffeeMachineApplication {
    private final MachineSupply machine;
    private final ArrayList<Coffee> coffeeRecipes;

    public CoffeeMachineApplication(MachineSupply supply) {
        this.machine = supply;
        final Coffee espresso = new Coffee(1, 250, 0, 16, 4);
        final Coffee latte = new Coffee(2, 350, 75, 20, 7);
        final Coffee cappuccino = new Coffee(3, 200, 100, 12, 6);
        coffeeRecipes = new ArrayList<>();
        coffeeRecipes.add(espresso);
        coffeeRecipes.add(latte);
        coffeeRecipes.add(cappuccino);
    }


    public void execute() {
        Scanner scanner = new Scanner(System.in);
        String userAction;
        do {
            System.out.println("Write action (buy, fill, take, remaining, exit): ");
            userAction = scanner.nextLine();
            switch (userAction) {
                case "buy":
                    // run buy method
                    buyCoffeeMenu();
                    break;
                case "fill":
                    // run fill method
                    refillMachine();
                    break;
                case "take":
                    // run take method
                    int money = takeMoney();
                    System.out.printf(String.format("I gave you $%d\n", money));
                    break;
                case "remaining":
                    showMachineState();
                    break;
            }
        }
        while (!(userAction.equals("exit")));
    }


    /**
     * Gets the current state of Machine's supply
     */
    public void showMachineState() {
        System.out.printf(String.format("The coffee machine has:\n" +
                        "%d ml of water\n" +
                        "%d ml of milk\n" +
                        "%d g of coffee beans\n" +
                        "%d disposable cups\n" +
                        "$%d of money\n", this.machine.getWater(), this.machine.getMilk(),
                this.machine.getCoffeeBeans(), this.machine.getDisposableCups(), this.machine.getCash()));
    }

    /**
     * Handles coffee transactions
     * Known bug, this part terminates when anything else is entered.
     */
    private void buyCoffeeMenu() {
        String userChoice;
        System.out.println("What do you want to buy? 1 - espresso, 2 - latte, 3 - cappuccino, back - to main menu: ");
        Scanner sc = new Scanner(System.in);
        userChoice = sc.nextLine();
        int choice;
        switch (userChoice) {
            case "1":
            case "2":
            case "3":
                // buy coffee from selected choice
                // check if there is enough resources first
                choice = Integer.parseInt(userChoice);
                if (checkIfEnough(choice)) {
                    System.out.println("I have enough resources, making you a coffee!");
                    transaction(choice);
                } else {
                    String resources = findLackingResources(choice);
                    System.out.printf(String.format("Sorry not enough %s!\n", resources));
                }
                break;
            case "back":
                break;
        }
    }


    /**
     * @param recipeID if there is enough resources to make a coffee
     * @return boolean value indicating resources availability.
     */
    public boolean checkIfEnough(int recipeID) {
        // Check if there is enough for a single cup.
        Coffee coffee = coffeeRecipes.get(recipeID - 1);
        return (machine.getWater() - coffee.water >= 0) &&
                (machine.getMilk() - coffee.milk >= 0) &&
                (machine.getCoffeeBeans() - coffee.coffeeBeans >= 0) &&
                (machine.getDisposableCups() >= 1);
    }

    /**
     * @param recipeID item number on the menu
     * @return Specific lacking resource
     */

    public String findLackingResources(int recipeID) {
        String resources = "";
        Map<String, Integer> map = new HashMap<String, Integer>();
        Coffee coffee = coffeeRecipes.get(recipeID - 1);
        map.put("water",machine.getWater() - coffee.water);
        map.put("milk", machine.getMilk() - coffee.milk);
        map.put("coffee beans", machine.getCoffeeBeans() - coffee.coffeeBeans);
        map.put("disposable cups", machine.getDisposableCups() - 1);

        for (Map.Entry<String, Integer> entry : map.entrySet()) {
            if (entry.getValue() < 0) {
                resources = entry.getKey();
            }
        }
        return resources;
    }


    /**
     * Refills the Coffee Machine Supply
     */
    private void refillMachine() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Write how many ml of water you want to add:");
        this.machine.setWater(machine.getWater() + sc.nextInt());

        System.out.println("Write how many ml of milk you want to add:");
        this.machine.setMilk(machine.getMilk() + sc.nextInt());

        System.out.println("Write how many grams of coffee beans you want to add:");
        this.machine.setCoffeeBeans(machine.getCoffeeBeans() + sc.nextInt());

        System.out.println("Write how many disposable cups you want to add: ");
        this.machine.setDisposableCups(machine.getDisposableCups() + sc.nextInt());

    }

    /**
     * Withdraw the money from the Machine
     * @return money
     */
    private int takeMoney() {
        int money = this.machine.getCash();
        this.machine.setCash(0);
        return money;
    }

    /**
     * Updates Machine's supply based on transaction
     * @param recipeID Selected coffee choice
     */
    private void transaction(int recipeID) {
        Coffee coffee = coffeeRecipes.get(recipeID - 1);
        machine.setMilk(machine.getMilk() - coffee.milk);
        machine.setWater(machine.getWater() - coffee.water);
        machine.setCoffeeBeans(machine.getCoffeeBeans() - coffee.coffeeBeans);
        machine.setDisposableCups(machine.getDisposableCups() - 1);
        machine.setCash(machine.getCash() + coffee.cost);

    }

}