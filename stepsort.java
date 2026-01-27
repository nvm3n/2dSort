    import java.util.Scanner;

public class stepsort {
    private int[][] sortarray = { new int[3], new int[3]};//this is (should be) adjustable :)
    //external array needed for quicksort
    private int[] qsarray;

    public stepsort()
    {
        //fill matrix on creation
        System.out.println("Filled Array:");
        arrayfill();
    }

    public void execute(String mode){
        //get current time for runtime calculation
        double start = System.nanoTime();
        //sort rows
        for (int row = 0; row < sortarray.length; row++){
            //too lazy to do this in-place
            int[] temparray = new int[sortarray[row].length];
            //copy row to temparray
            for (int col = 0; col < sortarray[row].length; col++){
                temparray[col] = sortarray[row][col];
            }
            //sort row
            temparray = sort(temparray, mode);
            //copy sorted row to original array
            for (int id = 0; id < sortarray[row].length; id++){
                sortarray[row][id] = temparray[id];
            }
        }

        //cosmetic text this can be removed if class is changed to return for other classes
        System.out.println("Step 1:");
        for (int row[] : sortarray){
            printarray(row);
        }

        //sort columns
        //everything same as above just with columns
        for (int col1 = 0; col1 < sortarray[0].length; col1++){
            int[] temparray = new int[sortarray.length];
            for (int row1 = 0; row1 < sortarray.length; row1++){
                temparray[row1] = sortarray[row1][col1];
            }

            temparray = sort(temparray, mode);

            for (int id = 0; id < sortarray.length; id++){
                sortarray[id][col1] = temparray[id];
            }
        }
        
        //cosmetic text this can be removed if class is changed to return for other classes
        System.out.println("Step 2:");
        for (int row[] : sortarray){
            printarray(row);
        }

        //get end time
        double end = System.nanoTime();
        //calculate and print total runtime
        double runtime = (end - start);
        System.out.println();
        System.out.println("Execution Runtime: " + runtime/1000000 + "ms");//nanotime returns in nanoseconds
    }

    public int[] sort(int[] passarray, String mode){
        int[] temparray = passarray;
        //extensible!!!
        switch (mode) {
            case "bubble":

                //if no swap occurs we can skip all other checks
                boolean swap = false;
                int temp;
                int s = temparray.length;

                //do while loop is needed for the swapcheck method
                do {
                    swap = false;
                    for(int i = 0; i<s-1; i++){
                        //swippy swappy
                        if(temparray[i] > temparray[i+1]){
                            temp = temparray[i];
                            temparray[i] = temparray[i+1];
                            temparray[i+1] = temp;
                            swap = true;
                        }
                    }
                    //this lets us skip already sorted slots
                    s = s-1;
                }while(swap);
            
            return temparray;

            case "insertion":

                //we start with 1 because we compare with the element before
                for(int i = 1; i<temparray.length; i++){
                    int selection = temparray[i];
                    //selector chooses the position for comparison/insertion
                    int selector = i;
                    while(selector>0 && temparray[selector-1] > selection){
                        //copy elements one slot to the right if they are larger then the selected one
                        temparray[selector] = temparray[selector - 1];
                        //move selector one slot to the left
                        selector = selector - 1;
                    }
                    //insert selection at the selector´s position
                    temparray[selector] = selection;
                }
                return temparray;
            
            case "quick":
                //call external quicksort (recursive, not in place)
                quicksort(temparray, 0, temparray.length - 1);
                return qsarray;

            default:
                System.out.println("An error occured within the sorting fischi.");
                return null;
        }

    }

    //within the entire quicksort electron (negative) is the lower bound proton (positive) is the upper bound and neutron (neutral) is the pivot. The counterparts correspond to these (eg. positron corresponds to electron)
    public void quicksort(int []passarray, int elektron, int proton){
        if(elektron >= 0 && proton >= 0 && elektron < proton){
            int neutron = kernspaltung(passarray, elektron, proton);
            quicksort(passarray, elektron, neutron);
            quicksort(passarray, neutron + 1, proton);
        }
    }

    //For the partitioning scheme Hoare´s one is used as it is more efficient than the standard Lomuto scheme, although it might be more difficult to understand
    public int kernspaltung(int[] passarray, int elektron, int proton){
        qsarray = passarray;
        int antineutron = qsarray[elektron];
        int positron = elektron - 1;
        int antiproton = proton + 1;
        boolean running = true;
        //quarkcluster is our temporary variable used for swaps
        int quarkcluster;
        //this partition scheme moves the pointers toward each other, until they collide
        while(running){
            //on the left side, move through the array until an element isnt smaller then the pivot
            do{
                positron++;
            }while(qsarray[positron]<antineutron);

            //on the right side, move through the array until an element isnt larger then the pivot
            do{
                antiproton--;
            }while(qsarray[antiproton]>antineutron);

            //check if the pointers have crossed
            if(positron>=antiproton){
                running = false;
                return antiproton;
            }
            
            //swap elements
            quarkcluster = qsarray[positron];
            qsarray[positron] = qsarray[antiproton];
            qsarray[antiproton] = quarkcluster;

        }
        return -10;
    }

    //fill the array with random numbers
    public void arrayfill(){
        //go through every row inside the array
        for (int row = 0; row < sortarray.length; row++){
            //go through every element inside the element
            for (int col = 0; col < sortarray[row].length; col++){
                //fill the individual elements with a random number (100 is the range as math random returns a value from 0-1)
                sortarray[row][col] = (int)(Math.random()*100);
            }
        }
        //go through each element with a for each loop, this executes for every element in the array with the counter carrying the respective value from the array
        for (int row[] : sortarray){
            printarray(row);
        }
        System.out.println();
    }

    //goes through a onedimensional array printing each element with a for each loop
    public void printarray(int[] passarray){
        for (int entry : passarray){
            System.out.print(entry + " ");
        }
        System.out.println();
    }

    //execution method
    public static void main(String[] args) {
        
        //create a new object
        stepsort matrixsorter = new stepsort();
        //variable is needed for the reroll function
        boolean running = true;
        try(final Scanner inputscanner = new Scanner(System.in)){
            while(running){
                System.out.println("Enter sorting mode (bubble, insertion, quick) or reroll");
                String sortingmode = inputscanner.nextLine();
                System.out.println();
                switch (sortingmode) {
                    case "reroll":
                        matrixsorter.arrayfill();
                        break;
                    case "bubble":
                        matrixsorter.execute("bubble");
                        running = false;
                        break;
                    case "insertion":
                        matrixsorter.execute("insertion");
                        running = false;
                        break;
                    case "quick":
                        matrixsorter.execute("quick");
                        running = false;
                        break;
                    default:
                        System.out.println("Error: Not a valid input");
                        break;
                }
            }
        }

    }
}