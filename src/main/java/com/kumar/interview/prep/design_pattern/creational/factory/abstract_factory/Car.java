package com.kumar.interview.prep.design_pattern.creational.factory.abstract_factory;

public interface Car {

    void assemble();
}

class Sedan implements Car {
    public void assemble() {
        System.out.println("Assembling Sedan car.");
    }
}

// Concrete Product for Hatchback Car
class Hatchback implements Car {
    public void assemble() {
        System.out.println("Assembling Hatchback car.");
    }
}
