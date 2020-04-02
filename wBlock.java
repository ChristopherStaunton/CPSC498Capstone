import java.util.ArrayList;

import javax.swing.JButton;

//individual block in puzzle matrix
public class wBlock extends JButton {
    //character shown
    protected char value;
    //coordinates
    protected int x, y;
    //if part of actual word in puzzle
    protected boolean answer;
    // U, uR, R, dR, D, lD, L, uL
    protected String direction;
    //length of total word
    protected ArrayList<Integer> l;
    //the total word
    protected ArrayList<String> words;
    //index in total string
    protected ArrayList<Integer> indexes;
    //number of words used for in puzzle
    protected int count;
    
    
    
    public void upUse() {
        count++;
    }
    
    //find/found word method
    
    private void startUpVar() {
        l = new ArrayList<Integer>();
        words = new ArrayList<String>();
        indexes = new ArrayList<Integer>();
    }
    
    public wBlock(String word, int x, int y, String direction, int index) {
        super("" + (word.charAt(index)));
        startUpVar();
        value = word.charAt(index);
        this.x = x;
        this.y = y;
        answer = true;
        this.direction = direction;
        l.add(word.length());
        words.add(word);
        indexes.add(index);
        count = 1;
    }
    
    public wBlock(char letter, int x, int y) {
        super("" + letter);
        startUpVar();
        value = letter;
        this.x = x;
        this.y = y;
        answer = false;
        direction = null;
        l = null;
        words = null;
        indexes = null;
    }
    
}
