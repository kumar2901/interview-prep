package com.kumar.interview.prep.design_pattern.creational.factory.abstract_factory;

public class NorthAmericaCarFactory implements CarFactory {
    @Override
    public Car createCar() {
        return new Sedan();
    }

    @Override
    public CarSpecification createSpecification() {
        return new NorthAmericaSpecification();
    }
}
