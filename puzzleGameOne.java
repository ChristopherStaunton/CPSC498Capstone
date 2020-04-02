import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

@SuppressWarnings("serial")
public class puzzleGameOne extends JFrame {
    
    private ArrayList<String> data = new ArrayList<String>(Arrays.asList("one", "two", "three", "four", "five"));
    private ArrayList<ArrayList<wBlock>> puzzle;
    private String searchTopic;
    private int longestStringLength;
    private final int maxPermittedWordLength = 9;
    
    //gets words based on input
    //and puts it into data list
    private void getData() {/*
    	runPython get = new runPython();
    	try {
			Process p = Runtime.getRuntime().exec("python /home/christopher/Desktop/pythonTesting/roughDraft498.py");
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
		}*/
    }
    
    private boolean checkData() {
        return true;
    }
    
    //-------------------------------------------------
    
    private void hideIntro() {
        setVisible(false);
    }
    
    private boolean corDist(String s, String d, int x, int y) {
        int l = s.length();
        boolean fit = true;
        int wordIndex = 0;
        if (d == "U") {
            fit = true;
            wordIndex = 0;
            if (l <= puzzle.get(x).size() - y) {//? length, cord etc accuracy?
                for (int a = y; (a < puzzle.get(x).size() && a < l ); a++) {//?
                    if (puzzle.get(x).get(a) == null || s.charAt(wordIndex) == puzzle.get(x).get(a).value) {
                        wordIndex++;
                    }
                    else {
                        fit = false;
                        break;
                    }
                }
                wordIndex = 0;
                if (fit) {
                    for (int a = y; (a < puzzle.get(x).size() && a < l ); a++) {//?
                        if (puzzle.get(x).get(a) == null) {
                            puzzle.get(x).set(a, new wBlock(s, x, y, d, wordIndex));
                        }
                        else {
                            puzzle.get(x).get(a).upUse();
                        }
                        wordIndex++;
                    }
                }
            }
        }
        else if (d == "uR") {
            
        }
        else if (d == "R") {
            
        }
        else if (d == "dR") {
            
        }
        else if (d == "D") {
            
        }
        else if (d == "dL") {
            
        }
        else if (d == "L") {
            
        }
        else if (d == "uL") {
            
        }
        else {
            return false;
        }
        
        return false;
    }
    
    private boolean checkAdded(String s, int x, int y) {
        //check up
        if (corDist(s, "U", x, y)) {
            return true;
        }
        //--
        
        //check right
        
        //--
        
        //check down
        
        //--
        
        //check left
        
        //--
        
        return false;
    }
    
    private boolean isRoomThenAdd(String s) {
        for (int x = 0; x < puzzle.size(); x++) {
            for (int y = 0; y < puzzle.get(0).size(); y++) {
                if (checkAdded(s, x, y)) {
                    return true;
                }
            }
        }
        return false;
    }
    
    //puts words from data list
    //into puzzle matrix
    private void convertData() {
        for (String s : data) {
            if (isRoomThenAdd(s)) {
               //
            }
        }
    }
    
   //-------------------------------------------------
    
    //finds a random letter
    private char getRandomLetter() {
        return 'x';
    }
    
    //fills blanks in matrix
    // with random variables
    private void fillBlank() {
        for (int x = 0; x < puzzle.size(); x++) {
            for (int y = 0; y < puzzle.get(x).size(); y++) {
                if (puzzle.get(x).get(y) == null) {
                    puzzle.get(x).set(y, new wBlock(getRandomLetter(), x, y));
                }
            }
        }
    }
    
    private void buildBlankPuzzle() {
        longestStringLength = 0;
        for (String s : data) {
            if (s.length() > longestStringLength && maxPermittedWordLength >= s.length()) {
                longestStringLength = s.length();
            }
            else if (maxPermittedWordLength < s.length()) {
                data.remove(new String(s));
            }
        }
        for (int x = 0; x < longestStringLength + 5; x++) {
            puzzle.add(new ArrayList<wBlock>());
            for (int y = 0; y < longestStringLength + 5; y++) {
                puzzle.get(x).add(null);
            }
        }
    }
    
    //handles puzzle creation
    //after input
    private boolean manage() {
        puzzle = new ArrayList<ArrayList<wBlock>>();
        getData();
        if (!checkData()) {
            return false;
        }
        buildBlankPuzzle();
        convertData();
        fillBlank();
        return true;
    }
    
    //hud for startup
    public puzzleGameOne() {
        
        JPanel inputTopic = new JPanel(new FlowLayout(FlowLayout.CENTER,10,5));
        add(inputTopic);
        
        JLabel inputTitle = new JLabel("Input Topic");
        inputTopic.add(inputTitle);
        
        JTextField input = new JTextField(20);
        inputTopic.add(input);
        
        JButton startButton = new JButton("Start");
        inputTopic.add(startButton);
        
        ActionListener startButtonListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (input.getText() == null || input.getText() == "") {
                    //inform blank
                }
                else {
                    searchTopic = input.getText();
                    if (manage()) {
                        wordSearchOne theGame = new wordSearchOne(puzzle);
                        hideIntro();
                    }
                    else {
                        //inform failed search
                    }
                }
                
            }
        };
        startButton.addActionListener(startButtonListener);
        
        pack();
        setSize(300,150);
        setResizable(false);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        
    }
    
    public static void main(String[] args) {
        JFrame frame = new puzzleGameOne();
        frame.setVisible(true);
    }
    
}
