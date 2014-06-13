package control;

import model.Calls;
import model.Elevator;

public class NearElevator {

    //public int index;
    //public int min;
    public int[] Raznost = new int[4];

//    public void ElevatorGo(int CurrFloor1, int CurrFloor2, int CurrFloor3, int CurrFloor4) {
//        for (int i = 0; i < Calls.floors.length; i++) {
//            if (Calls.floors[i] != 0) {
//                elevatorToFloor(i, CurrFloor1, CurrFloor2, CurrFloor3, CurrFloor4);
////                Raznost[0] = Math.abs(CurrFloor1 - Calls.floors[i]);
////                Raznost[1] = Math.abs(CurrFloor2 - Calls.floors[i]);
////                Raznost[2] = Math.abs(CurrFloor3 - Calls.floors[i]);
////                Raznost[3] = Math.abs(CurrFloor4 - Calls.floors[i]);
////
////                min = Raznost[0];
////                //int index = 0;
////                for (int j = 0; j <= Raznost.length - 1; j++) {
////                    if (Raznost[j] < min) {
////                        min = Raznost[j];
////                        index = j + 1;
////                        //System.out.println(index);
////                    }
////                }
////                System.out.println(index);
////                if (index == 1) {
////                    el1.ElevatorMovingToCall(min, 1, Raznost, capacity, CurrFloor1);
////                    System.out.println("The nearest to "+el1.DestinationFloor+" is Elevator1");
////                    for (int g = 0; g <= Calls.floors.length - 1; g++) {
////                        if (Calls.floors[g] == el1.DestinationFloor) {
////                            Calls.floors[g]=0;
////                        }
////                    }
////                }
////                if (index == 2) {
////                    el2.ElevatorMovingToCall(min, 2, Raznost, capacity, CurrFloor2);
////                    System.out.println("The nearest to "+el2.DestinationFloor+" is Elevator2");
////                    for (int g = 0; g <= Calls.floors.length - 1; g++) {
////                        if (Calls.floors[g] == el2.DestinationFloor) {
////                            Calls.floors[g]=0;
////                        }
////                    }
////                }
////                if (index == 3) {
////                    el3.ElevatorMovingToCall(min, 3, Raznost, capacity, CurrFloor3);
////                    System.out.println("The nearest to "+el3.DestinationFloor+" is Elevator3");
////                    for (int g = 0; g <= Calls.floors.length - 1; g++) {
////                        if (Calls.floors[g] == el3.DestinationFloor) {
////                            Calls.floors[g]=0;
////                        }
////                    }
////                }
////                if (index == 4) {
////                    el4.ElevatorMovingToCall(min, 4, Raznost, capacity, CurrFloor4);
////                    System.out.println("The nearest to "+el4.DestinationFloor+" is Elevator4");
////                    for (int g = 0; g <= Calls.floors.length - 1; g++) {
////                        if (Calls.floors[g] == el4.DestinationFloor) {
////                            Calls.floors[g]=0;
////                        }
////                    }
////                }
//
//            }
//        }
//    }
//
//    private void elevatorToFloor(int indexOfFloorToMove, int CurrFloor1, int CurrFloor2, int CurrFloor3, int CurrFloor4) {
//        Raznost[0] = Math.abs(CurrFloor1 - Calls.floors[indexOfFloorToMove]);
//        Raznost[1] = Math.abs(CurrFloor2 - Calls.floors[indexOfFloorToMove]);
//        Raznost[2] = Math.abs(CurrFloor3 - Calls.floors[indexOfFloorToMove]);
//        Raznost[3] = Math.abs(CurrFloor4 - Calls.floors[indexOfFloorToMove]);
//
//        int min = Raznost[0];
//        int index = 1;
//        for (int j = 1; j < Raznost.length; j++) {
//            if (Raznost[j] < min) {
//                min = Raznost[j];
//                index = j + 1;
//                //System.out.println(index);
//            }
//        }
//        System.out.println(index);
//        if (index == 1) {
//            el1.DestinationFloor = Calls.floors[indexOfFloorToMove];
//            el1.ElevatorMovingToCall(min, 1, CurrFloor1);
//            System.out.println("The nearest to " + el1.DestinationFloor + " is Elevator1");
//        }
//        if (index == 2) {
//            el2.DestinationFloor = Calls.floors[indexOfFloorToMove];
//            el2.ElevatorMovingToCall(min, 2, CurrFloor2);
//            System.out.println("The nearest to " + el2.DestinationFloor + " is Elevator2");
//        }
//        if (index == 3) {
//            el3.DestinationFloor = Calls.floors[indexOfFloorToMove];
//            el3.ElevatorMovingToCall(min, 3, CurrFloor3);
//            System.out.println("The nearest to " + el3.DestinationFloor + " is Elevator3");
//        }
//        if (index == 4) {
//            el4.DestinationFloor = Calls.floors[indexOfFloorToMove];
//            el4.ElevatorMovingToCall(min, 4, CurrFloor4);
//            System.out.println("The nearest to " + el4.DestinationFloor + " is Elevator4");
//        }
//        Calls.floors[indexOfFloorToMove] = 0;
//    }
}
