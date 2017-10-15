import java.awt.event.KeyEvent;
import java.io.UnsupportedEncodingException;

public class Parser {

    private boolean capital = false;
    private Functions f;

    public Parser(Functions f) {
        this.f = f;
    }

    public void keyPressed(KeyEvent e) throws UnsupportedEncodingException {
        switch (f.state) {
            case SETUP:
                switch (e.getKeyCode()) {
                    case KeyEvent.VK_ENTER:
                        f.activeLibrary = f.allLibraries[f.hoveredLibrary];
                        f.stateTransition();
                        break;
                    case KeyEvent.VK_DOWN:
                        f.hoveredLibrary = Math.min(f.allLibraries.length - 1, f.hoveredLibrary + 1);
                        break;
                    case KeyEvent.VK_UP:
                        f.hoveredLibrary = Math.max(0, f.hoveredLibrary -1);
                        break;
                }
                break;
            case ENDSCREEN:
                if (e.getKeyCode() == KeyEvent.VK_R)
                    f.reset();
                break;
            case DEAD:
                if (e.getKeyCode() == KeyEvent.VK_R)
                    f.reset();
                break;
            case PLAY:
                switch (e.getKeyCode()) {
                    case KeyEvent.VK_BACK_SPACE:
                        f.removeLastCharOfAllText();
                        f.increaseTimeLeftToDeath("");
                        break;
                    case KeyEvent.VK_SHIFT:
                        break;
                    case KeyEvent.VK_CONTROL:
                        break;
                    case KeyEvent.VK_ALT:
                        break;
                    case KeyEvent.VK_CAPS_LOCK:
                        break;
                    case KeyEvent.VK_META:
                        break;
                    default:
                        if (capital)
                            f.addText((String.valueOf(e.getKeyChar())).toUpperCase());
                        else
                            f.addText((String.valueOf(e.getKeyChar())));
                        break;
                }
                break;
        }
    }

    public void keyReleased(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_SHIFT) {
            capital = false;
        }
    }
}
