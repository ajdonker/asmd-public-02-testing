package master.a01a.sol2;

import javax.swing.*;
import java.util.*;
import java.util.List;
import java.awt.*;
import java.awt.event.ActionListener;

public class GUI extends JFrame {
    
    private static final long serialVersionUID = -6218820567019985015L;
    private final Map<JButton, Position> cells = new HashMap<>();
    private final Logic logic;
    private final Runnable onGameOver;

    public GUI(int size) {
        this(size, new LogicImpl(size), () -> System.exit(0));
    }
    public GUI(int size, Logic logic, Runnable onGameOver) {
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setSize(70*size, 70*size);
        this.logic = Objects.requireNonNull(logic);
        this.onGameOver = Objects.requireNonNull(onGameOver);
        
        JPanel panel = new JPanel(new GridLayout(size,size));
        this.getContentPane().add(panel);
        
        ActionListener al = e -> {
            var jb = (JButton)e.getSource();
            this.logic.hit(this.cells.get(jb));
            for (var entry: this.cells.entrySet()){
                entry.getKey().setText(
                    this.logic
                        .getMark(entry.getValue())
                        .map(String::valueOf)
                        .orElse(" "));
            }
            if (this.logic.isOver()){
                this.onGameOver.run();
            }
        };
                
        for (int i=0; i<size; i++){
            for (int j=0; j<size; j++){
            	final JButton jb = new JButton();
                this.cells.put(jb, new Position(j,i));
                jb.addActionListener(al);
                panel.add(jb);
            }
        }
        this.setVisible(true);
    }
    
}
