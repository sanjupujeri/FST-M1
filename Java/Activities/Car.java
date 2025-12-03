package activities;


public class Car {

    String color;
    String transmission;
    int make;
    int tyres;
    int doors;

    // Constructor to set default values
    public Car() {
        this.tyres = 4;
        this.doors = 4;
    }

    // Method to show all characteristics
    public void displayCharacteristics() {
        System.out.println("Car Make: " + make);
        System.out.println("Car Color: " + color);
        System.out.println("Transmission: " + transmission);
        System.out.println("Tyres: " + tyres);
        System.out.println("Doors: " + doors);
    }

    public void accelerate() {
        System.out.println("Car is moving forward.");
    }

    public void brake() {
        System.out.println("Car has stopped.");
    }
}
