import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.*;
import java.util.stream.IntStream;

public class Main {

    public static void path(Node node){
        String moves = "";
        while (node.parent != null){
            moves += node.action;
            node = node.parent;
        }

        StringBuilder reverseMoves = new StringBuilder();
        reverseMoves.append(moves);
        System.out.println(reverseMoves.reverse());

        try {
            FileWriter myWriter = new FileWriter(".\\src\\output.txt");
            myWriter.write(reverseMoves.reverse().toString());
            myWriter.close();
        } catch (IOException e){
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    public static boolean findState(int[] state, ArrayList<Node> nodeList){
        for (Node node: nodeList) {
            if (Arrays.equals(node.puzzle, state)){
                return true;
            }
        }

        return false;
    }

    public static String BFS(Puzzle puzzle, int[] target){
        Queue<Node> open = new LinkedList<>();
        ArrayList<Node> close = new ArrayList<>();

        open.add(new Node(puzzle.puzzleValues));

        while (!open.isEmpty()){
            Node nextNode = open.remove();
            //close.add(Arrays.copyOf(nextNode.puzzle, nextNode.puzzle.length));
            close.add(nextNode);
            //for s in expand
            Puzzle tempPuzzle = new Puzzle(nextNode.puzzle);
            for (Puzzle currPuzzle: tempPuzzle.posibleNextStates()) {
                if (!findState(currPuzzle.puzzleValues, close) && !findState(currPuzzle.puzzleValues, new ArrayList<Node>(open))) {
                    Node newNode = new Node(currPuzzle.puzzleValues, nextNode, currPuzzle.lastAction);
                    // Check if the required states achieved
                    if (Arrays.equals(currPuzzle.puzzleValues, target)){
                        path(newNode);

                        return "";
                    }

                    open.add(newNode);
                }
            }
        }

        return "";
    }

    public static String[] readInputFile(){
        String[] retString = new String[3];

        try {
            File myObj = new File(".\\src\\input.txt");
            //Scanner scan = new Scanner(System.in);
            //System.out.println("Enter input file path");
            //String inputPath = scan.nextLine();
            //File myObj = new File(inputPath);
            Scanner myReader = new Scanner(myObj);
            retString[0] = myReader.nextLine();
            retString[1] = myReader.nextLine();
            retString[2] = myReader.nextLine();

        } catch (FileNotFoundException e){
            System.out.println("An error occurred.");
            e.printStackTrace();
        }

        return retString;
    }

    public static void main(String[] args) {
        String[] puzzleData = readInputFile();
        Puzzle p = new Puzzle(puzzleData[1], puzzleData[2]);
        int puzzleSideSize = Integer.parseInt(puzzleData[1]);
        int[] target = IntStream.range(1, puzzleSideSize*puzzleSideSize+1).toArray();
        target[target.length - 1] = 0;

        switch (puzzleData[0]){
            case "1":
                // IDS implementation
                break;
            case "2":
                BFS(p,target);
                break;
            case "3":
                // A* implementation
                break;
        }
    }
}
