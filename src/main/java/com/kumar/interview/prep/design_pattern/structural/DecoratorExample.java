package com.kumar.interview.prep.design_pattern.structural;

/**
 * The decorator pattern can be thought of as a wrapper or more formally a way to enhance or extend the behavior of an
 * object dynamically. The pattern provides an alternative to subclassing when new functionality is desired.
 */
public class DecoratorExample {

    static void main() {
        Pizza pizza = new PlainPizza();

        System.out.println("Base Pizza Description " + pizza.getDescription());
        System.out.println("Base Pizza Cost " + pizza.getCost());

        PizzaDecorator cheesePizza = new CheeseDecorator(pizza);
        System.out.println("Cheese Pizza " + cheesePizza.getDescription());
        System.out.println("Cheese Cost " + cheesePizza.getCost());

        PizzaDecorator pepperoniPizza = new PepperoniDecorator(pizza);

        System.out.println("Pepperoni Pizza " + pepperoniPizza.getDescription());
        System.out.println("Pepperoni Pizza Cost " + pepperoniPizza.getCost());

    }
}

interface Pizza {
    String getDescription();

    double getCost();
}

class PlainPizza implements Pizza {

    @Override
    public String getDescription() {
        return "Plain Pizza";
    }

    @Override
    public double getCost() {
        return 5.0;
    }
}

abstract class PizzaDecorator implements Pizza {
    protected Pizza decoratedPizza;

    public PizzaDecorator(Pizza decoratedPizza) {
        this.decoratedPizza = decoratedPizza;
    }

}

class CheeseDecorator extends PizzaDecorator {

    public CheeseDecorator(Pizza decoratedPizza) {
        super(decoratedPizza);
    }

    @Override
    public String getDescription() {
        return decoratedPizza.getDescription() + ", Cheese";
    }

    @Override
    public double getCost() {
        return decoratedPizza.getCost() + 1.5;
    }
}

class PepperoniDecorator extends PizzaDecorator {

    public PepperoniDecorator(Pizza decoratedPizza) {
        super(decoratedPizza);
    }

    @Override
    public String getDescription() {
        return decoratedPizza.getDescription() + ",Pepperoni";
    }

    @Override
    public double getCost() {
        return decoratedPizza.getCost() + 2.5;
    }
}
