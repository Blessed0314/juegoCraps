package craps;
/**
 * ModelCraps apply craps rules
 * state = 1 => Natural winner
 * state = 2 => Craps looser.
 * state = 3 => Set point.
 * state = 4 => Get pont again winner.
 * state = 5 => Get 7 looser
 * default => Roll again
 * @author V.1.0.0 date 26/04/2023
 */

public class ModelCraps {
    private Dado dado1, dado2;
    private int launch, point, state, flag;
    private String stateToString;
    private int[] caras;


    public ModelCraps(){
        dado1 = new Dado();
        dado2 = new Dado();
        caras = new int[2];
        flag = 0;
    }

    public void calculateLaunch(){
        caras[0] = dado1.getCara();
        caras[1] = dado2.getCara();
        launch = caras[0] = caras[1];

    }

    public void determineGame (){
        if (flag == 0){
            if (launch == 7 || launch == 11){
                state = 0;
            }else{
                if (launch == 3 || launch == 2 || launch == 12){
                    state = 2;
                }else{
                    state = 3;
                    point = launch;
                    flag = 1;
                }
            }
        }else{
            //point's round
            roundPoint();
        }

    }

    private void roundPoint() {
        if (launch == point){
            state = 4;
            flag = 0;
        }
        if (launch == 7){
            state = 5;
            flag = 0;
        }
    }

    public int getLaunch() {
        return launch;
    }

    public int getPoint() {
        return point;
    }

    public String getStateToString() {
        switch (state){
            case 1: stateToString = "You roll natural, you have won";
                    break;
            case 2: stateToString = "You roll Craps, you have lost";
                    break;
            case 3: stateToString = "you set point on " + point + " throw again!"
                                    +"\n if you roll 7 you will lose but if you roll " + point +" again, you will win";
                    break;
            case 4: stateToString = "you roll " + point + " again, you have won";
                    break;
            case 5: stateToString = "you roll 5 before that " + point + ", you have won";
                    break;
            default: stateToString = "you still don't get a point, roll again";
        }
        return stateToString;
    }

    public int[] getCaras() {
        return caras;
    }
}



