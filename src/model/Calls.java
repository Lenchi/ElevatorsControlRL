package model;

import java.util.*;

public class Calls {

    public Person[] floors;
    public static int[] personFloorNum;
    public int floorNumber;
    public int callNumber = 0;
    public int destination;

    public Calls() {
        this.floorNumber = main.Main.FLOOR_NUMBER;
        initializeFloors();
        personFloorNum = new int[floorNumber];
    }

    public void initializeFloors() {
        floors = new Person[floorNumber];
        for (int i = 0; i < floorNumber; i++) {
            floors[i] = new Person(0, 0, 0);
        }
    }

    public void callGeneration(int steps) {
        Random destinationFloor = new Random();
        //floor number array generating
        for (int i = 0; i < floors.length; i++) {
            if (floors[i].getPersonCallFloor() == 0) {
                double a = Math.random() * 100;
                if (a < 8) {
                    destination = destinationFloor.nextInt(floorNumber) + 1;//random destination floor

                    if (i != (destination - 1)) {
                        floors[i] = new Person(steps, (i + 1), destination);
                        //System.out.println("Person was iniz: " + floors[i].toString());
                    }
                }
            }
        }
        //Current floors  with calls are printing
        //System.out.print("Current floors with calls are \r\n");
        for (int p = 0; p <= floors.length - 1; p++) {
            if (floors[p].getPersonCurrFloor() != 0) {
                callNumber++;
            }
        }
    }
}

