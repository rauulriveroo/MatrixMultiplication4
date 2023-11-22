package org.example.mtx;

import org.example.matrices.Coordinate;
import org.example.matrices.COOMatrix;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class MTXReader {

    public COOMatrix readMTXFile(String filePath) {
        List<Coordinate> cooElements = new ArrayList<>();
        int numRows = -1;
        int numCols = -1;

        try (Scanner scanner = new Scanner(new File(filePath))) {

            while (scanner.hasNextLine()) {
                String line = scanner.nextLine().trim();

                if (line.isEmpty() || line.startsWith("%")) {
                    continue;
                }

                String[] elements = line.split("\\s+");
                if (elements.length < 2) {
                    continue;
                }

                if (numRows == -1 || numCols == -1) {
                    numRows = Integer.parseInt(elements[0]);
                    numCols = Integer.parseInt(elements[1]);
                } else if (elements.length >= 3) {
                    int i = Integer.parseInt(elements[0]) - 1;
                    int j = Integer.parseInt(elements[1]) - 1;
                    double value = Double.parseDouble(elements[2]);
                    cooElements.add(new Coordinate(i, j, value));
                }
            }

        } catch (FileNotFoundException e) {
            throw new RuntimeException("File not found: " + filePath, e);
        }

        return new COOMatrix(numRows, numCols, cooElements);
    }
}