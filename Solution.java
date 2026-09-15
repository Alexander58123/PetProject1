package com.javarush.task.PetProject.Viselica01;

import com.javarush.task.PetProject.Viselica01.Other.Dictionary;
import com.javarush.task.PetProject.Viselica01.Other.Error;
import com.javarush.task.PetProject.Viselica01.Other.Menu;

import java.io.*;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Solution {

    public static void main(String[] args) {
        boolean continieGame = true;
        boolean doneYesNo = false;

        while (continieGame) {
            Scanner scanner = new Scanner(System.in);
            // меню (старт)
            Menu.menuStart();
            String variantStart = scanner.nextLine();

            if (variantStart.equalsIgnoreCase("Да")) {
                System.out.println("Игра началась");
                System.out.println("-----------------");

                // генерируем из списка (словаря)
                Dictionary dictionary = new Dictionary(new File("resources/words.txt"));

                String slovoIgru = dictionary.getUnknownRandom(); // слово текущего раунда
                StringBuilder slovoIgruMask = dictionary.getUnknownMask();

                System.out.println("Слово из " + dictionary.getUnknownMask().length() + " букв");
                System.out.println(dictionary.getUnknownMask().toString());

                while (com.javarush.task.PetProject.Viselica01.Other.Error.countError > 0) {
                    boolean doneChislo = false;
                    com.javarush.task.PetProject.Viselica01.Other.Error.matches = false;
                    char simvol = '\n';

                    System.out.println("Введите одну букву для отгадывания");
                    Scanner scannerBukva = new Scanner(System.in);

                    while (!doneChislo) {
                        try {
                            simvol = scannerBukva.next(".").charAt(0); // берет строго одну букву
                            doneChislo = true;
                        } catch (InputMismatchException inputMismatchException) {
                            System.out.println("Вводите именно букву");
                            scannerBukva.nextLine(); // очищаем неверный текущий scanner
                        }

                    }

                    for (int i = 0; i < slovoIgru.length(); i++) {
                        if (slovoIgru.charAt(i) == simvol) { // если есть совпадение
                            slovoIgruMask.setCharAt(i, simvol);
                            com.javarush.task.PetProject.Viselica01.Other.Error.matches = true;
                        }
                    }
                    // если совпадение буквы
                    if (com.javarush.task.PetProject.Viselica01.Other.Error.matches) {
                        System.out.println(slovoIgruMask);

                        // если текущее = слову-маске (успех)
                        if (slovoIgru.equals(slovoIgruMask.toString())) {
                            System.out.println("Молодец!!!! Отгаданное слово: " + slovoIgru);
                            System.out.println("-----------------");
                            break;
                        }

                    } else { // ошибка и уменьшаем кол-во попыток
                        System.err.println("Ошибка! Осталось попыток:" + (--com.javarush.task.PetProject.Viselica01.Other.Error.countError));
                        // не выводилось после проигрыша слово-маска
                        if (com.javarush.task.PetProject.Viselica01.Other.Error.countError == 0) {
                            System.err.println("Уровень закончен!");
                        }

                        // Error.countError++;
                    }
                }

                // когда исчерпаны попытки
                Error.countError = 3;

                // когда (Да/нет) из главного
            } else {
                System.err.println("Игра закончена!!!");
                continieGame = false;
            }
        }

    }
}
