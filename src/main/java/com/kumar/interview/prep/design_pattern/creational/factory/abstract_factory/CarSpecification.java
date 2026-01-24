package com.kumar.interview.prep.design_pattern.creational.factory.abstract_factory;

public interface CarSpecification {
    void display();
}

class NorthAmericaSpecification implements CarSpecification {
    public void display() {
        System.out.println("North America Car Specification: Safety features compliant with local regulations.");
    }
}

// Concrete Product for Europe Car Specification
class EuropeSpecification implements CarSpecification {
    public void display() {
        System.out.println("Europe Car Specification: Fuel efficiency and emissions compliant with EU standards.");
    }
}
