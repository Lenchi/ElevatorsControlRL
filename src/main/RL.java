package main;

import java.util.HashMap;
import java.util.Map;
import model.ElevatorMovingsEnum;

public class RL {

    private int floorNumber;
    private int elevatorsNumber;
    private int[][] states4Elevators;
    public static Map<Integer, int[]> statesMap = new HashMap<Integer, int[]>();
    public static Map<Integer, ElevatorMovingsEnum[]> actionsMap = new HashMap<Integer, ElevatorMovingsEnum[]>();
    public static Map<Integer, int[]> QFunctionsMap = new HashMap<Integer, int[]>();

    public RL() {
        this.floorNumber = Main.FLOOR_NUMBER;
        this.elevatorsNumber = Main.ELEVATOR_NUMBERS;

    }

    public void stimulationMatrix(){
        
    }

    public void states4ElevatorSystem(int floorNumber, int elNumber) {
        int a = (int) Math.pow((int) floorNumber, (int) elNumber);//количество размещений с повторениями для состояний
        //System.out.println("Number of combinations = " + a);
        states4Elevators = new int[floorNumber][elNumber];

        int[] floors = new int[floorNumber];
        for (int i = 0; i < floorNumber; i++) {
            floors[i] = i + 1;
        }
//размещение с перестановками, алгоритм. Перебор элементов (этажей) по 4-размерной комбинации
        int g = 0;
        // четыре для четырех лифтов
        //не знаю, как тут сделать меньше переборов (меньше циклов)
        for (int i = 0; i < floors.length; i++) {
            for (int j = 0; j < floors.length; j++) {
                for (int k = 0; k < floors.length; k++) {
                    for (int p = 0; p < floors.length; p++) {
                        int[] r = {floors[i], floors[j], floors[k], floors[p]};
                        statesMap.put(g, r);
                        g++;
                    }////10000 рядков разных комбинаций
                }
            }
        }

//для печати матрицы состояний (каждого элемента)
//        for (int d = 0; d < a; d++) {
//            System.out.println("====");
//            printStates(elNumber, d);
//        }

    }

//для печати матрицы состояний
    public void printStates(int elNumber, int d) {
        for (int i = 0; i < elNumber; i++) {
            System.out.println(statesMap.get(d)[i] + " ");
        }
    }

    public void actions4ElevatorSystem(int elNumber) {
        int b = (int) Math.pow(3, (int) elNumber);//количество размещений с повторениями для действий

        ElevatorMovingsEnum[] actionsArray = {ElevatorMovingsEnum.UP, ElevatorMovingsEnum.DOWN, ElevatorMovingsEnum.STOP};

        int g = 0;
        // четыре для четырех лифтов
        //не знаю, как тут сделать меньше переборов (меньше циклов)
        for (int i = 0; i < actionsArray.length; i++) {
            for (int j = 0; j < actionsArray.length; j++) {
                for (int k = 0; k < actionsArray.length; k++) {
                    for (int p = 0; p < actionsArray.length; p++) {
                        ElevatorMovingsEnum[] r = {actionsArray[i], actionsArray[j], actionsArray[k], actionsArray[p]};
                        actionsMap.put(g, r);
                        g++;
                    }////10000 рядков разных комбинаций
                }
            }
        }
        //для печати матрицы состояний (каждого элемента)
//        for (int d = 0; d < b; d++) {
//            System.out.println("====");
//            printActions(elNumber, d);
//        }

    }
//для печати матрицы действий

    public void printActions(int elNumber, int d) {
        for (int i = 0; i < elNumber; i++) {
            System.out.println(actionsMap.get(d)[i] + " ");
        }
    }

    public void qLearning(int floorNumber, int elNumber) {
        //Q(S,A)
        int a = (int) Math.pow((int) floorNumber, (int) elNumber);//states
        int b = (int) Math.pow(3, (int) elNumber);//actions
        int c = a * b;//количество элементов матрицы функции ценности

        states4ElevatorSystem(floorNumber, elNumber);
        actions4ElevatorSystem(elNumber);

        int[] states = new int[a];
        int[] actions = new int[b];

        //получение индексов из каждого масива (состояний и действий)
        //создала массивы индексов для каждого

//        int i = 0;
//        for (Map.Entry<Integer, int[]> state : statesMap.entrySet()) {
//            states[i] = state.getKey();
//            i++;
//        }

//        int j = 0;
//        for (Map.Entry<Integer, ElevatorMovingsEnum[]> action : actionsMap.entrySet()) {
//            actions[j] = action.getKey();
//            j++;
//        }

//        int g = 0;
//        for (int h = 0; h < a; h++) {
//            for (int k = 0; k < b; k++) {
//                int[] r = {states[h], actions[k]};
//                QFunctionsMap.put(g, r);
//                g++;
//            }
//        }
        //три выше объединила
        int g = 0;
        for (Map.Entry<Integer, int[]> state : statesMap.entrySet()) {
            for (Map.Entry<Integer, ElevatorMovingsEnum[]> action : actionsMap.entrySet()) {
                int[] r = {state.getKey(), action.getKey()};
                QFunctionsMap.put(g, r);
                g++;
            }
        }

        //System.out.println("g = " + g);        

        for (int x = 0; x < c; x++) {
            System.out.println("===");
            printAFunctionMap(x);
        }

    }
//метод вывода коэффициентов

    public void printAFunctionMap(int x) {
        for (int s = 0; s < 2; s++) {
            System.out.println(QFunctionsMap.get(x)[s] + " ");
        }
    }
//для инициализации матрицы функций ценности
    public void initializeQFunctionMatrix(int floorNumber, int elNumber) {
        int a = (int) Math.pow((int) floorNumber, (int) elNumber);//states
        int b = (int) Math.pow(3, (int) elNumber);//actions
        int c = a * b;//количество элементов матрицы функции ценности
        for (int i = 0; i < c; i++) {
            int[] r = {0, 0};
            QFunctionsMap.put(i, r);
        }
    }
}




