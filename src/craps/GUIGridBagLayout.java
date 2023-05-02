package craps;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;


public class GUIGridBagLayout extends JFrame {
    private static final String INITIAL_MESSAGE = "Welcome to the game of Craps"
            +"\nRules:\n1. Press the roll button to start the game."
            +"\n2. If your first roll is 7 or 11 you win with natural."
            +"\n3. If your first throw is 2, 3 or 11 you lose with Craps."
            +"\n4. If you roll any other value you will set the point."
            +"\n5. You being on point you must launch again, but now."
            +"\n6. You will win if you roll again the value set in point."
            +"\n7. If you roll 7 before that, you will lose.";

    private Header headerProject;
    private JLabel dice1, dice2;
    private JButton roll, help, exit;
    private JPanel panelDice;
    private ImageIcon imageIcon;
    private JTextArea results, messages;
    private Listener listener;
    private Actions mouseListener;
    private ModelCraps modelCraps;
    private int mouseX, mouseY;

    public GUIGridBagLayout(){
        initGUI();
        //Default JFrame configuration
        this.setTitle("Play Craps");
        this.setUndecorated(true);
        this.setBackground(new Color(255,0,0,192));
        this.pack();
        this.setResizable(true);
        this.setVisible(true);
        this.setLocationRelativeTo(null);
        //this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    private void initGUI(){
        //Set up JFrame Container's Layout
        this.getContentPane().setLayout(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();
        //Create Listener Object or Control Object
        listener = new Listener();
        mouseListener = new Actions();
        modelCraps = new ModelCraps();
        //Set up JComponents
        headerProject = new Header("Craps game table", Color.BLACK);
        headerProject.addMouseListener(mouseListener);
        headerProject.addMouseMotionListener(mouseListener);
        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.gridwidth = 2;
        constraints.fill = GridBagConstraints.HORIZONTAL;


        add(headerProject,constraints);

        help = new JButton("?");
        help.addActionListener(listener);
        constraints.gridx = 0;
        constraints.gridy = 1;
        constraints.gridwidth = 1;
        constraints.fill = GridBagConstraints.NONE;
        constraints.anchor = GridBagConstraints.LINE_START;

        add(help,constraints);

        exit = new JButton("Exit");
        exit.addActionListener(listener);
        constraints.gridx = 1;
        constraints.gridy = 1;
        constraints.gridwidth = 1;
        constraints.fill = GridBagConstraints.NONE;
        constraints.anchor = GridBagConstraints.LINE_END;

        add(exit,constraints);

        imageIcon = new ImageIcon(getClass().getResource("/resource/6T.png"));
        dice1 = new JLabel(imageIcon);
        dice2 = new JLabel(imageIcon);
        panelDice = new JPanel();
        panelDice.setPreferredSize(new Dimension(300,235) );
        panelDice.setBorder(BorderFactory.createTitledBorder("Your Dices"));
        panelDice.add(dice1);
        panelDice.add(dice2);
        constraints.gridx = 0;
        constraints.gridy = 2;
        constraints.gridwidth = 1;
        constraints.fill = GridBagConstraints.BOTH;
        constraints.anchor = GridBagConstraints.CENTER;

        add(panelDice,constraints);

        results = new JTextArea(7,31);
        results.setBorder(BorderFactory.createTitledBorder("Results"));
        results.setText("You must roll the dice");
        constraints.gridx = 1;
        constraints.gridy = 2;
        constraints.gridwidth = 1;
        constraints.fill = GridBagConstraints.BOTH;
        constraints.anchor = GridBagConstraints.CENTER;

        add(results,constraints);

        roll = new JButton("Roll");
        roll.addActionListener(listener);
        constraints.gridx = 0;
        constraints.gridy = 3;
        constraints.gridwidth = 2;
        constraints.fill = GridBagConstraints.NONE;
        constraints.anchor = GridBagConstraints.CENTER;

        add(roll,constraints);

        messages = new JTextArea(3,31);
        messages.setText("Before playing, see the rules of the game by pressing the button (?)");
        messages.setBorder(BorderFactory.createTitledBorder("Message"));
        constraints.gridx = 0;
        constraints.gridy = 4;
        constraints.gridwidth = 2;
        constraints.fill = GridBagConstraints.NONE;
        constraints.anchor = GridBagConstraints.CENTER;

        add(messages,constraints);

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
            if (e.getSource()==roll){
                modelCraps.calculateLaunch();
                int[] faces = modelCraps.getCaras();

                imageIcon = new ImageIcon(getClass().getResource("/resource/"+faces[0]+"T.png"));
                dice1.setIcon(imageIcon);
                imageIcon = new ImageIcon(getClass().getResource("/resource/"+faces[1]+"T.png"));
                dice2.setIcon(imageIcon);

                modelCraps.determineGame();
                results.setText(modelCraps.getStateToString()[0]);
                messages.setText(modelCraps.getStateToString()[1]);
            }else{
                if(e.getSource()==help){
                    JOptionPane.showMessageDialog(null,INITIAL_MESSAGE);
                }else{
                    System.exit(0);
                }
            }

        }
    }
    private class Actions  implements MouseListener, MouseMotionListener {


        @Override
        public void mouseClicked(MouseEvent e) {
            mouseX = e.getX();
            mouseY = e.getY();
        }

        @Override
        public void mousePressed(MouseEvent e) {

        }

        @Override
        public void mouseReleased(MouseEvent e) {

        }

        @Override
        public void mouseEntered(MouseEvent e) {

        }

        @Override
        public void mouseExited(MouseEvent e) {

        }

        @Override
        public void mouseDragged(MouseEvent e) {
            int posX = e.getXOnScreen() - mouseX;
            int posY = e.getYOnScreen() - mouseY;
            setLocation(posX, posY);
        }

        @Override
        public void mouseMoved(MouseEvent e) {

        }
    }

}
