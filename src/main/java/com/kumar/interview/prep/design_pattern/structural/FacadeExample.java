package com.kumar.interview.prep.design_pattern.structural;

import lombok.Getter;

public class FacadeExample {
    static void main() {

        AutopilotFacade autopilotFacade = new AutopilotFacade(new BoeingAltitudeMonitor(), new BoeingEngineController(),
                new BoeingFuelMonitor(), new BoeingNavigationSystem());

        autopilotFacade.autopilotOn();
        autopilotFacade.autopilotOff();

    }
}

class AutopilotFacade {

    private final BoeingAltitudeMonitor altitudeMonitor;
    private final BoeingEngineController engineController;
    private final BoeingFuelMonitor feulMonitor;
    private final BoeingNavigationSystem navigationSystem;

    public AutopilotFacade(BoeingAltitudeMonitor altitudeMonitor, BoeingEngineController engineController,
            BoeingFuelMonitor feulMonitor, BoeingNavigationSystem navigationSystem) {
        this.altitudeMonitor = altitudeMonitor;
        this.engineController = engineController;
        this.feulMonitor = feulMonitor;
        this.navigationSystem = navigationSystem;
    }

    public void autopilotOn() {
        altitudeMonitor.autoMonitor();
        engineController.setEngineSpeed(700);
        navigationSystem.setDirectionBasedOnSpeedAndFeul(engineController.getEngineSpeed(),
                feulMonitor.getRemainingFeulInGallons());
    }

    public void autopilotOff() {
        altitudeMonitor.turnOff();
        engineController.turnOff();
        navigationSystem.turnOff();
        feulMonitor.turnOff();
    }
}

class BoeingAltitudeMonitor {
    public void autoMonitor() {
        System.out.println("Boeing Altitude Auto Monitor");
    }

    public void turnOff() {
        System.out.println("Altitude Monitor Turned Off");
    }
}

@Getter
class BoeingEngineController {
    private int engineSpeed;

    public void setEngineSpeed(int speed) {

        this.engineSpeed = speed;
        System.out.println("Engine Speed set to " + speed);
    }

    public void turnOff() {
        System.out.println("Engine turned off");
    }
}

class BoeingFuelMonitor {
    public double getRemainingFeulInGallons() {

        double remainingFuel = 4000.7;
        System.out.println("Remaining Fuels in Gallons are" + remainingFuel);
        return remainingFuel;
    }

    public void turnOff() {
        System.out.println("Fuel Monitor is Turned off");
    }
}

@Getter
class BoeingNavigationSystem {

    private String direction;
    public void setDirectionBasedOnSpeedAndFeul(int engineSpeed, double remainingFeulInGallons) {
        if (engineSpeed > 200 && remainingFeulInGallons > 500.0) {
            direction = "East";
        } else {
            direction = "west";
        }
        System.out.println("Direction is set to " + direction);
    }

    public void turnOff() {
        System.out.println("Navigation System is turned off");
    }
}
