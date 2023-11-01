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
}