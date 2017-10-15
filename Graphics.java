import javax.swing.*;
import java.awt.*;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

public class Graphics extends JPanel {
    private Functions f;

    public Graphics(Functions f) {
        this.f = f;
    }

    @Override
    public void paint(java.awt.Graphics g) {
        Graphics2D g2d = (Graphics2D) g;

        switch (f.state) {
            case SETUP:
                paintSetUp(g2d);
                break;
            case DEAD:
                paintGameOver(g2d);
                break;
            case ENDSCREEN:
                paintCompleted(g2d);
                break;
            case WINSCREEN:
                paintCompleted(g2d);
                break;
            case PLAY:
                paintBackground(g2d);
                try {
                    paintText(g2d);
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
                paintTime(g2d);
                paintTimeLeft(g2d);
                break;
        }
    }

    private void paintSetUp(Graphics2D g) {
        paintBackground(g);
        String[] options = Constants.NAMES_OF_PHRASE_LIBRARIES;
        for (int i = 0; i < options.length; i++) {
            String word = options[i];
            int width = Constants.SCREEN_WIDTH/8;
            int height = Constants.SCREEN_HEIGHT/10;
            int x = Constants.SCREEN_WIDTH / 2 - width / 2;
            int y = Constants.SCREEN_HEIGHT / 3 - height / 2 + i * height * 2;
            g.setColor(Color.lightGray);
            if (i == f.hoveredLibrary) {
                g.setColor(Constants.COLOR);
            }
            g.drawRect(x, y, width, height);
            g.setFont(new Font("Garamond", Font.PLAIN, Constants.FONT_SIZE));
            int wordwidth = g.getFontMetrics().stringWidth(word);
            g.drawString(word, Constants.SCREEN_WIDTH / 2 - wordwidth / 2, y + height / 2 + Constants.FONT_SIZE / 3);
        }
    }


    private void paintCompleted(Graphics2D g) {
        paintBackground(g);
        g.setColor(Constants.COLOR);
        g.setFont(new Font("Garamond", Font.PLAIN, 20));
        String message = "Congratulations, you did it!";
        g.drawString(message, (Constants.SCREEN_WIDTH - textLen(g, message) )/ 2, Constants.SCREEN_HEIGHT / 3 - Constants.FONT_SIZE);
        message = "Talk to a moderator if you want to keep your masterpiece!";
        g.drawString(message, (Constants.SCREEN_WIDTH - textLen(g, message)) / 2, Constants.SCREEN_HEIGHT / 3);
    }

    private void paintTimeLeft(Graphics2D g) {
        long current = System.currentTimeMillis();
        long last = f.timeLastTyped;
        long div = (current - last);
        double delta = 1 - ((double) div / (double) (Constants.TYPE_TIMER));
        double width = Constants.SCREEN_WIDTH * delta / 3;
        double height = Constants.SCREEN_HEIGHT/50;
        g.setColor(Constants.COLOR);
        g.fillRect(Constants.SCREEN_WIDTH / 3, Constants.SCREEN_HEIGHT - 40, (int) width, (int) height);
    }

    private void paintGameOver(Graphics2D g) {
        paintBackground(g);
        g.setColor(Constants.COLOR);
        g.setFont(new Font(("Arial"), Font.PLAIN, 20));
        g.drawString("Game Over! Press R to retry", Constants.SCREEN_WIDTH / 2 - 140, Constants.SCREEN_HEIGHT / 2 - 10);
    }

    private void paintText(Graphics2D g) throws UnsupportedEncodingException {
        ArrayList<String> allTextRows = f.getAllText();
        int margin = Constants.DISPLAY_MARGIN;
        int iteration = 0;
        int row = 0;
        if (allTextRows.size() > Constants.MAX_NUM_TEXT_ROWS) {
            row = allTextRows.size() - Constants.MAX_NUM_TEXT_ROWS;
        }
        g.setColor(Constants.COLOR);
        g.setFont(new Font("Courier New", Font.PLAIN, Constants.FONT_SIZE));
        //alla utom sista gången
        for (; iteration < Constants.MAX_NUM_TEXT_ROWS && iteration < allTextRows.size(); iteration++, row++) {
            g.drawString(allTextRows.get(row), margin, Constants.FONT_SIZE * (iteration + 2));
        }
        if (textLen(g, allTextRows.get(row - 1)) > Constants.SCREEN_WIDTH - 2 * Constants.DISPLAY_MARGIN) {
            f.newRow();
        }
    }

    public static int textLen(Graphics2D g, String s) {
        return g.getFontMetrics().stringWidth(s);
    }

    private void paintBackground(Graphics2D g) {
        g.setColor(Color.black);
        g.fillRect(0, 0, Constants.SCREEN_WIDTH, Constants.SCREEN_HEIGHT);
    }

    private void paintTime(Graphics2D g) {
        g.setColor(Constants.COLOR);
        int timeFontSize = Constants.FONT_SIZE /2;
        g.setFont(new Font(("Helvetica"), Font.PLAIN, timeFontSize));
        g.drawString(String.valueOf(f.timeLeftToWin()/1000), Constants.SCREEN_WIDTH - (int)(timeFontSize*2.5), Constants.SCREEN_HEIGHT - timeFontSize);
    }
}