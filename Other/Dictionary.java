package com.javarush.task.PetProject.Viselica01.Other;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

// где будем хранить слова для отгадывания

public class Dictionary {

    private List<String> slova = new ArrayList<>();
    private String unknownRandom; // рандомное
    private StringBuilder unknownMask; // под *****

    // конструктор
    public Dictionary(File file) {
        BufferedReader bufferedReader = null;

        try {
            bufferedReader = new BufferedReader(new FileReader(file));
            while (bufferedReader.ready()) {
                slova.add(bufferedReader.readLine());
            }

        } catch (FileNotFoundException exception) {
            exception.fillInStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        initialUnknownRandom(); // инициализировали unknownRandom слово
        initialUnknownMask(); // инициир. unknownRandom c маской

    }

    public List<String> getSlova() {
        return slova;
    }

    public String getUnknownRandom() {
        return unknownRandom;
    }

    public StringBuilder getUnknownMask() {
        return unknownMask;
    }

    public void addElemment(String string) {
        slova.add(string);
    }

    private void initialUnknownRandom() {
        Random random = new Random();
        int randomInt = random.nextInt(slova.size());
        unknownRandom = slova.get(randomInt);
    }

    private void initialUnknownMask() {
        unknownMask = new StringBuilder();
        for (int i = 0; i < unknownRandom.length(); i++) {
            unknownMask = unknownMask.append("*");
        }
    }
}
