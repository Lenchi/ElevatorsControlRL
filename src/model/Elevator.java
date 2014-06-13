package model;

import control.ElevatorsControl;
import java.util.*;
import java.util.Iterator;
import java.util.Set;

public class Elevator {

    private int elCurrentFloor;
    private int destinationFloor;
    private int finDestinationFloor;//where person want to go from this floor
    private int elevatorID;
    private Set<Integer> elDestinationFloors = new HashSet<Integer>();//array of destination floors
    private int elCapacity;
    private List<Person> personsInElevator = new LinkedList<Person>();
    private ElevatorMovingsEnum elevatorsMove;
    private ElevatorFullingEnum fulling;
    private Calls calls;

    public Elevator(int capacity, int curFloor, int elevatorID, Calls calls) {
        this.elCapacity = capacity;
        this.elCurrentFloor = curFloor;
        this.elevatorID = elevatorID;
        this.elevatorsMove = ElevatorMovingsEnum.STOP;
        this.fulling = ElevatorFullingEnum.NOTFULL;
        this.calls = calls;
    }

    //правило выбора ближайшего этажа вызова лифтом
    public void stoppedElevatorMovingToCall(int potentionalDestFl) {
        if (potentionalDestFl < elCurrentFloor) {
            elevatorsMove = ElevatorMovingsEnum.DOWN;
        }
        if (potentionalDestFl > elCurrentFloor) {
            elevatorsMove = ElevatorMovingsEnum.UP;
        }
        if (potentionalDestFl == elCurrentFloor) {
            elevatorsMove = ElevatorMovingsEnum.STOP;
        }
        System.out.println("Elevator " + elevatorID + ": " + elevatorsMove
                + " " + potentionalDestFl + " <- " + elCurrentFloor);
    }

//    public void getRLdestinationFloor(){
//        RLmatrix rlStates = new
//    }
    //capacity of elevator = Full or Nofull?
    public void elFull() {
        if (personsInElevator.size() == elCapacity) {
            fulling = ElevatorFullingEnum.FULL;
        } else {
            fulling = ElevatorFullingEnum.NOTFULL;
        }
        // System.out.println("Elevator " + elevatorID + " is " + fulling);
    }

    public void tryToAddPerson() {
        if (calls.floors[elCurrentFloor - 1].getPersonCurrFloor() == elCurrentFloor) {
            personsInElevator.add(calls.floors[elCurrentFloor - 1]);
            ElevatorsControl.setIntoPersonsList(calls.floors[elCurrentFloor - 1]);
            System.out.println("            ADD person");
            calls.floors[elCurrentFloor - 1].setPersonCallFloor(0);
            calls.floors[elCurrentFloor - 1].setTimeOfElArrived(ElevatorsControl.steps);
            elDestinationFloors.add(calls.floors[elCurrentFloor - 1].getPersonDestFloor());
            elDestinationFloors.remove(elCurrentFloor);
            System.out.println("            destinationFloors() = " + elDestinationFloors);
        } else {
            elevatorsMove = ElevatorMovingsEnum.STOP;
            System.out.println("Elevator is in " + elevatorsMove + " state now.");
        }
    }

    public void addingPersonInEl() {
        elFull();

        if (fulling != ElevatorFullingEnum.FULL) {
            // if (personNumber == 0) {
            if (personsInElevator.isEmpty()) {
                tryToAddPerson();
            } else {
                Person personToAdd = calls.floors[elCurrentFloor - 1];
                if (!personToAdd.equals(new Person(0, 0, 0))) {
                    System.out.println("___THINK_ABOUT_ADDING_PERSON_____");
                    ElevatorMovingsEnum folowToDest = personToAdd.getFolowToDest();
                    System.out.println(personToAdd.toString());

                    if (elevatorsMove == ElevatorMovingsEnum.UP) {
                        System.out.println("_____fB = " + folowToDest);
                        if (folowToDest == ElevatorMovingsEnum.UP) {
                            System.out.println("___TRY_TO_ADD");
                            if (personToAdd.getPersonDestFloor() <= finDestinationFloor) {
                                System.out.println("___ADD");
                                tryToAddPerson();
                            }
                        } else {
                            System.out.println("Person wants into another way(DOWN)");
                        }
                    }
                    if (elevatorsMove == ElevatorMovingsEnum.DOWN) {
                        if (folowToDest == ElevatorMovingsEnum.DOWN) {
                            if (personToAdd.getPersonDestFloor() >= finDestinationFloor) {
                                tryToAddPerson();
                            }
                        } else {
                            System.out.println("Person wants into another way (UP)");
                        }
                    }

                    if (elevatorsMove == ElevatorMovingsEnum.STOP) {
                        //  System.out.println("ADD WITH STOP:");
                        tryToAddPerson();
                    }
                }
            }
        }
    }

//    public void removingPersonFromEl() {
//        System.out.println("Start to remove!");
//        if (!personsInElevator.isEmpty()) {
//            if (elDestinationFloors.contains(elCurrentFloor)) {
//                Iterator<Person> personsInElevatorIt = personsInElevator.iterator();
//                int countRemovedPersons = 0;
//                while (personsInElevatorIt.hasNext()) {
//                    Person person = personsInElevatorIt.next();
//                    if (person.getPersonDestFloor() == elCurrentFloor) {
//                        personsInElevatorIt.remove();
//                        countRemovedPersons++;
//                        System.out.println("    Remove : " + person.toString());
//                    }
//                }
//                elDestinationFloors.remove(elCurrentFloor);
//                System.out.println("Elevator REMOVED " + countRemovedPersons //    + " person: \n" + calls.floors[elCurrentFloor - 1]
//                );
//            }
//            System.out.println("Elevator now has " + personsInElevator.size() + " people");
//        }
//    }

    public int getDestinationFloor() {
        return destinationFloor;
    }

    public void setDestinationFloor(int destinationFloor) {
        this.destinationFloor = destinationFloor;
        this.finDestinationFloor = destinationFloor;
        elDestinationFloors.add(destinationFloor);
    }

    public int getElCurrentFloor() {
        return elCurrentFloor;
    }

    public void setElCurrentFloor(int elCurrentFloor) {
        this.elCurrentFloor = elCurrentFloor;
    }

    public int getElevatorID() {
        return elevatorID;
    }

    public void setElevatorID(int elevatorID) {
        this.elevatorID = elevatorID;
    }

    public ElevatorMovingsEnum getElevatorsMove() {
        return elevatorsMove;
    }

    public void setElevatorsMove(ElevatorMovingsEnum elevatorsMove) {
        this.elevatorsMove = elevatorsMove;
    }

    public ElevatorFullingEnum getFulling() {
        return fulling;
    }

    public void setFulling(ElevatorFullingEnum fulling) {
        this.fulling = fulling;
    }

    public Set getElDestinationFloors() {
        return elDestinationFloors;
    }

    public int getFinDestinationFloor() {
        return finDestinationFloor;
    }

    public void setFinDestinationFloor(int finDestinationFloor) {
        this.finDestinationFloor = finDestinationFloor;
    }
}
