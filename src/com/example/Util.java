package com.example;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOError;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by Dominik Gudic on 20.06.2017.
 */
public class Util {

    private static String basePath = "C:\\Users\\Dominik Gudic\\IdeaProjects\\AdventOfCode\\src\\com\\example\\sources\\";

    public static String loadFile(String path) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(basePath + path));
        StringBuilder sb = new StringBuilder();
        String line = "";
        do {
            sb.append(line);
            line = br.readLine();
        } while (line != null);
        br.close();
        return sb.toString();
    }

    public static List<String> loadFileIntoList(String path) throws IOException {
        List<String> strings = new LinkedList<>();
        BufferedReader br = new BufferedReader(new FileReader(basePath + path));
        String line = "";
        do {
            line = br.readLine();
            if (line != null)
                strings.add(line);
        } while (line != null);
        br.close();
        return strings;
    }
}
