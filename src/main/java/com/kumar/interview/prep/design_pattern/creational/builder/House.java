package com.kumar.interview.prep.design_pattern.creational.builder;

import lombok.Getter;

/**
 * This class represents a House object that is created using the Builder pattern. The House has multiple optional
 * properties that can be set step by step.
 */

@Getter
public class House {
    // Required properties
    private final String address;
    private final int rooms;

    // Optional properties
    private final boolean hasGarage;
    private final boolean hasSwimmingPool;
    private final boolean hasGarden;
    private final String color;
    private final int squareFeet;

    /**
     * Private constructor to force usage of the Builder class.
     */
    private House(HouseBuilder builder) {
        this.address = builder.address;
        this.rooms = builder.rooms;
        this.hasGarage = builder.hasGarage;
        this.hasSwimmingPool = builder.hasSwimmingPool;
        this.hasGarden = builder.hasGarden;
        this.color = builder.color;
        this.squareFeet = builder.squareFeet;
    }

    @Override
    public String toString() {
        return "House{" + "address='" + address + '\'' + ", rooms=" + rooms + ", hasGarage=" + hasGarage
                + ", hasSwimmingPool=" + hasSwimmingPool + ", hasGarden=" + hasGarden + ", color='" + color + '\''
                + ", squareFeet=" + squareFeet + '}';
    }

    /**
     * Static nested Builder class for creating House instances.
     */
    public static class HouseBuilder {
        // Required properties
        private final String address;
        private final int rooms;

        // Optional properties with default values
        private boolean hasGarage = false;
        private boolean hasSwimmingPool = false;
        private boolean hasGarden = false;
        private String color = "White";
        private int squareFeet = 2000;

        /**
         * Constructor with required properties.
         *
         * @param address
         *            the address of the house
         * @param rooms
         *            the number of rooms
         */
        public HouseBuilder(String address, int rooms) {
            this.address = address;
            this.rooms = rooms;
        }

        /**
         * Add a garage to the house.
         *
         * @return the builder instance for method chaining
         */
        public HouseBuilder withGarage(boolean hasGarage) {
            this.hasGarage = hasGarage;
            return this;
        }

        /**
         * Add a swimming pool to the house.
         *
         * @return the builder instance for method chaining
         */
        public HouseBuilder withSwimmingPool(boolean hasSwimmingPool) {
            this.hasSwimmingPool = hasSwimmingPool;
            return this;
        }

        /**
         * Add a garden to the house.
         *
         * @return the builder instance for method chaining
         */
        public HouseBuilder withGarden(boolean hasGarden) {
            this.hasGarden = hasGarden;
            return this;
        }

        /**
         * Set the color of the house.
         *
         * @return the builder instance for method chaining
         */
        public HouseBuilder withColor(String color) {
            this.color = color;
            return this;
        }

        /**
         * Set the square footage of the house.
         *
         * @return the builder instance for method chaining
         */
        public HouseBuilder withSquareFeet(int squareFeet) {
            this.squareFeet = squareFeet;
            return this;
        }

        /**
         * Build and return the House instance.
         *
         * @return a new House instance
         */
        public House build() {
            return new House(this);
        }
    }
}
