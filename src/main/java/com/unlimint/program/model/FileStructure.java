package com.unlimint.program.model;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Setter;

import java.util.LinkedHashMap;
import java.util.Map;

@Setter
public class FileStructure {
    private Long orderId;
    private double amount;
    private String currency;
    private String comment;
    private String parseResult;
    private String fileName;
    private int line;

    public String toString() {
        // {“id”:1,“orderId”:1,”amount”:100,”comment”:”order
        //payment”,”filename”:”orders.csv”,”line”:1,”result”:”OK”}
        Map<String,Object> fileStructure = new LinkedHashMap<>();
        fileStructure.put("id",orderId);
        fileStructure.put("amount",amount);
        fileStructure.put("currency",currency);
        fileStructure.put("comment",comment);
        fileStructure.put("fileName",fileName);
        fileStructure.put("line",line);
        fileStructure.put("parseResult",parseResult);
        ObjectMapper mapper = new ObjectMapper();
        String json = null;
        try {
            json = mapper.writeValueAsString(fileStructure);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        return json;
    }
}
