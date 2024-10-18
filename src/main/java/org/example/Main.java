package org.example;
// CA1
import java.io. * ;
import java.util.*;

public class Main {
    public static void main(String[] args) {

        String fileName = "titanic-data-100.csv"; // file should be in the project folder (below pom.xml)

        ArrayList<Passenger> passengerList= new ArrayList<>();
        ArrayList<Passenger> sortedPassengers = new ArrayList<>();

        loadPassengerDataFromFile( passengerList, fileName);

        displayAllPassengers( passengerList );


        // Assignment: Implement and test the following methods.
        // See the description of each method in the CA1 Specification PDF file from Moodle

          getPassengerNames(passengerList);
          getPassengersContainingNames(passengerList, "William");
          getPassengersOlderThan(passengerList, 30);
          countPassengersByGender(passengerList);
          sumFares(passengerList);
          maleSurvivors(passengerList);
          ticketOwner(passengerList, "2680");
          averageAge(passengerList);
          getPassengersByTicketClass(passengerList);
          sortPassengersByPassengerId(passengerList);
          sortPassengersByName(passengerList);
          sortPassengersByAgeThenName(passengerList);
          sortPassengersByGenderThenPassengerNumber(passengerList);
          sortPassengersByFareThenSurvival(passengerList);
          sortPassengersByTicketClass(passengerList);
          sortPassengersByAge(passengerList);
          sortPassengersByTicketNumberLambda(passengerList);
//        sortPassengersByTicketNumberStatic();
//        findPassengerByTicketNumber();
//        findPassengerByPassengerId();

        System.out.println("Finished, Goodbye!");
    }

    /**
     * Populate an ArrayList of Passenger objects with data from a text file
     * @param passengerList - an ArrayList to be filled with Passenger objects
     * @param fileName - name of CSV text file containing passenger details
     */
    public static void loadPassengerDataFromFile( ArrayList<Passenger> passengerList, String fileName) {

        // Format of each row of data is:
        // Name,Age,Height(m),GPA  - these heading names are included as the first row in file
        // John Malone,20,1.78,3.55   for example

        // Use a Regular Expression to set both comma and newline as delimiters.
        //  sc.useDelimiter("[,\\r\\n]+");
        // Text files in windows have lines ending with "CR-LF" or "\r\n" sequences.
        // or may have only a newline - "\n"
        // Windows uses CRLF (\r\n, 0D 0A) line endings while Unix just uses LF (\n, 0A).
        //
        try (Scanner sc = new Scanner(new File(fileName))
                .useDelimiter("[,\\r\\n]+"))
        {

            // skip past the first line, as it has field names (not data)
            if(sc.hasNextLine())
                sc.nextLine();   // read the header line containing column titles, but don't use it

            // while there is a next token to read....
            System.out.println("Go...");

            while (sc.hasNext())
            {
                String passengerId = sc.next();    // read passenger ID
                int survived = sc.nextInt();     // 0=false, 1=true
                int passengerClass = sc.nextInt();  // passenger class, 1=1st, 2=2nd or 3rd
                String name = sc.next();
                String gender = sc.next();
                int age = sc.nextInt();
                int siblingsAndSpouses = sc.nextInt();
                int parentsAndChildren = sc.nextInt();
                String ticketNumber = sc.next();
                double fare = sc.nextDouble();
                String cabin = sc.next();
                String embarkedAt = sc.next();

                System.out.println(passengerId +", " + name);

                passengerList.add(
                        new Passenger( passengerId, survived, passengerClass,
                                name, gender, age, siblingsAndSpouses,parentsAndChildren,ticketNumber,
                                fare, cabin, embarkedAt)
                );
            }
        } catch (FileNotFoundException exception)
        {
            System.out.println("FileNotFoundException caught. The file " +fileName+ " may not exist." + exception);
        }
    }

    public static void displayAllPassengers( ArrayList<Passenger> passengerList ) {
        System.out.println("\n\nDisplaying all passengers:");
        for( Passenger passenger : passengerList)
        {
            System.out.println(passenger);
        }
    }
    public static void getPassengerNames( ArrayList<Passenger> passengerList ) {
        System.out.println("\n\nDisplaying passengers' names:");
        for( Passenger passenger : passengerList)
        {
            System.out.println(passenger.getName());
        }
    }

    public static void getPassengersContainingNames(ArrayList<Passenger> passengerList, String name){
        System.out.println("\n\nDisplay all passengers with name 'William:'");
        for(Passenger passenger : passengerList){
            if(passenger.getName().toLowerCase().contains(name.toLowerCase())){
                System.out.println(passenger);
            }
        }
    }

    public static void getPassengersOlderThan(ArrayList<Passenger> passengerList, int age){
        System.out.println("\n\nDisplay all passengers older than 30:");
        for(Passenger passenger : passengerList){
            if(passenger.getAge() > age){
                System.out.println(passenger);
            }
        }
    }

    public static void countPassengersByGender(ArrayList<Passenger> passengerList){
        System.out.println("\n\nCount all passengers by Gender:");

        int fCount = 0;
        int mCount = 0;
        for(Passenger passenger : passengerList){
            if(passenger.getGender().equalsIgnoreCase("female")){
                fCount++;
            }
            else {
                mCount++;
            }
        }

        System.out.println("Number of Female Passengers on board: "+fCount);
        System.out.println("Number of Male Passengers on board: "+mCount);
    }

    public static double sumFares(ArrayList<Passenger> passengerList){
        double totalFare = 0.0;
        System.out.println("\n\nSum of all fares: ");
        for(Passenger passenger : passengerList){
            totalFare += passenger.getFare();
        }
        System.out.println(totalFare);
        return totalFare;
    }

    public static void maleSurvivors(ArrayList<Passenger> passengerList){
        System.out.println("\n\nList of male survivors: ");
        for(Passenger passenger : passengerList){
            if(passenger.getGender().equalsIgnoreCase("male") && passenger.getSurvived() == 1){
                System.out.println(passenger);
            }
        }
    }

    public static void ticketOwner (ArrayList<Passenger> passengerList, String ticketNumber){
        boolean found = false;
        System.out.println("\n\nOwner of the ticket");
        for(Passenger passenger : passengerList){
            if(passenger.getTicketNumber().equalsIgnoreCase(ticketNumber)){
                System.out.println(passenger);
                found = true;
                break;
            }
        }
        if(!found){
            System.out.println("No passenger found with ticket number: "+ticketNumber);
        }
    }

    public static double averageAge(ArrayList<Passenger> passengerList){
        double avgAge = 0;
        int ageCount = 0;
        int count = 0;
        //System.out.println("\n\nAverage age:" +avgAge);
        for(Passenger passenger : passengerList){
            ageCount += passenger.getAge();
            count++;
        }
        if(count > 0){
            avgAge = (double) ageCount/count;
        }

        System.out.println("\n\nAverage age: " +avgAge);
        return avgAge;
    }

    public static void getPassengersByTicketClass(ArrayList<Passenger> passengerList){
        System.out.println("\n\nGet all 1st class Passengers: ");
        boolean found = false;
        PassengerClass classType = PassengerClass.FIRST;
        for(Passenger passenger : passengerList) {
            if(classType == passenger.getPassengerClass()){
                System.out.println(passenger);
                found = true;
            }
    }
        if(!found){
            System.out.println("No passenger found in "+ passengerList.getClass()+" class.");
        }
    }

    public static void sortPassengersByPassengerId(ArrayList<Passenger> passengerList){
        System.out.println("\n\nSort passengers by passengers id:");
        Collections.sort(passengerList, new Comparator<Passenger>(){
            @Override
            public int compare (Passenger p1, Passenger p2){
                return p1.getPassengerId().compareTo(p2.getPassengerId());
            }
        });
        for (Passenger passenger : passengerList){
            System.out.println(passenger);
        }
    }

    public static void sortPassengersByName(ArrayList<Passenger> passengerList){
        System.out.println("\n\nSort passengers by name: ");
        Collections.sort(passengerList, new Comparator<Passenger>(){

            @Override
            public int compare(Passenger o1, Passenger o2) {
                return o1.getName().compareTo(o2.getName());
            }
        });
        for(Passenger passenger : passengerList){
            System.out.println(passenger);
        }
    }

    public static void sortPassengersByAgeThenName(ArrayList<Passenger> passengerList){
        System.out.println("\n\nSort passengers by age and then by name: ");
        Collections.sort(passengerList, new Comparator<Passenger>() {
            @Override
            public int compare(Passenger o1, Passenger o2) {
                int ageComparison = Integer.compare(o1.getAge(), o2.getAge());

                if (ageComparison == 0) {
                    return o1.getName().compareTo(o2.getName());
                }
                return ageComparison;
            }});

        for(Passenger passenger : passengerList){
            System.out.println(passenger);
        }
    }
    public static void sortPassengersByGenderThenPassengerNumber(ArrayList<Passenger> passengerList){
        System.out.println("\n\n Sort passengers by gender then by passenger number");

        Collections.sort(passengerList, new Comparator<Passenger>() {
            @Override
            public int compare(Passenger p1, Passenger p2) {
                int genderComparison = p1.getGender().compareTo(p2.getGender());

                if(genderComparison ==0){
                    return p1.getPassengerId().compareTo(p2.getPassengerId());
                }
                return genderComparison;
            }
        });
        for (Passenger passenger: passengerList){
            System.out.println(passenger);
        }
    }
    public static void sortPassengersByFareThenSurvival(ArrayList<Passenger> passengerList){
        System.out.println("\n\n Sort passengers by fare then by survival: ");

        Collections.sort(passengerList, new Comparator<Passenger>() {
         @Override
         public int compare (Passenger p1, Passenger p2){
             int fareComparison = Double.compare(p1.getFare(), p2.getFare());

             if(fareComparison == 0){
                 return Integer.compare(p1.getSurvived(), p2.getSurvived());
             }
             return fareComparison;
            }
        });
        for(Passenger passenger : passengerList){
            System.out.println(passenger);
        }
    }
    public static void sortPassengersByTicketClass(ArrayList<Passenger> passengerList){
        System.out.println("\n\nSort by passengers' ticket: ");
        Collections.sort(passengerList, new Comparator<Passenger>(){
            @Override
            public int compare (Passenger p1, Passenger p2){
                return p1.getPassengerClass().compareTo(p2.getPassengerClass());
            }
        });
        for (Passenger passenger : passengerList){
            System.out.println(passenger);
        }
    }

    public static void sortPassengersByAge(ArrayList<Passenger> passengerList){
        System.out.println("\n\nSort passengers by age: ");
        Collections.sort(passengerList, new Comparator<Passenger>(){
            @Override
            public int compare (Passenger p1, Passenger p2){
                return Integer.compare(p1.getAge(), p2.getAge());
            }
        });
        for (Passenger passenger : passengerList){
            System.out.println(passenger);
        }
    }
    public static void sortPassengersByTicketNumberLambda(ArrayList<Passenger> passengerList){
        System.out.println("\n\nSort passengers by ticket number (using Lambda): ");
        Collections.sort(passengerList, (p1, p2) -> p1.getTicketNumber().compareTo(p2.getTicketNumber()));

    for(Passenger passenger : passengerList) {
        System.out.println(passenger);
    }
    }
}