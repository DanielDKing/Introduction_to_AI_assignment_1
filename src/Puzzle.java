import java.util.ArrayList;
import java.util.Arrays;

public class Puzzle {
    int puzzleValues[];
    int zeroLocation;
    int sideSize;
    String lastAction;
    
    
    public Puzzle(String puzzleSize, String puzzleValues){
        //array = new int[puzzleSize * puzzleSize];
        String []array = puzzleValues.split("-");
        this.puzzleValues  = Arrays.asList(array).stream().mapToInt(Integer::parseInt).toArray();
        this.sideSize = Integer.parseInt(puzzleSize);

        // Finding the location of the empty cell
        for (int index=0; index<this.puzzleValues.length; index++)
        {
            if (this.puzzleValues[index] == 0){
                this.zeroLocation = index;
                break;
            }
        }
    }

    public Puzzle(int[] puzzleValues){
        //array = new int[puzzleSize * puzzleSize];

        this.puzzleValues  = Arrays.copyOf(puzzleValues, puzzleValues.length);
        this.sideSize = (int)Math.sqrt(puzzleValues.length);

        // Finding the location of the empty cell
        for (int index=0; index<this.puzzleValues.length; index++)
        {
            if (this.puzzleValues[index] == 0){
                this.zeroLocation = index;
                break;
            }
        }
    }
    public Puzzle(int[] puzzleValues, String lastAction){
        //array = new int[puzzleSize * puzzleSize];
        this.lastAction = lastAction;
        this.puzzleValues  = Arrays.copyOf(puzzleValues, puzzleValues.length);
        this.sideSize = (int)Math.sqrt(puzzleValues.length);

        // Finding the location of the empty cell
        for (int index=0; index<this.puzzleValues.length; index++)
        {
            if (this.puzzleValues[index] == 0){
                this.zeroLocation = index;
                break;
            }
        }
    }

    public boolean left(){
        if ((this.zeroLocation + 1) % this.sideSize == 0){
            return false;
        }

        this.puzzleValues[this.zeroLocation] = this.puzzleValues[this.zeroLocation + 1];
        this.zeroLocation++;
        this.puzzleValues[this.zeroLocation] = 0;

        this.lastAction = "L";
        return true;
    }

    public boolean right(){
        if ((this.zeroLocation + 1) % this.sideSize == 1){
            return false;
        }

        this.puzzleValues[this.zeroLocation] = this.puzzleValues[this.zeroLocation - 1];
        this.zeroLocation--;
        this.puzzleValues[this.zeroLocation] = 0;

        this.lastAction = "R";
        return true;
    }

    public boolean up(){
        if (this.zeroLocation / this.sideSize == this.sideSize - 1){
            return false;
        }

        this.puzzleValues[this.zeroLocation] = this.puzzleValues[this.zeroLocation + this.sideSize];
        this.zeroLocation += this.sideSize;
        this.puzzleValues[this.zeroLocation] = 0;

        this.lastAction = "U";
        return true;
    }

    public boolean down(){
        //if (this.zeroLocation + this.sideSize > this.puzzleValues.length - 1 ){
        if (this.zeroLocation / this.sideSize == 0){
            return false;
        }

        this.puzzleValues[this.zeroLocation] = this.puzzleValues[this.zeroLocation - this.sideSize];
        this.zeroLocation -= this.sideSize;
        this.puzzleValues[this.zeroLocation] = 0;
        this.lastAction = "D";

        return true;
    }

    public ArrayList<Puzzle> posibleNextStates(){
        ArrayList<Puzzle> states = new ArrayList<Puzzle>();
        Puzzle tempPuzzle = new Puzzle(this.puzzleValues);//up down left right
        if (tempPuzzle.up()){
            states.add(new Puzzle(tempPuzzle.puzzleValues, "U"));
            tempPuzzle.down();
        }
        if (tempPuzzle.down()){
            states.add(new Puzzle(tempPuzzle.puzzleValues, "D"));
            tempPuzzle.up();
        }
        if (tempPuzzle.left()){
            states.add(new Puzzle(tempPuzzle.puzzleValues, "L"));
            tempPuzzle.right();
        }
        if (tempPuzzle.right()){
            states.add(new Puzzle(tempPuzzle.puzzleValues, "R"));
            tempPuzzle.left();
        }

        return states;
    }

    public void print(){
        for (int index = 0; index <this.puzzleValues.length; index++){
            System.out.print(this.puzzleValues[index] + ", ");

            if ((index + 1) % this.sideSize == 0){
                System.out.println("");
            }
        }
    }
}
