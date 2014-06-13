package control;

import RLlearning.RL;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import model.Calls;
import model.Elevator;
import model.ElevatorMovingsEnum;
import model.Person;

public class ElevatorsControl {

    private Elevator[] elevatorsArray;
    private List<Elevator> stopedElevatorsList = new ArrayList<Elevator>();
    public static int steps;
    private Calls calls;
    private double avrWaitingTime;
    private static List<Person> personsList = new ArrayList<Person>();
    //RL rl = new RL();

    public ElevatorsControl(Elevator[] elevatorsArray, Calls calls) {
        this.elevatorsArray = elevatorsArray;
        this.calls = calls;
        elevatorsControl();
    }

    private void elevatorsControl() {

        System.out.println("Start elevatorsControl");
        while (steps < 3) {
            calls.callGeneration(steps);
            if (calls.callNumber == 0) {
                System.out.println("");
                break;
            } else {
                System.out.println("\r\nAction #" + steps);
                System.out.println("floorsCalls: ");
                for (int i = 0; i < calls.floors.length; i++) {
                    if (calls.floors[i].getPersonCallFloor() != 0) {
                        System.out.println(calls.floors[i].getFolowToDest() + " : " + (i + 1) + " -> " + calls.floors[i].getPersonDestFloor());
                    }
                }
                getStopedElevators();
                stoppedElevatorsGo();

                chooseActionsForStoppedElevators();

                for (int i = 0; i < elevatorsArray.length; i++) {

                    System.out.println("!!!!!!! Elevator " + i + " getElCurrentFloor = " + elevatorsArray[i].getElCurrentFloor() + ";  getFinDestinationFloor = " + elevatorsArray[i].getFinDestinationFloor());

                    elevatorsArray[i].removingPersonFromEl();
                    elevatorsArray[i].addingPersonInEl();
                    if (elevatorsArray[i].getElCurrentFloor() == elevatorsArray[i].getFinDestinationFloor()) {
                        elevatorsArray[i].setElevatorsMove(ElevatorMovingsEnum.STOP);
                        elevatorsArray[i].getElDestinationFloors().remove(elevatorsArray[i].getFinDestinationFloor());
                        elevatorsArray[i].setFinDestinationFloor(0);
                    }
                }
                changeCurrFloorsOfElevators();
            }
            System.out.println("\r=====================================\r\n");
            steps++;
        }
        calculateAvrWaitingTime();
    }

    public void calculateAvrWaitingTime() {
        int sumWaitTime = 0;
        int count = 0;
        if (!personsList.isEmpty()) {
            //System.out.println("PersonsList: ");
            for (Person person : personsList) {
                sumWaitTime += person.getPersonWaitingTime();
                //System.out.println(person.toString());
                count++;
            }
            avrWaitingTime = (double) sumWaitTime / personsList.size();
        }
        System.out.println("avrWaitingTime = " + avrWaitingTime);
        System.out.println("ServicedPersonNumber = " + count);
    }

    public static void setIntoPersonsList(Person person) {
        personsList.add(person);
    }

////////////////////////////////////////////////RL
    @SuppressWarnings({"static-access", "static-access"})
    public void chooseActionsForStoppedElevators() {
        int[] indexesStop = new int[main.Main.ELEVATOR_NUMBERS];
        int[] elevatorsCurrentFloors = new int[main.Main.ELEVATOR_NUMBERS];
        ElevatorMovingsEnum[] elevatorMoves = new ElevatorMovingsEnum[main.Main.ELEVATOR_NUMBERS];

        for (Elevator elevator : elevatorsArray) {
            elevator.getElevatorsMove();
            elevator.getElCurrentFloor();
            if (elevator.getElevatorsMove() == ElevatorMovingsEnum.STOP) {
                indexesStop[elevator.getElevatorID()] = elevator.getElevatorID();
            } else {
                indexesStop[elevator.getElevatorID()] = (-1);
            }
            elevatorsCurrentFloors[elevator.getElevatorID()] = elevator.getElCurrentFloor();
            elevatorMoves[elevator.getElevatorID()] = elevator.getElevatorsMove();
            //System.out.println("indexesStop = "+indexesStop[elevator.getElevatorID()]);
            System.out.println("elevatorsCurrentFloor = " + elevatorsCurrentFloors[elevator.getElevatorID()]);
            System.out.println("El Moves = " + elevatorMoves[elevator.getElevatorID()]);
        }

        int a = (int) Math.pow((int) main.Main.FLOOR_NUMBER, (int) main.Main.ELEVATOR_NUMBERS);
        for (int i = 0; i < a; i++) {
            RL.getStatesMap().get(i);
            int k = 0;
            for (int j = 0; j < main.Main.ELEVATOR_NUMBERS; j++) {
                if (elevatorsCurrentFloors[j] == RL.getStatesMap().get(i)[j]) {
                    k++;
                }
            }
            if (k == 4) {
                int indexState = i;
            }
        }

        //List<Integer> indexMoving = new ArrayList<Integer>();
        //ElevatorMovingsEnum[] fixElement = new ElevatorMovingsEnum[main.Main.ELEVATOR_NUMBERS];
        List<ElevatorMovingsEnum[]> placeFix1 = new ArrayList<ElevatorMovingsEnum[]>();
        List<ElevatorMovingsEnum[]> placeFix2 = new ArrayList<ElevatorMovingsEnum[]>();
        List<ElevatorMovingsEnum[]> placeFix3 = new ArrayList<ElevatorMovingsEnum[]>();
        List<ElevatorMovingsEnum[]> placeFix4 = new ArrayList<ElevatorMovingsEnum[]>();

        int b = (int) Math.pow(3, (int) main.Main.ELEVATOR_NUMBERS);


        for (int i = 0; i < b; i++) {
            RL.getActionsMap().get(i);
            if (elevatorMoves[0] != ElevatorMovingsEnum.STOP) {
                if (elevatorMoves[0] == RL.getActionsMap().get(i)[0]) {
                    placeFix1.add(RL.getActionsMap().get(i));
                }
            }
        }

        System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
        for (int i = 0; i < placeFix1.size(); i++) {
            if (elevatorMoves[1] != ElevatorMovingsEnum.STOP) {
                if(elevatorMoves[1] == placeFix1.get(i)[1]){
                    placeFix2.add(placeFix1.get(i));
                }
            }
        }

        for(int i=0; i<placeFix2.size(); i++){
            if(elevatorMoves[2] != ElevatorMovingsEnum.STOP){
                if(elevatorMoves[2] == placeFix2.get(i)[2]){
                    placeFix3.add(placeFix2.get(i));
                }
            }
        }

        for(int i=0; i<placeFix3.size(); i++){
            if(elevatorMoves[3] != ElevatorMovingsEnum.STOP){
                if(elevatorMoves[3] == placeFix3.get(i)[3]){
                    placeFix4.add(placeFix3.get(i));
                }
            }
        }

        System.out.println("----->"+placeFix4.size());
          System.out.println("----->"+placeFix3.size());
            System.out.println("----->"+placeFix2.size());

        for(int i=0; i<placeFix4.size(); i++){
            System.out.println("====");
            for(int j=0; j<main.Main.ELEVATOR_NUMBERS; j++){
                System.out.println(placeFix4.get(i)[j]);
            }
        }
    }
    ////////////////////////////////////////////////RL

    private void getStopedElevators() {
        for (Elevator elevator : elevatorsArray) {
            if (elevator.getElevatorsMove() == ElevatorMovingsEnum.STOP) {
                stopedElevatorsList.add(elevator);
            }
        }
    }

    private HashMap<Elevator, Integer> getStoppedElevatorsCurrentFloors() {
        HashMap<Elevator, Integer> currentFloors = new HashMap();
        for (int i = 0; i < stopedElevatorsList.size(); i++) {
            Elevator elevator = stopedElevatorsList.get(i);
            currentFloors.put(elevator, elevator.getElCurrentFloor());
        }
        return currentFloors;
    }

    private void stoppedElevatorsGo() {
        for (int i = 0; i < calls.floors.length; i++) {
            int potentionalDestFl = calls.floors[i].getPersonCallFloor();
            if (potentionalDestFl != 0) {
                if (stopedElevatorsList.size() > 0) {
                    HashMap<Elevator, Integer> elevatrosCurrentFloors = getStoppedElevatorsCurrentFloors();

                    Elevator elToMoveToFloor = chooseStoppedElevatorToFloor(i, elevatrosCurrentFloors);

                    elToMoveToFloor.setDestinationFloor(potentionalDestFl);

                    elToMoveToFloor.stoppedElevatorMovingToCall(potentionalDestFl);
//                    System.out.println("The nearest to "
//                            + elToMoveToFloor.getDestinationFloor()
//                            + " is Elevator[ " + elToMoveToFloor.getElevatorID() + " ]"
//                            + " with curFl = " + elToMoveToFloor.getElCurrentFloor()
//                            + "elMove = " + elToMoveToFloor.getElevatorsMove());

                    stopedElevatorsList.remove(elToMoveToFloor);
                } else {
                    //System.out.println("Sorry! Elevators are busy! :P");
                }
            }
        }
    }

    private Elevator chooseStoppedElevatorToFloor(int indexOfFloorToMove, HashMap<Elevator, Integer> elCurrentFloors) {
        int potentionalDestinationFloor = calls.floors[indexOfFloorToMove].getPersonCurrFloor();

        HashMap<Elevator, Integer> differenceOfCurrFloorDestinationFloor = initializeDiffCurFloorDestinationFloor(elCurrentFloors, potentionalDestinationFloor);

        Elevator elevator = findStoppedElWithMinDiffOfCurFlDestintFl(differenceOfCurrFloorDestinationFloor);

        //calls.floors[indexOfFloorToMove] = 0;

        return elevator;
    }

    private Elevator findStoppedElWithMinDiffOfCurFlDestintFl(HashMap<Elevator, Integer> differenceOfCurrFloorDestinationFloor) {
        Collection<Integer> minDiffOfCurFloorDestintFloor = differenceOfCurrFloorDestinationFloor.values();
        int min = Collections.min(minDiffOfCurFloorDestintFloor, null);

        Elevator elevatorForFloor = null;
        Set<Elevator> keys = differenceOfCurrFloorDestinationFloor.keySet();
        Iterator<Elevator> keysIt = keys.iterator();

        while (keysIt.hasNext()) {
            elevatorForFloor = keysIt.next();
            if (differenceOfCurrFloorDestinationFloor.get(elevatorForFloor) == min) {
                break;
            }
        }
        return elevatorForFloor;
    }

    private HashMap<Elevator, Integer> initializeDiffCurFloorDestinationFloor(HashMap<Elevator, Integer> elCurrentFloors, int potentionalDestinationFloor) {
        HashMap<Elevator, Integer> differenceCurrFloorDestinationFloor = new HashMap<Elevator, Integer>();
        Set<Elevator> keys = elCurrentFloors.keySet();
        Iterator<Elevator> keysIt = keys.iterator();
        while (keysIt.hasNext()) {
            Elevator elevator = keysIt.next();
            differenceCurrFloorDestinationFloor.put(elevator, Math.abs(elCurrentFloors.get(elevator) - potentionalDestinationFloor));
        }
        return differenceCurrFloorDestinationFloor;
    }

    private void changeCurrFloorsOfElevators() {
        System.out.println("\nChangeCurrFlorsOfElevator():");
        for (int i = 0; i < elevatorsArray.length; i++) {
            //System.out.println("Elevator " + i + " :   destinationFloors() = " + elevatorsArray[i].getElDestinationFloors());

            checkConstraints(elevatorsArray[i]);

            ElevatorMovingsEnum elMove = elevatorsArray[i].getElevatorsMove();
            int currentFloor = elevatorsArray[i].getElCurrentFloor();

            if (elMove == ElevatorMovingsEnum.DOWN) {
                elevatorsArray[i].setElCurrentFloor(currentFloor - 1);
            }
            if (elMove == ElevatorMovingsEnum.UP) {
                elevatorsArray[i].setElCurrentFloor(currentFloor + 1);
            }
            System.out.println("                 curFl = " + elevatorsArray[i].getElCurrentFloor());
        }
    }

    private void checkConstraints(Elevator elevator) {
        if (elevator.getElDestinationFloors().isEmpty()) {
            elevator.setElevatorsMove(ElevatorMovingsEnum.STOP);
        } else {
            if (elevator.getElevatorsMove() == ElevatorMovingsEnum.STOP) {
                Iterator<Integer> elDestinationFloorsIt = elevator.getElDestinationFloors().iterator();
                Integer destFl = elDestinationFloorsIt.next();
                if (destFl > elevator.getElCurrentFloor()) {
                    destFl = Integer.valueOf(Collections.max(elevator.getElDestinationFloors()).toString());
                }
                if (destFl < elevator.getElCurrentFloor()) {
                    destFl = Integer.valueOf(Collections.min(elevator.getElDestinationFloors()).toString());
                }

                if (destFl == elevator.getElCurrentFloor()) {
                    System.out.println("WARNING!: destFl == elevator.getElCurrentFloor()");
                    destFl = elDestinationFloorsIt.next();
                }
                //System.out.println("destFl = " + destFl);
                elevator.stoppedElevatorMovingToCall(destFl);
                elevator.setFinDestinationFloor(destFl);
            }
        }

//////        if (elevator.getElDestinationFloors().size() == 1) {
//////            Iterator<Integer> elDestinationFloorsIt = elevator.getElDestinationFloors().iterator();
//////            Integer destFl = elDestinationFloorsIt.next();
//////            //System.out.println("destFl = " + destFl);
//////            elevator.stoppedElevatorMovingToCall(destFl);
//////        }
    }
//    public void ElControl(int ID, int floorNumber) {
//        Elevator el1 = new Elevator(1, 1, ID);
////Elevator el2 = new Elevator(2, 2, ID);
//
//        //el1.ElevatorMovingToCall(ID, el1.elCapacity, el1.elCurrentFloor);
//        //el2.ElevatorMovingToCall(ID, el1.elCapacity, el1.elCurrentFloor);
//        // Calls c = new Calls();
//        // c.CallGeneration(floorNumber);
//
//        //el1.ElevatorMovingToCall(ID, el1.elCapacity, el1.elCurrentFloor);
//        //System.out.println("Destination Floor of Elevator #"+ID+" = "+el1.DestinationFloor);
//        // System.out.println("===>"+el1.eM);
//
//        while (steps < 40) {
//            if (Calls.callNumber == 0) {
//                System.out.println("");
//                break;
//            } else {
//                System.out.println("\r\nAction #" + steps);
//
//                if (el1.eM == el1.eM.STOP) {
//                    System.out.println("Elevator is on " + el1.elCurrentFloor);
//                    //System.out.println(el1.personNumber);
//                    //el1.elFull(el1.personNumber, el1.elCapacity, ID);
//                    // el1.RemovingPersonFromEl(el1.ElDestFloors, Calls.destArray, el1.DestinationFloor);
//                    // el1.AddingPersonInEl( el1.elCapacity, el1.DestinationFloor, el1.elCurrentFloor, ID);
//                    // el1.ElevatorMoving(el1.ElDestFloors, el1.elCurrentFloor, ID);
//                    //el2.RemovingPersonFromEl(el1.ElDestFloors, Calls.destArray, el1.DestinationFloor);
//                    //el2.AddingPersonInEl( el1.elCapacity, el1.DestinationFloor, el1.elCurrentFloor, ID);
//                    //el2.ElevatorMoving(el1.ElDestFloors, el1.elCurrentFloor, ID);
//
//                    //System.out.println("Elevator added "+el1.personNumber+" person");
//                    //el1.elFull(el1.personNumber, el1.elCapacity, ID);
//                    if (el1.eM == el1.eM.STOP) {
//                        //el1.RemovingPersonFromEl(el1.ElDestFloors, Calls.destArray, el1.DestinationFloor);
//                        //el1.ElevatorMovingToCall(1, ElevatorS.mathArrayEl1, el1.elCapacity, el1.elCurrentFloor);
//                        //el2.RemovingPersonFromEl(el1.ElDestFloors, Calls.destArray, el1.DestinationFloor);
//                        //el2.ElevatorMovingToCall(ID, el1.elCapacity, el1.elCurrentFloor);
//                    }
//                }
//
//                if (el1.eM == el1.eM.UP) {
//
//                    el1.elCurrentFloor = el1.elCurrentFloor + 1;
//                    System.out.println("Elevator " + ID + " moves " + el1.eM + " to " + el1.elCurrentFloor + " floor.");
//                    //el1.elFull(el1.personNumber, el1.elCapacity, ID);
//                    el1.RemovingPersonFromEl(el1.ElDestFloors, Calls.destArray, el1.DestinationFloor);
//                    el1.AddingPersonInEl(el1.elCapacity, el1.DestinationFloor, el1.elCurrentFloor, ID);
//                    el1.ElevatorMoving(el1.ElDestFloors, el1.elCurrentFloor, ID);
//                    //System.out.println("Elevator added "+el1.personNumber+" person");
//                    //el1.elFull(el1.personNumber, el1.elCapacity, ID);
//
//                    if (el1.elCurrentFloor == el1.DestinationFloor) {
//                        el1.eM = el1.eM.STOP;
//                        System.out.println("Elevator " + ID + " is arrived to " + el1.elCurrentFloor + " floor");
//                        //break;
//                    }
//                }
//
//                if (el1.eM == el1.eM.DOWN) {
//
//                    el1.elCurrentFloor = el1.elCurrentFloor - 1;
//                    System.out.println("Elevator " + ID + " moves " + el1.eM + " on " + el1.elCurrentFloor + " floor.");
//                    // el1.elFull(el1.personNumber, el1.elCapacity, ID);
//                    el1.RemovingPersonFromEl(el1.ElDestFloors, Calls.destArray, el1.DestinationFloor);
//                    el1.AddingPersonInEl(el1.elCapacity, el1.DestinationFloor, el1.elCurrentFloor, ID);
//                    el1.ElevatorMoving(el1.ElDestFloors, el1.elCurrentFloor, ID);
//                    //System.out.println("Elevator added "+el1.personNumber+" person");
//                    //el1.elFull(el1.personNumber, el1.elCapacity, ID);
//
//                    if (el1.elCurrentFloor == el1.DestinationFloor) {
//                        el1.eM = el1.eM.STOP;
//                        System.out.println("Elevator " + ID + " is arrived to " + el1.elCurrentFloor + " floor");
//                        //break;
//                    }
//                }
//
//
//
//                System.out.println("" + "\r\n===========================\r\n");
//                steps++;
//            }
//        }
//
//
//    }
}

