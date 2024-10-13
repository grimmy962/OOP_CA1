package org.example;
// CA1
import java.io. * ;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        String fileName = "titanic-data-100.csv"; // file should be in the project folder (below pom.xml)

        ArrayList<Passenger> passengerList= new ArrayList<>();

        loadPassengerDataFromFile( passengerList, fileName);

        displayAllPassengers( passengerList );


        // Assignment: Implement and test the following methods.
        // See the description of each method in the CA1 Specification PDF file from Moodle

          getPassengerNames(passengerList);
          getPassengersContainingNames(passengerList, "William");
          getPassengersOlderThan(passengerList, 30);
          countPassengersByGender(passengerList);
          sumFares(passengerList);
//        maleSurvivors();
//        ticketOwner();
//        averageAge();
//        getPassengersByTicketClass();
//        sortPassengersByPassengerId()
//        sortPassengersByName();
//        sortPassengersByAgeThenName();
//        sortPassengersByGenderThenPassengerNumber()
//        sortPassengersByFareThenSurvival();
//        sortPassengersByTicketClass()
//        sortPassengersByAge();
//        sortPassengersByTicketNumberLambda();
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
}