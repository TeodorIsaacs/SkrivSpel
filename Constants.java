import java.awt.*;

/**
 * Created by Teodor Isaacs on 2016-04-02.
 */
public class Constants {

    //The time allowed between finished words, in milliseconds
    //Brukade vara 6000
    public static final long TYPE_TIMER = Long.parseLong(Words.parseConstants(0));

    public static final int TIME_GAINED_WHEN_LETTER_TYPED = (int) TYPE_TIMER / 10;

    public static final String[] NAMES_OF_PHRASE_LIBRARIES = {Words.parseConstants(1), Words.parseConstants(2), Words.parseConstants(3)};

    public static final String[] NAMES_OF_PHRASE_LIBRARIES_DAT =
            {NAMES_OF_PHRASE_LIBRARIES[0] + ".dat", NAMES_OF_PHRASE_LIBRARIES[1] + ".dat", NAMES_OF_PHRASE_LIBRARIES[2] + ".dat"};

    public static final int SENTENCES_BETWEEN_PHRASES = Integer.parseInt(Words.parseConstants(4));

    public static final int SCREEN_WIDTH = Toolkit.getDefaultToolkit().getScreenSize().width;

    public static final int SCREEN_HEIGHT = Toolkit.getDefaultToolkit().getScreenSize().height;

    public static final int COMPLETED_TIME = Integer.parseInt(Words.parseConstants(5));

    public static final int DISPLAY_MARGIN = SCREEN_WIDTH / 20;

    public static final int TEXT_FONT_SIZE = SCREEN_HEIGHT / 20;

    public static final int TEXT_ROW_LENGHT = (SCREEN_WIDTH - 2 * DISPLAY_MARGIN) / (5 * TEXT_FONT_SIZE / 7);

    public static final Color COLOR = Color.green;

    public static final int MAX_NUM_TEXT_ROWS = (int) (SCREEN_HEIGHT / TEXT_FONT_SIZE * 0.9);

    public static final Font FONT = Font.getFont("Helvetica");
}