package model.logic;

import java.util.Iterator;
import model.Person;

public class ElevatorLogic extends Elevator {

    public void removingPersonFromEl() {
        System.out.println("Start to remove!");
        if (!personsInElevator.isEmpty()) {
            if (elDestinationFloors.contains(elCurrentFloor)) {
                Iterator<Person> personsInElevatorIt = personsInElevator.iterator();
                int countRemovedPersons = 0;
                while (personsInElevatorIt.hasNext()) {
                    Person person = personsInElevatorIt.next();
                    if (person.getPersonDestFloor() == elCurrentFloor) {
                        personsInElevatorIt.remove();
                        countRemovedPersons++;
                        System.out.println("    Remove : " + person.toString());
                    }
                }
                elDestinationFloors.remove(elCurrentFloor);
                System.out.println("Elevator REMOVED " + countRemovedPersons //    + " person: \n" + calls.floors[elCurrentFloor - 1]
                );
            }
            System.out.println("Elevator now has " + personsInElevator.size() + " people");
        }
    }
//   public void addingPersonInEl() {
//        elFull();
//
//        if (fulling != ElevatorFullingEnum.FULL) {
//            // if (personNumber == 0) {
//            if (personsInElevator.isEmpty()) {
//                tryToAddPerson();
//            } else {
//
//                Person personToAdd = calls.floors[elCurrentFloor - 1];
//
//                if (elevatorsMove == ElevatorMovingsEnum.UP) {
//                    System.out.println("_____fB = " + calls.fB[elCurrentFloor - 1]);
//                    if (calls.fB[elCurrentFloor - 1] != null
//                            && calls.fB[elCurrentFloor - 1] == Calls.floorButton.UP) {
//                        System.out.println("___TRY_TO_ADD");
//                        if (calls.floors[elCurrentFloor - 1].getPersonDestFloor() <= finDestinationFloor) {
//                            System.out.println("___ADD");
//                            tryToAddPerson();
//                        }
//                    } else {
//                        if (calls.floors[elCurrentFloor - 1] != null) {//LENA
//                            System.out.println("Person wants into another way(DOWN)");
//                        }
//                    }
//                }
//                if (elevatorsMove == ElevatorMovingsEnum.DOWN) {
//                    if (calls.fB[elCurrentFloor - 1] == Calls.floorButton.DOWN) {
//                        if (calls.floors[elCurrentFloor - 1].getPersonDestFloor() >= finDestinationFloor) {
//                            tryToAddPerson();
//                        }
//                    } else {
//                        if (calls.floors[elCurrentFloor - 1] != null) {//LENA
//                            System.out.println("Person wants into another way (UP)");
//                        }
//                    }
//                }
//
//                // System.out.println("Elevator has " + personNumber + " people" + "\r\n==========================\r\n");
//
//                if (elevatorsMove == ElevatorMovingsEnum.STOP) {
//                    //  System.out.println("ADD WITH STOP:");
//                    tryToAddPerson();
////                    if (!elDestinationFloors.isEmpty()) {
////                        Iterator<Integer> elDestinationFloorsIt = elDestinationFloors.iterator();
////                        Integer destFl = elDestinationFloorsIt.next();
////                        //System.out.println("destFl = " + destFl);
////                        stoppedElevatorMovingToCall(destFl);
////                        System.out.println(elevatorsMove);
////                        //System.out.println("----END ADD WITH STOP----");
////                    }
//
////                    if (elevatorsMove == ElevatorMovingsEnum.STOP) {
////                        Iterator<Integer> elDestinationFloorsIt = elDestinationFloors.iterator();
////                        Integer destFl = elDestinationFloorsIt.next();
////                        if (destFl > elCurrentFloor) {
////                            destFl = Integer.valueOf(Collections.max(elDestinationFloors).toString());
////                        }
////                        if (destFl < elCurrentFloor) {
////                            destFl = Integer.valueOf(Collections.min(elDestinationFloors).toString());
////                        }
////
////                        if (destFl == elCurrentFloor) {
////                            System.out.println("WARNING!: destFl == elevator.getElCurrentFloor()");
////                            destFl = elDestinationFloorsIt.next();
////                        }
////                        //System.out.println("destFl = " + destFl);
////                        stoppedElevatorMovingToCall(destFl);
////                        setFinDestinationFloor(destFl);
////                    }
//                }
//            }
//        }
//    }

//    public void callGeneration(int steps) {
//        FileWriter f = new FileWriter("D:\\Calls.txt");//file for printing results
//        Random destinationFloor = new Random();
//        //floor number array generating
//        for (int i = 0; i < floors.length; i++) {
//            if (floors[i].getPersonCurrFloor() == 0) {
//                double a = Math.random() * 100;
//                if (a < 20) {
//                    destination = destinationFloor.nextInt(floorNumber) + 1;//random destination floor
//
//                    if (i != (destination - 1)) {
////                        //UP or DOWN person destination floor
////                        if ((destination - 1) < i) {
////                            fB[i] = floorButton.DOWN;
////                            // text = "Person is calling elevator " + fB[i] + " from " + (i + 1) + " to " + destination;
////                            text = fB[i] + " : " + (i + 1) + " -> " + destination;
////                            //System.out.println(text);
////                        } else {
////                            fB[i] = floorButton.UP;
////                            //text = "Person is calling elevator " + fB[i] + " from " + (i + 1) + " to " + destination;
////                            text = fB[i] + " :   " + (i + 1) + " -> " + destination;
////                            // System.out.println(text);
////                        }
//                        //if(destination!=0){
//                        floors[i] = new Person(steps, (i + 1), destination);
//                        //destArray[i] = destination;
//                        System.out.println("Person was iniz: " + floors[i].toString());
//                        S = S + "\r\n" + text;//for printing cycle results
//                        // }
//                    }
//                }
//            }
//        }
//        //Current floors  with calls are printing
//        //System.out.print("Current floors with calls are \r\n");
//        for (int p = 0; p <= floors.length - 1; p++) {
//            if (floors[p].getPersonCurrFloor() != 0) {
//                // System.out.print(floors[p].getPersonCurrFloor() + " to " + destArray[p] + " ");
//                //personFloorNum[p]=personFloorNum[p]+1;//+1 person on current floor
//                D = D + floors[p].getPersonCurrFloor() + ", ";
//                callNumber++;
//            }
//
//        }
//        //System.out.println("\r\ncallNumber = " + callNumber + "\r\n");
//        F = S + D + "\r\n===============================================================";
//
//        f.write(F);//printing results in txt file
//    }
//    
}
