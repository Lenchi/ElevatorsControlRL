package main;

import RLlearning.RL;
import control.ElevatorsControl;
import java.util.Random;
import model.Calls;
import model.Elevator;

public class Main {

    public static int ELEVATOR_NUMBERS = 4;
    public static int CAPACITY = 4;
    public static int FLOOR_NUMBER = 10;
    public static int MIN_TIME;//for RL
    public static int MAX_TIME;//for RL
    static RL rl = new RL();

    public static void main(String[] args) {
        Calls calls = new Calls();

        int[] startCurrentFloors = initializeRLStarCurrentFloors();        
        Elevator[] elevatorsArray = initializeElevators(startCurrentFloors, calls);        
        new ElevatorsControl(elevatorsArray, calls);
        ElevatorsControl elc = new ElevatorsControl(elevatorsArray, calls);
        elc.chooseActionsForStoppedElevators();
    }    

// CurrentFloors initializing method without RL
//    private static int[] initializeStarCurrentFloors() {
//        int[] startCurrentFloors = new int[ELEVATOR_NUMBERS];
//        int[] someStartStateFloors = {1, 4, 5, 10};
//        for (int i = 0; i < startCurrentFloors.length; i++) {
//            startCurrentFloors[i] = someStartStateFloors[i];
//        }
//        return startCurrentFloors;
//    }
    
    @SuppressWarnings("static-access")
    private static int[] initializeRLStarCurrentFloors() {
        initializeRLmatrix();
        
        int[] startCurrentFloors = new int[ELEVATOR_NUMBERS];

        int a = (int) Math.pow((int) main.Main.FLOOR_NUMBER, 4);
        Random index = new Random();
        Integer ind = index.nextInt(a);
        for (int i = 0; i < 4; i++) {
            startCurrentFloors[i] = rl.getStatesMap().get(ind)[i];
        }
        return startCurrentFloors;
    }

    private static Elevator[] initializeElevators(int[] startCurrentFloors, Calls calls) {
        Elevator[] elevatorsArray = new Elevator[ELEVATOR_NUMBERS];
        for (int i = 0; i < elevatorsArray.length; i++) {
            int elevatorID = i;
            elevatorsArray[i] = new Elevator(CAPACITY, startCurrentFloors[i], elevatorID, calls);
        }
        return elevatorsArray;
    }

    private static void initializeRLmatrix() {
        rl.actions4ElevatorSystem(4);
        rl.states4ElevatorSystem(main.Main.FLOOR_NUMBER, 4);
        rl.qLearning(main.Main.FLOOR_NUMBER, 4);//Q-Function Matrix initialization
    }
}
