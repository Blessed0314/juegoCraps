package craps;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GUIGridBagLayout extends JFrame {
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
    private JButton roll, help, exit;
    private JPanel panelDice;
    private ImageIcon imageIcon;
    private JTextArea results, Messages;
    private Listener listener;
    private ModelCraps modelCraps;

    public GUIGridBagLayout(){
        initGUI();
        //Default JFrame configuration
        this.setTitle("Play Craps");
        this.pack();
        this.setResizable(true);
        this.setVisible(true);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    private void initGUI(){
        //Set up JFrame Container's Layout
        this.getContentPane().setLayout(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();
        //Create Listener Object or Control Object
        listener = new Listener();
        modelCraps = new ModelCraps();
        //Set up JComponents
        headerProject = new Header("Craps game table", Color.BLACK);
        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.gridwidth = 2;
        constraints.fill = GridBagConstraints.HORIZONTAL;
        this.add(headerProject,constraints);

        help = new JButton("?");
        help.addActionListener(listener);
        constraints.gridx = 0;
        constraints.gridy = 1;
        constraints.gridwidth = 1;
        constraints.fill = GridBagConstraints.NONE;
        constraints.anchor = GridBagConstraints.LINE_START;
        this.add(help,constraints);

        exit = new JButton("Exit");
        exit.addActionListener(listener);
        constraints.gridx = 1;
        constraints.gridy = 1;
        constraints.gridwidth = 1;
        constraints.fill = GridBagConstraints.NONE;
        constraints.anchor = GridBagConstraints.LINE_END;
        this.add(exit,constraints);

    }
    /**
     * Main process of the Java program
     * @param args Object used in order to send input data from command line when
     *             the program is execute by console.
     */
    public static void main(String[] args){
        EventQueue.invokeLater(() -> {
            GUIGridBagLayout miProjectGUI = new GUIGridBagLayout();
        });
    }
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
            results.setText(modelCraps.getStateToString());
        }
    }
}
