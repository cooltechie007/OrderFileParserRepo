package com.unlimint.program.service;

import com.unlimint.program.model.FileStructure;

import java.util.List;

public interface GenericFileReader extends Runnable {
    FileStructure parseOrder(Object order, int line, String fileName);

    default void printData(List<FileStructure> fileRecords) {
        for (FileStructure record : fileRecords) {
            System.out.println(record);
        }
    }
}
