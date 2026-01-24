package com.kumar.interview.prep.design_pattern.creational.factory.abstract_factory;

public class EuropeCarFactory implements CarFactory {
    @Override
    public Car createCar() {
        return new Hatchback();
    }

    @Override
    public CarSpecification createSpecification() {
        return new EuropeSpecification();
    }
}
