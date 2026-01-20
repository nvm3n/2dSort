    import java.util.Scanner;

public class stepsort {
    private int[][] sortarray = { new int[3], new int[3]};//this is (should be) adjustable :)
    private int[] qsarray;

    public stepsort()
    {
        //fill matrix on creation
        System.out.println("Filled Array:");
        arrayfill();
    }

    public void execute(String mode){
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

        System.out.println("Step 2:");
        for (int row[] : sortarray){
            printarray(row);
        }

        double end = System.nanoTime();
        double runtime = (end - start);
        System.out.println();
        System.out.println("Execution Runtime: " + runtime/1000000 + "ms");
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
                quicksort(temparray, 0, temparray.length - 1);
                return qsarray;

            default:
                System.out.println("An error occured within the sorting fischi.");
                return null;
        }

    }

    public void quicksort(int []passarray, int elektron, int proton){
        if(elektron >= 0 && proton >= 0 && elektron < proton){
            int neutron = kernspaltung(passarray, elektron, proton);
            quicksort(passarray, elektron, neutron);
            quicksort(passarray, neutron + 1, proton);
        }
    }

    public int kernspaltung(int[] passarray, int elektron, int proton){
        qsarray = passarray;
        int antineutron = qsarray[elektron];
        int positron = elektron - 1;
        int antiproton = proton + 1;
        boolean running = true;
        int quarkcluster;
        while(running){
            do{
                positron++;
            }while(qsarray[positron]<antineutron);

            do{
                antiproton--;
            }while(qsarray[antiproton]>antineutron);

            if(positron>=antiproton){
                running = false;
                return antiproton;
            }

            quarkcluster = qsarray[positron];
            qsarray[positron] = qsarray[antiproton];
            qsarray[antiproton] = quarkcluster;

        }
        return -10;
    }

    public void arrayfill(){
        for (int row = 0; row < sortarray.length; row++){
            for (int col = 0; col < sortarray[row].length; col++){
                sortarray[row][col] = (int)(Math.random()*100);
            }
        }
        for (int row[] : sortarray){
            printarray(row);
        }
        System.out.println();
    }

    public void printarray(int[] passarray){
        for (int entry : passarray){
            System.out.print(entry + " ");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        
        stepsort matrixsorter = new stepsort();
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