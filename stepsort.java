public class stepsort {
    private int[][] sortarray = { new int[3], new int[3]};

    public stepsort()
    {
        arrayfill();
    }

    public void execute(String mode){
        int[] temparray = new int[3];

        for (int row = 0; row < sortarray.length; row++){

            for (int col = 0; col < sortarray[row].length; col++){
                temparray[col] = sortarray[row][col];
            }

            sort(temparray, mode);

            for (int id = 0; id < sortarray[row].length; id++){
                sortarray[row][id] = temparray[id];
            }
        }
    }

    public void sort(int[] passarray, String mode){
        int[] temparray = new int[3];
        switch (mode) {
            case "bubble":
                break;

            case "insertion":
                break;

            default:
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
