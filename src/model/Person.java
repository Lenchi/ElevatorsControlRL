package model;

public class Person {

    private int timeOfCall;
    private int timeOfElArrived;
    private int personCallFloor;
    private int personCurrFloor;
    private int personDestFloor;
    private int personWaitingTime;
    private ElevatorMovingsEnum folowToDest;

    public Person(int timeOfCall, int personCurrFloor, int destinationFl) {
        this.timeOfCall = timeOfCall;
        this.personCurrFloor = personCurrFloor;
        this.personCallFloor = personCurrFloor;
        this.personDestFloor = destinationFl;
        this.personWaitingTime = -1;
        this.timeOfElArrived = -1;
        initializeFollowDest();
    }

    private void initializeFollowDest() {
        if (personCurrFloor > personDestFloor) {
            this.folowToDest = ElevatorMovingsEnum.DOWN;
        }
        if (personCurrFloor < personDestFloor) {
            this.folowToDest = ElevatorMovingsEnum.UP;
        }
//        System.out.println("Person: " + folowToDest + " : "
//                + personCurrFloor + " -> " + personDestFloor);
    }

    private void calculatePersonWaitingTime() {
        personWaitingTime = timeOfElArrived - timeOfCall;
    }

    public ElevatorMovingsEnum getFolowToDest() {
        return folowToDest;
    }

    public int getPersonWaitingTime() {
        return personWaitingTime;
    }

    public int getTimeOfElArrived() {
        return timeOfElArrived;
    }

    public void setTimeOfElArrived(int timeOfElArrived) {
        this.timeOfElArrived = timeOfElArrived;
        calculatePersonWaitingTime();
    }

    public int getTimeOfCall() {
        return timeOfCall;
    }

    public int getPersonCurrFloor() {
        return personCurrFloor;
    }

    public void setPersonCurrFloor(int personCurrFloor) {
        this.personCurrFloor = personCurrFloor;
    }

    public int getPersonDestFloor() {
        return personDestFloor;
    }

    public void setPersonDestFloor(int personDestFloor) {
        this.personDestFloor = personDestFloor;
    }

    public int getPersonCallFloor() {
        return personCallFloor;
    }

    public void setPersonCallFloor(int personCallFloor) {
        this.personCallFloor = personCallFloor;
    }

//    @Override
//    public String toString() {
//        return "Person: \n    timeOfCall = " + timeOfCall + " step;"
//                + "personCurrFloor = " + personCurrFloor + ";"
//                + "timeOfElArrived = " + timeOfElArrived
//                + " destFloor = " + personDestFloor
//                + " folowToDest = " + folowToDest
//                + " personWaitingTime = " + personWaitingTime;
//    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Person other = (Person) obj;
        if (this.timeOfCall != other.timeOfCall) {
            return false;
        }
        if (this.timeOfElArrived != other.timeOfElArrived) {
            return false;
        }
        if (this.personCurrFloor != other.personCurrFloor) {
            return false;
        }
        if (this.personDestFloor != other.personDestFloor) {
            return false;
        }
        if (this.personWaitingTime != other.personWaitingTime) {
            return false;
        }
        if (this.folowToDest != other.folowToDest && (this.folowToDest == null || !this.folowToDest.equals(other.folowToDest))) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        return hash;
    }
}
