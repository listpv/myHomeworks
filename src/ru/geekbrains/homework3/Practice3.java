package ru.geekbrains.homework3;

/*1. Написать программу, которая загадывает случайное число от 0 до 9, и пользователю дается 3 попытки угадать это число.
При каждой попытке компьютер должен сообщить больше ли указанное пользователем число чем загаданное, или меньше.
 После победы или проигрыша выводится запрос – «Повторить игру еще раз? 1 – да / 0 – нет»(1 – повторить, 0 – нет).
2 * Создать массив из слов String[] words = {"apple", "orange", "lemon", "banana", "apricot", "avocado", "broccoli",
 "carrot", "cherry", "garlic", "grape", "melon", "leak", "kiwi", "mango", "mushroom", "nut", "olive", "pea", "peanut",
  "pear", "pepper", "pineapple", "pumpkin", "potato"};
При запуске программы компьютер загадывает слово, запрашивает ответ у пользователя,
сравнивает его с загаданным словом и сообщает правильно ли ответил пользователь.
Если слово не угадано, компьютер показывает буквы которые стоят на своих местах.
apple – загаданное
apricot - ответ игрока
ap############# (15 символов, чтобы пользователь не мог узнать длину слова)
Для сравнения двух слов посимвольно, можно пользоваться:
String str = "apple";
str.charAt(0); - метод, вернет char, который стоит в слове str на первой позиции
Играем до тех пор, пока игрок не отгадает слово
Используем только маленькие буквы */


import org.jetbrains.annotations.NotNull;

import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class Practice3
{
    private static Scanner scan = new Scanner(System.in);
    public static void main(String[] args)
    {
        System.out.println("Задание 1");
        guessNumber(9, 3);        // добавил две переменные - от 0 до 'limit' , кол-во попыток ('attempt')
        System.out.println("Задание 2");
        System.out.println(Arrays.toString(makeArray()));   // вывод всего массива слов
        guessWord(makeArray());
        scan.close();
    }

    // Метод для Задания 1.
    static void guessNumber(int limit, int attempt)
    {
        int yesnot;
        do
            {
                findNumber(limit, attempt);
                System.out.println("Повторить игру ещё раз? 1 - повторить 0 - нет");
                yesnot = scan.nextInt();
            }
        while (yesnot == 1);
        return;
    }

    // Метод для Задания 1.
    static void findNumber( int limit, int attempt)
    {
        Random rd = new Random();
        int nrd = rd.nextInt(limit + 1);
        int var;
        for(int i = 0; i < attempt; i++)
        {
            do
                {
                    System.out.println("Введите число от 0 до " + limit +". Количество попыток " + (attempt - i));
                    var = scan.nextInt();
                }
            while (var < 0 || var > limit);
//            System.out.println("Введите число от 0 до " + limit +". Количество попыток " + attempt);
//            var = scan.nextInt();
            if (var > nrd)
            {
                System.out.println("Ваше число больше загаданного.");
            }
            else if(var < nrd)
            {
                System.out.println("Ваше число меньше загаданного.");
            }
            else if(var == nrd)
            {
                System.out.println("Вы угадали!");
                return;
            }
        }
        System.out.println("Вы не угадали. Загаданное число " + nrd);
        return;
    }

    // Метод для Задания 2.
    static String[] makeArray()
    {
        String[] mass = {"apple", "orange", "lemon", "banana", "apricot", "avocado", "broccoli",
                "carrot", "cherry", "garlic", "grape", "melon", "leak", "kiwi", "mango", "mushroom", "nut", "olive", "pea", "peanut",
                "pear", "pepper", "pineapple", "pumpkin", "potato"};
        return mass;
    }
//   String b = sc.nextLine();                        // чтение введенной строки
//    String c = sc.next();                               // слово до следующего пробела

    // Метод для Задания 2.
    static void guessWord(String @NotNull [] mass)
    {
        Random rand = new Random();
        int nrd = rand.nextInt(mass.length);
        boolean yn;
        do
            {
                yn = compareWords(mass, nrd);
            }
        while (yn == false);
    }

    // Метод для Задания 2.
    static boolean compareWords(String[] mass, int num)
    {
        System.out.println("Введите слово");
        String var =  scan.next();
        int lensum = mass[num].length();
        if(var.length() < lensum)
        {
            lensum = var.length();
        }
        if(var.equals(mass[num]))
        {
            System.out.println("Угадали");
            return true;
        }
        else
        {
            char[] chk;
            if(var.length() > mass[num].length())
            {
                chk = new char[var.length()];
                for(int i = 0; i < var.length(); i++)
                {
                    chk[i] = var.charAt(i);
                }
            }
            else
                {
                    chk = new char[mass[num].length()];
                    for(int i =0; i < lensum; i++)
                    {
                        if(var.charAt(i) == mass[num].charAt(i))
                        {
                            chk[i] = var.charAt(i);
                        }
                        else
                        {
                            i = lensum - 1;
                        }
                    }
                }
            System.out.println("Не угадали");
            System.out.print(chk);
            if(chk.length < 15)
            {
                char[] symb = countSymbol(chk);
                System.out.print(symb);
            }
            System.out.println();
        }
        return false;
    }

    // Метод для Задания 2.
    static char[] countSymbol(char[] cntAr)       // возвращает недостающее кол-во '#'.
    {
        int j = 0;
        for(int i = 0; i < cntAr.length; i++)
        {
            if(cntAr[i] == 0)
            {
                i = cntAr.length - 1;
            }
            else
                {
                    j++;
                }
        }
        char[] cnt = new char[15 - j];
        for(int i = 0; i < (15 - j); i++)
        {
            cnt[i] = '#';
        }
        return cnt;
    }
}
/*    Для формирования случайного числа нужно создать объект класса Random и вызвать у него метод nextInt(n), который возвращает случайное целое число в пределах от 0 до n – 1 включительно. В примере ниже в x могут попасть числа 0, 1, 2, 3, ..., 19.

public class MainClass {
    public static void main(String[] args) {
        Random rand = new Random();
        int x = rand.nextInt(20);
    }
}*/
