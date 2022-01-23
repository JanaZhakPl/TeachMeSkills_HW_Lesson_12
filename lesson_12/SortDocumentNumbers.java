package com.teachmeskills.lesson_12;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;
//C:\Users\wikto\OneDrive\Рабочий стол\information\validOutput.txt
//C:\Users\wikto\OneDrive\Рабочий стол\information\invalidOutput.txt
public class SortDocumentNumbers {

    public static void main(String[] args) throws IOException {
        Scanner s = new Scanner(System.in);
        ArrayList<String> pathList = new ArrayList<>();
        System.out.println("Insert a File Path, enter 0 to finish");
        String inputPath = s.nextLine();

        do {
            pathList.add(inputPath);
            inputPath = s.nextLine();
        } while (!inputPath.equals("0"));

        Set<String> allDoc = new HashSet<>();

        for (String eachPathLine : pathList) {
            ArrayList<String> lines = new ArrayList<>(Files.readAllLines(Paths.get(eachPathLine)));
            allDoc.addAll(lines);
        }

        System.out.println(allDoc);

        HashMap<String, String> mapDocNum = new HashMap<>();

        for (String key : allDoc)
            if (key.length() == 15 && (key.toString().startsWith("contract") || key.toString().startsWith("docnum"))) {
                mapDocNum.put(key, " - VALID");

            } else if (key.length() != 15) {
                mapDocNum.put(key, " - INVALID: the number must be 15 characters long");

            } else if (!(key.toString().startsWith("docnum") || key.toString().startsWith("contract"))) {
                mapDocNum.put(key, " - INVALID:The number must start with \"contract\" or \"docnum\"");

            } else {
                mapDocNum.put(key, "INVALID");
            }

        Files.write(Paths.get("1.txt"),
        mapDocNum.entrySet().stream()
                        .map(k->k.getKey() + "\r\n" + k.getValue())
                        .collect(Collectors.toList()));

        Files.lines(Paths.get("1.txt")).forEach(System.out::println);
    }
}


