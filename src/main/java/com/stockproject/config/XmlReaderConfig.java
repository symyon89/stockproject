package com.stockproject.config;


import com.stockproject.dto.ProduseListDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
@Slf4j
@Configuration
@ConfigurationProperties(prefix = "path")
public class XmlReaderConfig {
    @Value("${path.source}")
    private String source;
    @Value("${path.target}")
    private String target;
    @Bean
        public ProduseListDto unmarshall() throws JAXBException, IOException {
        JAXBContext context = null;
        try {
            context = JAXBContext.newInstance(ProduseListDto.class);
        } catch (JAXBException e) {
            log.error("Context load failed");
            e.printStackTrace();
        }
        FileReader file = null;
        try {
            file = new FileReader(source);
        } catch (FileNotFoundException e) {
            log.error("File not found file path :" + source);
            e.printStackTrace();
        }
        ProduseListDto list = (ProduseListDto) context.createUnmarshaller()
                .unmarshal(file);
        Files.createDirectories(Paths.get(target));

        Files.move(Paths.get(source),
                Paths.get(target), StandardCopyOption.REPLACE_EXISTING);
        return list;
    }
}
