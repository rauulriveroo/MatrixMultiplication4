package org.example.mtx;

import org.example.matrices.COOMatrix;
import org.example.matrices.Coordinate;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class MTXWriter {

    public void writeToMTXFile(COOMatrix matrix, String filePath) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            for (Coordinate coordinate : matrix.getCoordinates()) {
                writer.write((coordinate.i() +1) + " " + (coordinate.j()+1) + " " + coordinate.value());
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}