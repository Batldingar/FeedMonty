package com.baldware.feedmonty;

import android.content.Context;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class HistoryHandler {

    public enum Category {
        GAME_STATE,
        SETTINGS
    }

    private final File file;

    public HistoryHandler(Context context, String fileName) {
        file = new File(context.getFilesDir(), fileName);
    }

    public void writeHistory(String key, String value, Category category) {
        // Delete the entry if it already exists
        if(getEntry(key, category) != null) {
            deleteHistory(key, category);
        }

        // Recreate the entry with the new value
        String message = null;
        FileWriter fileWriter = null;
        BufferedWriter bufferedWriter;

        // Generate the message
        if(category == Category.GAME_STATE) {
            message = "GS," + key + "," + value;
        } else if(category == Category.SETTINGS) {
            message = "SG," + key + "," + value;
        }

        // Initialize the fileWriter
        try {
            fileWriter = new FileWriter(file, true);
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Initialize the bufferedWriter on top of the fileWriter
        bufferedWriter = new BufferedWriter(fileWriter);

        try {
            bufferedWriter.write(message);
            bufferedWriter.newLine();
            bufferedWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<String> readHistory(Category category) {
        FileReader fileReader = null;
        BufferedReader bufferedReader;
        ArrayList<String> resultStrings = new ArrayList<>();

        if(file.exists()) {
            // Initialize the fileReader
            try {
                fileReader = new FileReader(file);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }

            if (fileReader != null) {
                // Initialize the bufferedReader on top of the fileReader
                bufferedReader = new BufferedReader(fileReader);

                try {
                    String line = bufferedReader.readLine();
                    while (line != null) {
                        switch(category) {
                            case GAME_STATE:
                                if(line.startsWith("GS")) {
                                    resultStrings.add(line);
                                }
                                break;
                            case SETTINGS:
                                if(line.startsWith("SG")) {
                                    resultStrings.add(line);
                                }
                                break;
                        }

                        line = bufferedReader.readLine();
                    }
                    bufferedReader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        return resultStrings;
    }

    // returns null if the entry can not be found
    private String getEntry(String key, Category category) {
        ArrayList<String> entryList = readHistory(category);
        String value = null;

        if(!entryList.isEmpty()) {
            for(String entry : entryList) {
                String[] splitEntry = entry.split(",");
                if(splitEntry[1].equals(key)) {
                    value = splitEntry[2];
                    break;
                }
            }
        }

        return value;
    }

    public int getEntryInt(String key, Category category, int defaultValue) {
        int value = defaultValue;
        if(getEntry(key, category) != null) {
            value = Integer.parseInt(getEntry(key, category));
        }
        return value;
    }

    public float getEntryFloat(String key, Category category, float defaultValue) {
        float value = defaultValue;
        if(getEntry(key, category) != null) {
            value = Float.parseFloat(getEntry(key, category));
        }
        return value;
    }

    public String getEntryString(String key, Category category, String defaultValue) {
        String value = defaultValue;
        if(getEntry(key, category) != null) {
            value = getEntry(key, category);
        }
        return value;
    }

    public boolean getEntryBoolean(String key, Category category, boolean defaultValue) {
        boolean value = defaultValue;
        if(getEntry(key, category) != null) {
            value = Boolean.parseBoolean(getEntry(key, category));
        }
        return value;
    }

    public boolean deleteHistory() {
        if(file.exists()) {
            return file.delete();
        }

        return false;
    }

    public void deleteHistory(String key, Category category) {
        FileReader fileReader = null;
        BufferedReader bufferedReader;
        ArrayList<String> remainingStrings = new ArrayList<>();

        // Store the remaining lines
        if(file.exists()) {
            // Initialize the fileReader
            try {
                fileReader = new FileReader(file);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }

            if (fileReader != null) {
                // Initialize the bufferedReader on top of the fileReader
                bufferedReader = new BufferedReader(fileReader);

                try {
                    String line = bufferedReader.readLine();
                    while (line != null) {
                        String[] splitLine = line.split(",");

                        switch (category) {
                            case GAME_STATE:
                                if(!splitLine[0].equals("GS") || !splitLine[1].equals(key)) {
                                    remainingStrings.add(line);
                                }
                                break;
                            case SETTINGS:
                                if(!splitLine[0].equals("SG") || !splitLine[1].equals(key)) {
                                    remainingStrings.add(line);
                                }
                                break;
                        }
                        line = bufferedReader.readLine();
                    }
                    bufferedReader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            // Delete the file
            deleteHistory();

            // Rewrite history
            for (String line : remainingStrings) {
                String[] splitLine = line.split(",");
                switch (splitLine[0]) {
                    case "GS":
                        writeHistory(splitLine[1], splitLine[2], Category.GAME_STATE);
                        break;
                    case "SG":
                        writeHistory(splitLine[1], splitLine[2], Category.SETTINGS);
                        break;
                }
            }
        }

        return;
    }

    public void deleteHistory(Category category) {
        FileReader fileReader = null;
        BufferedReader bufferedReader;
        ArrayList<String> remainingStrings = new ArrayList<>();

        // Store the remaining lines
        if(file.exists()) {
            // Initialize the fileReader
            try {
                fileReader = new FileReader(file);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }

            if (fileReader != null) {
                // Initialize the bufferedReader on top of the fileReader
                bufferedReader = new BufferedReader(fileReader);

                try {
                    String line = bufferedReader.readLine();
                    while (line != null) {
                        switch (category) {
                            case GAME_STATE:
                                if (!line.startsWith("GS")) {
                                    remainingStrings.add(line);
                                }
                                break;
                            case SETTINGS:
                                if (!line.startsWith("SG")) {
                                    remainingStrings.add(line);
                                }
                                break;
                        }

                        line = bufferedReader.readLine();
                    }
                    bufferedReader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            // Delete the file
            deleteHistory();

            // Rewrite history
            for (String line : remainingStrings) {
                String[] splitLine = line.split(",");
                switch (splitLine[0]) {
                    case "GS":
                        writeHistory(splitLine[1], splitLine[2], Category.GAME_STATE);
                        break;
                    case "SG":
                        writeHistory(splitLine[1], splitLine[2], Category.SETTINGS);
                        break;
                }
            }
        }

        return;
    }
}