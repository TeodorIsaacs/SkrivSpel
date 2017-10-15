import javax.swing.*;
import java.awt.*;
import java.io.UnsupportedEncodingException;

public class Main {
    public Parser p;
    public Functions f;
    public static final int SCREEN_WIDTH = Constants.SCREEN_WIDTH;
    public static final int SCREEN_HEIGHT = Constants.SCREEN_HEIGHT;

    public static void main(String[] args) throws UnsupportedEncodingException {
        Main main = new Main();
        JFrame frame = new JFrame();
        frame.setFocusable(true);

        //frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        int screenWidth = Toolkit.getDefaultToolkit().getScreenSize().width;
        int screenHeight = Toolkit.getDefaultToolkit().getScreenSize().height;
        int xBound = (screenWidth - Constants.SCREEN_WIDTH)/2;
        int yBound = (screenHeight - Constants.SCREEN_HEIGHT)/2;
        frame.setBounds(xBound, yBound, Constants.SCREEN_WIDTH, Constants.SCREEN_HEIGHT);
        frame.setUndecorated(true);
        frame.setVisible(true);

        main.f = new Functions();
        main.p = new Parser(main.f);
        Graphics gf = new Graphics(main.f);
        frame.add(gf);
        frame.setVisible(true);
        frame.addKeyListener(new KeyInput(main));

        while (true) {
            gf.invalidate();
            gf.repaint();
            main.f.tick();
            try {
                Thread.sleep(20);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}

