package org.example;

import java.util.Comparator;

public class PassengersAgeThenName implements Comparator<Passenger>{
    @Override
    public int compare(Passenger o1, Passenger o2) {
        int ageComparison = Integer.compare(o1.getAge(), o2.getAge());

        if(ageComparison ==0){
            return o1.getName().compareTo(o2.getName());
        }
        return ageComparison;
    }
}
