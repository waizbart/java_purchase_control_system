package Managers;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class FileManager {
    public Vector<String[]> readRows(String path) {
        try (BufferedReader br = new BufferedReader(new FileReader(path))) {
            String linha;
            Vector<String[]> dados = new Vector<String[]>();

            while ((linha = br.readLine()) != null) {
                dados.add(linha.split(","));
            }

            return dados;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void writeRows(String path, Vector<String[]> rows) {
        try {
            String content = "";
            for (String[] row : rows) {
                for (String cell : row) {
                    content += cell + ",";
                }
                content += "\n";
            }
            java.io.File file = new java.io.File(path);
            java.io.FileWriter fw = new java.io.FileWriter(file);
            fw.write(content);
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}