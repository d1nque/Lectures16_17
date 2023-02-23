package ua.d1nque.mongodb.services;

import ua.d1nque.mongodb.model.PepEntity;
import ua.d1nque.mongodb.repository.PepEntityRepository;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;

import java.util.InvalidPropertiesFormatException;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

@Service
public class UnZipAndParseService {
    private final PepEntityRepository pepEntityRepository;
    private final ObjectMapper mapper = new ObjectMapper();
    private static final int BUFFER_SIZE = 1024;
    private static final String dataDirectoryPath = "src/main/resources/Data";

    @Autowired
    public UnZipAndParseService(PepEntityRepository pepEntityRepository){
        this.pepEntityRepository = pepEntityRepository;
    }

    public void parseUnZipJsonFilestodb(MultipartFile zip){
        pepEntityRepository.deleteAll();
        unZip(zip);
        File[] files = new File(dataDirectoryPath).listFiles();
        if(files != null){
            for(File file : files){
                readAndParseLargeJson(file);
            }
        }
        clearDataDirectory();
    }

    private void readAndParseLargeJson(File file){

        try(JsonParser jsonParser = mapper.getFactory().createParser(file)){
            if(jsonParser.nextToken() != JsonToken.START_ARRAY){
                throw new InvalidPropertiesFormatException("Must begin with arr");
            }
            while(jsonParser.nextToken() != JsonToken.END_ARRAY){
                pepEntityRepository.save(mapper.readValue(jsonParser, PepEntity.class));
            }
        }catch (Exception e){
            System.out.println(e.getMessage());
        }

    }

    private void unZip(final MultipartFile zip) {
        byte[] buffer = new byte[BUFFER_SIZE];

        try {
            final ZipInputStream zis = new ZipInputStream(zip.getInputStream());
            ZipEntry ze = zis.getNextEntry();
            String nextFileName;
            while (ze != null) {
                nextFileName = ze.getName();
                File nextFile = new File("src/main/resources/Data" + File.separator + nextFileName);
                try (FileOutputStream fos = new FileOutputStream(nextFile)) {
                    int length;
                    while ((length = zis.read(buffer)) > 0) {
                        fos.write(buffer, 0, length);
                    }
                }
                ze = zis.getNextEntry();
            }
            zis.closeEntry();
            zis.close();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    private void clearDataDirectory(){
        File[] files = new File(dataDirectoryPath).listFiles();
        if(files != null){
            for(File file : files){
                file.delete();
            }
        }
    }

}




