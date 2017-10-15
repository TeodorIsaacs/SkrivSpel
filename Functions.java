import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

/**
 * Created by Teodor Isaacs on 2016-04-01.
 */
public class Functions {
    private Parser p;
    private Words w;
    public long startTime = System.currentTimeMillis();
    public long timeLastTyped = System.currentTimeMillis() + 2000;
    private int sentencesSinceLastPhrase = 0;
    private int phraseAt = 0;
    private ArrayList<String> phrases;
    public String[] allLibraries = Constants.NAMES_OF_PHRASE_LIBRARIES_DAT;
    public String activeLibrary = Constants.NAMES_OF_PHRASE_LIBRARIES_DAT[0];
    public int hoveredLibrary = 0;
    private ArrayList<String> allTextRows;
    private int atRow = 0;
    public static final String[] extraTimeChars = {" ", "?", "!", ",", "."};
    public static final String[] sentenceEndChars = {"?", "!", "."};

    public State state = State.ENDSCREEN;

    public enum State {WINSCREEN, SETUP, ENDSCREEN, PLAY, DEAD}

    public Functions() throws UnsupportedEncodingException {
        reset();
    }

    public void tick() throws UnsupportedEncodingException {
        if (timeLeftToWin() <= 0 && state == State.PLAY) {
            state = State.WINSCREEN;
            w.completedGame(allTextRows);
            state = State.ENDSCREEN;

        }
        if (timeLeftToDeath() < 0 && state == State.PLAY) {
            state = State.DEAD;
        }
    }

    public void stateTransition() {
        switch (state) {
            case SETUP:
                timeLastTyped = System.currentTimeMillis();
                startTime = System.currentTimeMillis();
                state = State.PLAY;
                break;
        }
    }

    public void reset() throws UnsupportedEncodingException {
        startTime = System.currentTimeMillis();
        timeLastTyped = System.currentTimeMillis() + 500;
        p = new Parser(this);
        w = new Words(this);
        phrases = w.getPhraseList(activeLibrary);
        phraseAt = 0;
        atRow = 0;
        allTextRows = new ArrayList<>();
        allTextRows.add("");
        addTextSimple(phrases.get(phraseAt));
        phraseAt++;
        switch (state) {
            case DEAD:
                state = State.PLAY;
                break;
            case ENDSCREEN:
                state = State.SETUP;
                break;
        }

    }

    private long timeLeftToDeath() {

        return Constants.TYPE_TIMER - (System.currentTimeMillis() - timeLastTyped);
    }

    public long playTime() {
        return System.currentTimeMillis() - startTime;
    }

    public ArrayList<String> getAllText() {
        return allTextRows;
    }

    public void addText(String s) throws UnsupportedEncodingException {

        allTextRows.set(atRow, allTextRows.get(atRow) + s);
        increaseTimeLeftToDeath(s);

        if (inArray(s, sentenceEndChars)) {
            String lastChar = allTextRows.get(atRow).substring(allTextRows.get(atRow).length() - 2, allTextRows.get(atRow).length() - 1);
            //Det här ser konstigt ut, men är för att inte strängen "..." ska ge nya fraser hela tiden
            if (!(inArray(s, sentenceEndChars))) {
                sentencesSinceLastPhrase++;
            }
            if (sentencesSinceLastPhrase >= Constants.SENTENCES_BETWEEN_PHRASES) {
                sentencesSinceLastPhrase = 0;
                addTextSimple(wrtiteNextPhrase());
            }
        }
    }

    public void newRow() {
        allTextRows.add("");
        String[] rowAsArray = allTextRows.get(atRow).split(" ");
        String lastWord;
        String rowAsString;
        if (rowAsArray.length == 1) {
            rowAsString = rowAsArray[0].substring(0, rowAsArray[0].length() - 2) + "-";
            lastWord = rowAsArray[0].substring(rowAsArray[0].length() - 2);
        } else {
            lastWord = rowAsArray[rowAsArray.length - 1];
            rowAsArray[rowAsArray.length - 1] = "";
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < rowAsArray.length; i++)
                sb.append(rowAsArray[i] + " ");
            rowAsString = sb.toString();
        }
        allTextRows.set(atRow, rowAsString);
        atRow++;
        try {
            addText(lastWord);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }

    private void addTextSimple(String s) {
        allTextRows.set(atRow, allTextRows.get(atRow) + s);
    }

    private String wrtiteNextPhrase() {
        String returning = phrases.get(phraseAt);
        phraseAt++;
        allTextRows.add("");
        allTextRows.add("");
        atRow += 2;
        return returning;
    }

    public long timeLeftToWin() {
        return Constants.COMPLETED_TIME - (System.currentTimeMillis() - startTime);
    }

    public void removeLastCharOfAllText() {
        if (allTextRows.get(atRow).length() == 0) {
            allTextRows.remove(atRow);
            atRow--;
        }
        removeLastCharCurrentRow();
    }

    private void removeLastCharCurrentRow() {
        allTextRows.set(atRow, allTextRows.get(atRow).substring(0, allTextRows.get(atRow).length() - 1));
    }

    public void increaseTimeLeftToDeath(String s) {
        int timeGain = Constants.TIME_GAINED_WHEN_LETTER_TYPED;
        if (inArray(s, extraTimeChars)) {
            timeGain *= 2;
        }
        if (timeLeftToDeath() + timeGain > Constants.TYPE_TIMER)
            timeLastTyped = System.currentTimeMillis();
        else
            timeLastTyped += timeGain;
    }

    public static boolean inArray(String s, String[] list) {
        for (String elem : list) {
            if (s.equals(elem))
                return true;
        }
        return false;
    }
}