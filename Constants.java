import java.awt.*;

/**
 * Created by Teodor Isaacs on 2016-04-02.
 */
public class Constants {

    //The time allowed between finished words, in milliseconds
    //Brukade vara 6000
    public static final long TYPE_TIMER = Long.parseLong(FileHandler.parseConstants(0));

    public static final int TIME_GAINED_WHEN_LETTER_TYPED = (int) TYPE_TIMER / 10;

    public static final String[] NAMES_OF_PHRASE_LIBRARIES = {FileHandler.parseConstants(1), FileHandler.parseConstants(2), FileHandler.parseConstants(3)};

    public static final String[] NAMES_OF_PHRASE_LIBRARIES_DAT =
            {NAMES_OF_PHRASE_LIBRARIES[0] + ".dat", NAMES_OF_PHRASE_LIBRARIES[1] + ".dat", NAMES_OF_PHRASE_LIBRARIES[2] + ".dat"};

    public static final int SENTENCES_BETWEEN_PHRASES = Integer.parseInt(FileHandler.parseConstants(4));

    public static final int SCREEN_WIDTH = Toolkit.getDefaultToolkit().getScreenSize().width;

    public static final int SCREEN_HEIGHT = Toolkit.getDefaultToolkit().getScreenSize().height;

    public static final int COMPLETED_TIME = Integer.parseInt(FileHandler.parseConstants(5));

    public static final int DISPLAY_MARGIN = SCREEN_WIDTH / 20;

    public static final int FONT_SIZE = SCREEN_HEIGHT / 20;

    public static final int TEXT_ROW_LENGHT = (SCREEN_WIDTH - 2 * DISPLAY_MARGIN) / (5 * FONT_SIZE / 7);

    public static final Color COLOR = Color.green;

    public static final int MAX_NUM_TEXT_ROWS = (int) (SCREEN_HEIGHT / FONT_SIZE * 0.9);

    public static final Font FONT = Font.getFont("Helvetica");
}