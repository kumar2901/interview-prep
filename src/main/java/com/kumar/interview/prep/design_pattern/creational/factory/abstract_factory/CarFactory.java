package com.kumar.interview.prep.design_pattern.creational.factory.abstract_factory;

public interface CarFactory {
    Car createCar();

    CarSpecification createSpecification();
}
