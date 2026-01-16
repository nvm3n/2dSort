public class stepsort {
    private int[][] sortarray = { new int[3], new int[3]};

    public stepsort()
    {
        arrayfill();
    }

    public void execute(String mode){
        int[] temparray = new int[3];

        //sort rows
        for (int row = 0; row < sortarray.length; row++){

            for (int col = 0; col < sortarray[row].length; col++){
                temparray[col] = sortarray[row][col];
            }

            sort(temparray, mode);

            for (int id = 0; id < sortarray[row].length; id++){
                sortarray[row][id] = temparray[id];
            }
        }

        //sort columns
        for (int col1 = 0; col1 < sortarray[col1].length; col++){

            for (int row1 = 0; row1 < sortarray.length; row1++){
                temparray[col1] = sortarray[row1][col1];
            }

            sort(temparray, mode);

            for (int id = 0; id < sortarray.length; id++){
                sortarray[row1][id] = temparray[id];
            }
        }
        
    }

    public void sort(int[] passarray, String mode){
        int[] temparray = new int[3];
        switch (mode) {
            case "bubble":

                for (int i = 0; i< temparray.length - 1; i++) {
                    for ( int j = 0; j < temparray.length - 1 - i; j++) {
                        if (temparray[j] > temparray[j + 1]) {
                        int temp = temparray[j];
                        temparray[j] = temparray[j + 1];
                        temparray[j + 1] = temp;
                    }
                }
                return temparray;
            }
            case "insertion":

                


            default:
                System.out.printerr
                break;
        }

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
    }

    public void printarray(int[] passarray){
        for (int entry : passarray){
            System.out.print(entry);
        }
    }

}
