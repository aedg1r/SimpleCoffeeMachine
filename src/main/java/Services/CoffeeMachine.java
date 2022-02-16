package Services;

import Entities.CoffeeMachineController;
import Tools.CoffeeMachineException;
import Tools.State;

import java.util.Scanner;

public class CoffeeMachine {
    private final CoffeeMachineController controller = new CoffeeMachineController();
    private State state = State.WAITING_COMMAND;

    public void execute(String request) {
        if (state == State.WAITING_COMMAND) {
            state = controller.handleRequest(request);
        }

        switch (state) {
            case HANDLING_ERROR:
                System.out.println("Invalid input");
                state = State.WAITING_COMMAND;
                break;
            case BUYING:
                System.out.println("What do you want to buy? 1 - espresso, 2 - latte, 3 - cappuccino, back - to main menu: ");
                state = State.MAKING_COFFEE;
                break;
            case MAKING_COFFEE:
                try {
                    switch (request) {
                        case "1" -> controller.brewEspresso();
                        case "2" -> controller.brewLatte();
                        case "3" -> controller.brewCappuccino();
                        default -> {
                        }
                    }
                    System.out.println("I have enough resources, making you a coffee!");
                } catch (CoffeeMachineException e) {
                    System.out.println(e.getMessage());
                }
                state = State.WAITING_COMMAND;
                break;
            case REFILLING:
                state = State.REFILLING_WATER;
                System.out.println("Write how many ml of water you want to add: ");
                break;
            case REFILLING_WATER:
                controller.refillWater(Integer.parseInt(request));
                state = State.REFILLING_MILK;
                System.out.println("Write how many ml of milk you want to add:");
                break;
            case REFILLING_MILK:
                controller.refillMilk(Integer.parseInt(request));
                state = State.REFILLING_BEANS;
                System.out.println("Write how many grams of coffee beans you want to add: ");
                break;
            case REFILLING_BEANS:
                controller.refillBeans(Integer.parseInt(request));
                state = State.REFILLING_CUPS;
                System.out.println("Write how many disposable cups of coffee you want to add: ");
                break;
            case REFILLING_CUPS:
                controller.refillCups(Integer.parseInt(request));
                state = State.WAITING_COMMAND;
                break;
            case GIVING_MONEY:
                long cash = controller.giveMoney();
                System.out.println("I gave you " + cash);
                state = State.WAITING_COMMAND;
                break;
            case PRINTING_INFORMATION:
                long water = controller.getWater();
                long milk = controller.getMilk();
                long beans = controller.getBeans();
                long cups = controller.getCups();
                long money = controller.getMoney();
                System.out.println("The coffee machine has:\n" +
                        water + " ml of water\n" +
                        milk + " ml of milk\n" +
                        beans + " g of coffee beans\n" +
                        cups + " disposable cups\n" +
                        "$" + money + " of money\n");
                state = State.WAITING_COMMAND;
                break;
            case EXITING:
                break;
            default:
                state = State.WAITING_COMMAND;
                break;
        }
    }

    public static void main(String[] args) {
        CoffeeMachine coffeeMachine = new CoffeeMachine();
        Scanner scanner = new Scanner(System.in);
        while (coffeeMachine.state != State.EXITING) {
            if (coffeeMachine.state == State.WAITING_COMMAND) {
                System.out.println("Write action (buy, fill, take, remaining, exit): ");
            }
            String request = scanner.nextLine();
            coffeeMachine.execute(request);
        }
    }
}


