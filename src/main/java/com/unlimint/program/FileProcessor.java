package com.unlimint.program;

import com.unlimint.program.configuration.Config;
import com.unlimint.program.service.GenericFileReader;
import com.unlimint.program.constant.Constant;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class FileProcessor implements CommandLineRunner {
    @Value("${input.file.json.columns.order}")
    private String json;

    public static void main(String[] args) {
        if (args.length == 0) {
            System.out.println("No input file found");
            return;
        }
        SpringApplication.run(FileProcessor.class, args);
    }

    @Override
    public void run(String... args) {
        List<GenericFileReader> fileReader = initialize(args);
        initiateProcessing(fileReader);
    }

    private List<GenericFileReader> initialize(String... args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(Config.class);
        List<GenericFileReader> fileReader = new ArrayList<>();
        for (String arg : args) {
            if (arg.contains(Constant.FILE_EXTENSION_JSON)) {
                fileReader.add((GenericFileReader) context.getBean("jsonFileService", arg, this.json.split(",")));
            } else if (arg.contains(Constant.FILE_EXTENSION_CSV)) {
                fileReader.add((GenericFileReader) context.getBean("csvFileParser", arg));
            }
        }
        return fileReader;
    }

    private void initiateProcessing(List<GenericFileReader> fileReader) {
        int argSize = fileReader.size();
        Thread[] myThreads = new Thread[argSize];
        for (int j = 0; j < argSize; j++) {
            myThreads[j] = new Thread(fileReader.get(j));
            myThreads[j].start();
        }
        for (int j = 0; j < argSize; j++) {
            try {
                myThreads[j].join();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
