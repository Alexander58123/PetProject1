package com.javarush.task.PetProject.Viselica01;

import java.io.*;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Solution {

    /*
    - нужен файл со словами в им. падеже
    - из файла добавляем в List слова
    - при отображении будет маска ******, когда угадываешь отображаются буквы ***а***а
    - счетчик ошибок
    - когда ошибка определенная, то рисуется виселица и другие части с человеком

    - обработка когда отгадали слово - успех и на новый переход игры
    - при проигрышке, заново предлагается игра
     */

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
                Slovar slovar = new Slovar(new File("E:\\slova.txt"));

                String slovoIgru = slovar.getUnknown(); // слово текущего раунда
                StringBuilder slovoIgruMask = slovar.getUnknownMask();

                System.out.println("Слово из " + slovar.getUnknownMask().length() + " букв");
                System.out.println(slovar.getUnknownMask().toString());

                while (Error.countError > 0) {
                    boolean doneChislo = false;
                    Error.sovpalo = false;
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
                            Error.sovpalo = true;
                        }
                    }
                    // если совпадение буквы
                    if (Error.sovpalo) {
                        System.out.println(slovoIgruMask);

                        // если текущее = слову-маске (успех)
                        if (slovoIgru.equals(slovoIgruMask.toString())) {
                            System.out.println("Молодец!!!! Отгаданное слово: " + slovoIgru);
                            System.out.println("-----------------");
                            break;
                        }

                    } else { // ошибка и уменьшаем кол-во попыток
                        System.err.println("Ошибка! Осталось попыток:" + (--Error.countError));
                        // не выводилось после проигрыша слово-маска
                        if (Error.countError == 0) {
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
