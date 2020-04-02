import java.awt.GridLayout;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class wordSearchOne extends JFrame {
    
    private ArrayList<ArrayList<wBlock>> puzzle;
    private wBlock tempBlock;
    
    public wordSearchOne(ArrayList<ArrayList<wBlock>> wordSearchData) {
        
        
        puzzle = wordSearchData;
        
        
        JPanel game = new JPanel(new GridLayout(puzzle.size(),puzzle.size()));
        add(game);
        
        for (int x = 0; x < puzzle.size(); x++) {
            
            
            for (int y = 0; y < puzzle.get(0).size(); y++) {
                
                tempBlock = puzzle.get(x).get(y);
                game.add(tempBlock);
                
            }
            
        }
        
        pack();
        setSize(500,500);
        setResizable(false);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setVisible(true);
    
    }
    
}
