package com.unlimint.program.configuration;

import com.unlimint.program.service.impl.CSVFileReader;
import com.unlimint.program.service.impl.JSONFileReader;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

@Configuration
public class Config {

    @Bean(name="csvFileParser")
    @Scope(BeanDefinition.SCOPE_PROTOTYPE)
    public CSVFileReader csvFileReader(String fileName) {
        return new CSVFileReader(fileName);
    }

    @Bean(name="jsonFileService")
    @Scope(BeanDefinition.SCOPE_PROTOTYPE)
    public JSONFileReader jsonFileReader(String fileName, String[] fields) {
        return new JSONFileReader(fileName, fields);
    }
}
