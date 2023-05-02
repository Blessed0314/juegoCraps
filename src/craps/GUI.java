package craps;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;



/**
 * This class is used for ...
 * @autor Christian Daniel Villegas christian.villegas@correounivalle.edu.co
 * @version v.1.0.0 date:21/03/2023
 */
public class GUI extends JFrame {
    private static final String INITIAL_MESSAGE = "Welcome to the game of Craps"
            +"\npress the roll button to start the game"
            +"\nif your first roll is 7 or 11 you win with natural"
            +"\nif your first throw is 2, 3 or 11 you lose with Craps"
            +"\nif you roll any other value you will set the point"
            +"\nyou being on point you must launch again, but now,"
            +"\nyou will win if you roll again the value set in point"
            +"\nif you roll 7 before that, you will lose";

    private Header headerProject;
    private JLabel dice1, dice2;
    private JButton roll;
    private JPanel panelDice, panelResults;
    private ImageIcon imageIcon;
    private JTextArea results;
    private Listener listener, mouseListener;
    private ModelCraps modelCraps;


    /**
     * Constructor of GUI class
     */
    public GUI() {
        initGUI();

        //Default JFrame configuration
        this.setTitle("Play Craps");
        //this.setSize(200,100);
        this.pack();
        this.setResizable(true);
        this.setVisible(true);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    /**
     * This method is used to set up the default JComponent Configuration,
     * create Listener and control Objects used for the GUI class
     */
    private void initGUI() {
        //Set up JFrame Container's Layout
        //Create Listener Object or Control Object
        listener = new Listener();
        modelCraps = new ModelCraps();
        //Set up JComponents
        headerProject = new Header("Craps game table", Color.BLACK);
        this.add(headerProject,BorderLayout.NORTH); //Change this line if you change JFrame Container's Layout

        imageIcon = new ImageIcon(getClass().getResource("/resource/6T.png"));
        dice1 = new JLabel(imageIcon);
        dice2 = new JLabel(imageIcon);

        roll = new JButton("Roll");
        roll.addActionListener(listener);

        panelDice = new JPanel();
        panelDice.setPreferredSize(new Dimension(300,235) );
        panelDice.setBorder(BorderFactory.createTitledBorder("Your Dices"));
        panelDice.add(dice1);
        panelDice.add(dice2);
        panelDice.add(roll);

        this.add(panelDice,BorderLayout.CENTER);

        results = new JTextArea(7,31);
        results.setText(INITIAL_MESSAGE);
        results.setBorder(BorderFactory.createTitledBorder("What should you do?"));;
        JScrollPane scroll = new JScrollPane(results);
        this.add(scroll,BorderLayout.EAST);
    }

    /**
     * Main process of the Java program
     * @param args Object used in order to send input data from command line when
     *             the program is execute by console.
     */
    public static void main(String[] args){
        EventQueue.invokeLater(() -> {
            GUI miProjectGUI = new GUI();
        });
    }

    /**
     * inner class that extends an Adapter Class or implements Listeners used by GUI class
     */
    private class Listener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            modelCraps.calculateLaunch();
            int[] faces = modelCraps.getCaras();

            imageIcon = new ImageIcon(getClass().getResource("/resource/"+faces[0]+"T.png"));
            dice1.setIcon(imageIcon);
            imageIcon = new ImageIcon(getClass().getResource("/resource/"+faces[1]+"T.png"));
            dice2.setIcon(imageIcon);

            modelCraps.determineGame();
            results.setText(modelCraps.getStateToString()[1]);
        }
    }
}
