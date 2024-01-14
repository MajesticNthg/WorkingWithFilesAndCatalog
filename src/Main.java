import java.io.*;
import java.nio.file.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import java.io.FilenameFilter;
import java.io.File;
import java.io.IOException;


public class Main {
    static String exFile(String s) throws Exception { // функция определения расширения файла
        String[] fileName = s.split("\\.");
        String lastWord = fileName[fileName.length - 1];
        return "." + lastWord;
    }

    // Задание 4.2
    public static ArrayList<ArrayList<File>> listTwoList(String path, String exFile, boolean flag) {
        ArrayList<ArrayList<File>> res = new ArrayList<ArrayList<File>>();
        ArrayList<File> fileEx = new ArrayList<File>(); // файлы с заданным расширением
        ArrayList<File> catalog = new ArrayList<File>(); // каталоги
        res.add(fileEx);
        res.add(catalog);
        ArrayList<File> expand = new ArrayList<File>();
        File root = new File(path);
        expand.addAll(Arrays.asList(root.listFiles()));

        int z = 0; // количество уровней вложенности
        if (flag)
            z = 3;
        for(int depth = 0; depth < z; depth++) {
            File[] expandCopy = expand.toArray(new File[expand.size()]);
            expand.clear();
            for (File file : expandCopy) {
                if (file.isDirectory()) {
                    expand.addAll(Arrays.asList(file.listFiles()));
                    if (!catalog.contains(file))
                        catalog.add(file);
                } else {
                    String x = file.getName();
                    if (x.endsWith(exFile)) {
                        fileEx.add(file);
                    }

                }
            }
        }

        return res;
    }

    // Задание 4.3
    public static boolean delCatalog (String path) {
        ArrayList<ArrayList<File>> r = listTwoList(path, ".txt", true);

        if(r.get(1).size() > 0) return false;
        for(int i=0; i<r.get(0).size(); i++)
            if(! r.get(0).get(i).delete()) return false;
        File del = new File(path);
        if(! del.delete()) return false;
        return true;
    }


    public static void main(String[] args) throws Exception {
        // Задание 4.1
        File root = new File("."); // связка с файлом ".", где точка - любой символ
        ArrayList<File> expand = new ArrayList<File>(); // создан массив expand, содержащий объекты типа file
        expand.add(root); // добавление объекта root (типа file) в массив

        for(int depth = 0; depth < 10; depth++) { // цикл с переменной depth, которая отвечает за глубину вложенности
            File[] expandCopy = expand.toArray(new File[expand.size()]); // конвертация в обычный массив expandCopy
            expand.clear(); // очистка массива
            for (File file : expandCopy) { // переменной file присваивается массив expandCopy
                System.out.println(depth + " " + file); // выводится "глубина вложенности" и массив в виде списка файлов
                if (file.isDirectory()) { // проверка, представляет ли file данный каталог
                    expand.addAll(Arrays.asList(file.listFiles())); // в очищенный массив expand добавляется новая коллекция (содержимое file)
                }
            }
        }



        File my_fil1 = new File("F:\\Программирование\\Обучение Java\\2 курс (с нуля)\\15 Работа с файлами и каталогами\\sss.txt");
        BufferedWriter bw1 = new BufferedWriter(new FileWriter(my_fil1));
        File my_fil2 = new File("F:\\Программирование\\Обучение Java\\2 курс (с нуля)\\15 Работа с файлами и каталогами\\111.txt");
        BufferedWriter bw2 = new BufferedWriter(new FileWriter(my_fil2));
        File my_fil3 = new File("F:\\Программирование\\Обучение Java\\2 курс (с нуля)\\15 Работа с файлами и каталогами\\222.txt");
        BufferedWriter bw3 = new BufferedWriter(new FileWriter(my_fil3));
        File my_fil4 = new File("F:\\Программирование\\Обучение Java\\2 курс (с нуля)\\15 Работа с файлами и каталогами\\111.jpg");
        BufferedWriter bw4 = new BufferedWriter(new FileWriter(my_fil4));
        File new_dir = new File("F:\\Программирование\\Обучение Java\\2 курс (с нуля)\\15 Работа с файлами и каталогами\\my_test_dir");
        new_dir.mkdir();
        File my_fil5 = new File("F:\\Программирование\\Обучение Java\\2 курс (с нуля)\\15 Работа с файлами и каталогами\\my_test_dir\\777.txt");
        BufferedWriter bw5 = new BufferedWriter(new FileWriter(my_fil5));

        listTwoList("F:\\Программирование\\Обучение Java\\2 курс (с нуля)\\15 Работа с файлами и каталогами", ".txt", true);


        File new_testDir = new File("F:\\Программирование\\Обучение Java\\2 курс (с нуля)\\15 Работа с файлами и каталогами\\test_catalog_del");
        new_testDir.mkdir();

        String delFile = "F:\\Программирование\\Обучение Java\\2 курс (с нуля)\\15 Работа с файлами и каталогами\\test_catalog_del";
        delCatalog(delFile);
    }
}