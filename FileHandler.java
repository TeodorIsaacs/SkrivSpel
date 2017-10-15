import javax.swing.*;
import java.io.*;
import java.util.ArrayList;
import java.util.Random;

/**
 * Created by Teodor Isaacs on 2016-03-23.
 */
public class FileHandler {

    public static ArrayList<String> getPhraseList(String nameOfFile) throws UnsupportedEncodingException {
        FileReader fileReader = null;
        BufferedReader bufferedReader = null;
        ArrayList<String> fileStrings = new ArrayList<>();
        if (!new File(nameOfFile).exists()) {
            try {
                new File(nameOfFile).createNewFile();
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        }
        String line;
        try {
            bufferedReader = new BufferedReader(new InputStreamReader(
                    new FileInputStream(nameOfFile), "UTF-8"));
            try {
                while ((line = bufferedReader.readLine()) != null) {
                    fileStrings.add(line + " ");
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } finally {
            try {
                if (bufferedReader != null)
                    bufferedReader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return shuffleList(fileStrings);
    }

    public static boolean completedGame(ArrayList<String> allWordsList) {
        FileOutputStream fileWriter = null;
        BufferedWriter bufferedWriter = null;
        int index = 0;
        String name = JOptionPane.showInputDialog("Please enter your full name:");
        String filename = name + "_ResultFile";
        try {
            bufferedWriter = new BufferedWriter(new OutputStreamWriter(
                    new FileOutputStream(filename), "UTF-8"));
            for (; index < allWordsList.size() - 1; index++) {
                bufferedWriter.write(allWordsList.get(index));
                bufferedWriter.newLine();
            }
            bufferedWriter.write(allWordsList.get(index));

        } catch (IOException e) {
            try {
                new File(filename).createNewFile();
                completedGame(allWordsList);
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        } finally {
            try {
                if (bufferedWriter != null)
                    bufferedWriter.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return true;
    }

    public static String parseConstants(int index) {
        FileReader fileReader = null;
        BufferedReader bufferedReader = null;
        ArrayList<String> fileStrings = new ArrayList<>();
        if (!new File("variables.dat").exists()) {
            try {
                new File("variables.dat").createNewFile();
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        }
        String line;
        try {
            bufferedReader = new BufferedReader(new InputStreamReader(
                    new FileInputStream("variables.dat"), "UTF-8"));
            try {
                int i = 1;
                while ((line = bufferedReader.readLine()) != null) {
                    if (i % 2 == 0) {
                        fileStrings.add(line);
                    }
                    i++;
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } finally {
            try {
                if (bufferedReader != null)
                    bufferedReader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        String word = fileStrings.get(index);
        return word;
    }

    private static ArrayList<String> shuffleList(ArrayList<String> strings) {
        Random random = new Random();
        ArrayList<String> shuffList = new ArrayList<String>();
        ArrayList<String> tempList = new ArrayList<String>(strings);
        int size = strings.size();

        while (shuffList.size() < size) {
            int r = random.nextInt(tempList.size());
            shuffList.add(tempList.get(r));
            tempList.remove(r);
        }
        return shuffList;
    }
}

