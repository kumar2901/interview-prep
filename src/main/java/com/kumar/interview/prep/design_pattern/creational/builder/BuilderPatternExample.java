package com.kumar.interview.prep.design_pattern.creational.builder;

/**
 * Example demonstrating the Builder Design Pattern.
 *
 * <p>
 * The Builder pattern is useful when: - An object has many optional attributes - The constructor would have too many
 * parameters (telescoping constructor problem) - We want to create complex objects step by step - We want to ensure
 * immutability
 *
 * <p>
 * Benefits: - Cleaner and more readable code - Reduces constructor overloading - Makes the object immutable - Allows
 * step-by-step object creation
 */
public class BuilderPatternExample {

    static void main(String[] args) {
        // Example 1: Create a simple house with required fields only
        System.out.println("=== Example 1: Simple House ===");

        House simpleHouse = new House.HouseBuilder("123 Main Street", 3).withColor("Blue").build();
        System.out.println(simpleHouse);
        System.out.println();

        // Example 2: Create a luxury house with all features
        System.out.println("=== Example 2: Luxury House ===");
        House luxuryHouse = new House.HouseBuilder("456 Oak Avenue", 5).withGarage(true).withSwimmingPool(true)
                .withGarden(true).withColor("Gold").withSquareFeet(8000).build();
        System.out.println(luxuryHouse);
        System.out.println();

        // Example 3: Create a house with some features
        System.out.println("=== Example 3: Standard House with Features ===");
        House standardHouse = new House.HouseBuilder("789 Elm Street", 4).withGarage(true).withGarden(true)
                .withColor("White").withSquareFeet(3500).build();
        System.out.println(standardHouse);
        System.out.println();

        // Example 4: Demonstrate immutability - trying to create another house
        // with same builder but different values shows immutability
        System.out.println("=== Example 4: Demonstrating Immutability ===");
        House.HouseBuilder builder = new House.HouseBuilder("999 Pine Road", 6);
        House house4a = builder.withGarage(true).withColor("Red").build();
        House house4b = new House.HouseBuilder("999 Pine Road", 6).withGarage(true).withColor("Green").build();

        System.out.println("House 4a: " + house4a);
        System.out.println("House 4b: " + house4b);
        System.out.println("Both houses are independent objects (immutable)");
        System.out.println();

        // Example 5: Create with only required parameters (minimal example)
        System.out.println("=== Example 5: Minimal House (Required Fields Only) ===");
        House minimalHouse = new House.HouseBuilder("111 Maple Lane", 2).build();
        System.out.println(minimalHouse);
        System.out.println("Notice: Uses default values for optional fields (hasGarage=false, color=White, etc.)");
    }
}
