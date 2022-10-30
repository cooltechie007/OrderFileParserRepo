package com.unlimint.program.service.impl;

import com.unlimint.program.model.FileStructure;
import com.unlimint.program.service.GenericFileReader;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class JSONFileReader implements GenericFileReader {

    private final String[] fields;
    private final String fileName;
    public JSONFileReader(String fileName, String[] fields) {
        this.fields = fields;
        this.fileName = fileName;
    }

    public FileStructure parseOrder(Object order, int line, String fileName) {
        FileStructure fileStructure = new FileStructure();
        try {
            JsonNode orderJson = (JsonNode) order ;
            fileStructure.setFileName(fileName);
            fileStructure.setLine(line);

            fileStructure.setCurrency(orderJson.get(this.fields[2]).asText());
            fileStructure.setComment ( orderJson.get(this.fields[3]).asText());
            fileStructure.setOrderId (orderJson.get(this.fields[0]).asLong());
            fileStructure.setAmount( orderJson.get(this.fields[1]).asDouble());
            fileStructure.setParseResult("Ok");
        } catch (Exception e) {
            fileStructure.setParseResult(e.toString());
        }
        return fileStructure;
    }
    @Override
    public void run() {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            InputStream input = new FileInputStream(fileName);
            JsonNode masterJSON = objectMapper.readTree(input);
            List<FileStructure> fileRecords = new ArrayList<>();
            for (int i = 0; i < masterJSON.size(); i++) {
                fileRecords.add(this.parseOrder(masterJSON.get(i), i+1, fileName));
            }
            printData(fileRecords);
        } catch (IOException e) {
            System.out.println("No file Found or error while reading file");
        }
    }
}