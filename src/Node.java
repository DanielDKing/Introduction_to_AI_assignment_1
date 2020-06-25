public class Node {
    Node parent;
    int[] puzzle;
    String action;

    public Node(int[] puzzle){
        this.parent = null;
        this.puzzle = puzzle;
        this.action = "";
    }

    public Node(int[] puzzle, Node parent, String action) {
        this.parent = parent;
        this.puzzle = puzzle;
        this.action = action;
    }
}
