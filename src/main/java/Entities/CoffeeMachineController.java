package Entities;

import Tools.CoffeeMachineException;
import Tools.State;

public class CoffeeMachineController {

    private long cups = 9;
    private long beans = 120;
    private long milk = 540;
    private long water = 400;
    private long money = 550;

    public long getCups() {
        return cups;
    }

    public long getBeans() {
        return beans;
    }

    public long getMilk() {
        return milk;
    }

    public long getWater() {
        return water;
    }

    public long getMoney() {
        return money;
    }

    public State handleRequest(String request) {
        switch (request) {
            case "buy":
                return State.BUYING;
            case "fill":
                return State.REFILLING;
            case "take":
                return State.GIVING_MONEY;
            case "remaining":
                return State.PRINTING_INFORMATION;
            case "exit":
                return State.EXITING;
            default:
                return State.HANDLING_ERROR;
        }
    }

    public void brewLatte() throws CoffeeMachineException {
        if (cups - 1 < 0) {
            throw new CoffeeMachineException("Sorry, not enough cups!");
        }
        if (beans - 20 < 0) {
            throw new CoffeeMachineException("Sorry, not enough beans!");
        }
        if (water - 350 < 0) {
            throw new CoffeeMachineException("Sorry, not enough water!");
        }
        if (milk - 75 < 0) {
            throw new CoffeeMachineException("Sorry, not enough milk!");
        }
        cups -= 1;
        beans -= 20;
        milk -= 75;
        water -= 350;
        money += 7;
    }

    public void brewEspresso() throws CoffeeMachineException {
        if (cups - 1 < 0) {
            throw new CoffeeMachineException("Sorry, not enough cups!");
        }
        if (beans - 16 < 0) {
            throw new CoffeeMachineException("Sorry, not enough beatns!");
        }
        if (water - 250 < 0) {
            throw new CoffeeMachineException("Sorry, not enough water!");
        }
        cups -= 1;
        beans -= 16;
        water -= 250;
        money += 4;
    }

    public void brewCappuccino() throws CoffeeMachineException {
        if (cups - 1 < 0) {
            throw new CoffeeMachineException("Sorry, not enough cups!");
        }
        if (beans - 12 < 0) {
            throw new CoffeeMachineException("Sorry, not enough beans!");
        }
        if (water - 200 < 0) {
            throw new CoffeeMachineException("Sorry, not enough water!");
        }
        if (milk - 100 < 0) {
            throw new CoffeeMachineException("Sorry, not enough milk!");
        }
        cups -= 1;
        beans -= 12;
        milk -= 100;
        water -= 200;
        money += 6;
    }

    public void refillWater(long ml) {
        water += ml;
    }

    public void refillMilk(long ml) {
        milk += ml;
    }

    public void refillBeans(long g) {
        beans += g;
    }

    public void refillCups(long units) {
        cups += units;
    }

    public long giveMoney() {
        long tmp = money;
        money = 0;
        return tmp;
    }
}