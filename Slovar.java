package com.javarush.task.PetProject.Viselica01;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

// где будем хранить слова для отгадывания

public class Slovar {

    private List<String> slova = new ArrayList<>();
    private String unknown; // рандомное
    private StringBuilder unknownMask; // под *****

    // конструктор
    public Slovar(File file) {
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

        initialUnknown(); // инициализировали unknown слово
        initialUnknownMask(); // инициир. unknown c маской

    }

    public List<String> getSlova() {
        return slova;
    }

    public String getUnknown() {
        return unknown;
    }

    public StringBuilder getUnknownMask() {
        return unknownMask;
    }

    public void addElemment(String string) {
        slova.add(string);
    }

    private void initialUnknown() {
        Random random = new Random();
        int randomInt = random.nextInt(slova.size());
        unknown = slova.get(randomInt);
    }

    private void initialUnknownMask() {
        unknownMask = new StringBuilder();
        for (int i = 0; i < unknown.length(); i++) {
            unknownMask = unknownMask.append("*");
        }
    }
}
