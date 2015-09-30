import javax.swing.*;

/**
 * Created by novoselov on 30.09.2015.
 */
public class FunnyRaceWindow extends JFrame {
    JTextArea jTextArea;
    public FunnyRaceWindow () {
        setSize(800,400);
        setTitle("Веселые бега");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        jTextArea = new JTextArea();
//        jTextArea.setEnabled(false);
        add(jTextArea);
    }

    public void openWindow () {
        setVisible(true);
    }

    public void clearAndSetText (String text) {
        jTextArea.setText(text);
    }

    public void appendText (String text) {
        jTextArea.append(text);
    }

}
