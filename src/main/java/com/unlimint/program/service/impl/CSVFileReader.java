package com.unlimint.program.service.impl;

import com.unlimint.program.model.FileStructure;
import com.unlimint.program.service.GenericFileReader;
import com.opencsv.CSVReader;

import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class CSVFileReader implements GenericFileReader {
    private final String fileName;

    public CSVFileReader(String fileName) {
        this.fileName = fileName;
    }

    public FileStructure parseOrder(Object order, int line, String fileName) {

        FileStructure fileStructure = new FileStructure();
        try {
            String [] orderArray = (String []) order;

            fileStructure.setFileName(fileName);
            fileStructure.setLine(line);
            fileStructure.setCurrency(orderArray[2]);
            fileStructure.setComment (orderArray[3]);
            fileStructure.setOrderId (Long.parseLong(orderArray[0]));
            fileStructure.setAmount(Double.parseDouble(orderArray[1]));
            fileStructure.setParseResult("Ok");
        } catch (Exception e) {
            fileStructure.setParseResult(e.toString());

        }
        return fileStructure;
    }

    @Override
    public void run() {
        try {
            CSVReader reader = null;
            reader = new CSVReader(new FileReader(fileName));
            String[] nextLine;
            List<FileStructure> fileRecords = new ArrayList<>();
            int counter =1;
            while ((nextLine = reader.readNext()) != null) {
                fileRecords.add(this.parseOrder(nextLine, counter, fileName));
                counter +=1;
            }
            printData(fileRecords);
        }
        catch(Exception e) {
            e.printStackTrace();
        }
    }
}