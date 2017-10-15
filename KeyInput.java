import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.UnsupportedEncodingException;

public class KeyInput extends KeyAdapter {
    Main main;

    public KeyInput(Main main) {
        this.main = main;
    }

    @Override
    public void keyPressed(KeyEvent e) {
        try {
            main.p.keyPressed(e);
        } catch (UnsupportedEncodingException e1) {
            e1.printStackTrace();
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        main.p.keyReleased(e);
    }
}