package com.stockproject.config;


import com.stockproject.dto.ProduseListDto;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;


@Configuration
public class XmlReaderConfig {
    @Bean
        public ProduseListDto unmarshall() throws JAXBException, IOException {
            JAXBContext context = JAXBContext.newInstance(ProduseListDto.class);
        FileReader file = new FileReader("src/main/resources/STOCURI_NOI/stocuri_noi.xml");
        ProduseListDto list = (ProduseListDto) context.createUnmarshaller()
                .unmarshal(file);
        Files.createDirectories(Paths.get("src/main/resources/STOCURI_PROCESATE"));

        Files.move(Paths.get("src/main/resources/STOCURI_NOI/stocuri_noi.xml"),
                Paths.get("src/main/resources/STOCURI_PROCESATE/stocuri_noi.xml"), StandardCopyOption.REPLACE_EXISTING);

        return list;
    }
}
