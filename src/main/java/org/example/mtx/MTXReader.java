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

        try {
            File file = new File(filePath);
            Scanner scanner = new Scanner(file);

            while (scanner.hasNextLine()) {
                String line = scanner.nextLine().trim();

                if (line.startsWith("%")) {
                    continue;
                }

                if (numRows == -1 || numCols == -1) {
                    String[] sizeInfo = line.split("\\s+");
                    numRows = Integer.parseInt(sizeInfo[0]);
                    numCols = Integer.parseInt(sizeInfo[1]);
                } else {
                    String[] elements = line.split("\\s+");
                    int i = Integer.parseInt(elements[0]) - 1;
                    int j = Integer.parseInt(elements[1]) - 1;
                    double value = Double.parseDouble(elements[2]);
                    cooElements.add(new Coordinate(i, j, value));
                }
            }

            scanner.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        return new COOMatrix(numRows, numCols, cooElements);
    }
}
