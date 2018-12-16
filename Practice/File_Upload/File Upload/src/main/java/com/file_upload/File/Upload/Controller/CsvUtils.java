package com.file_upload.File.Upload.Controller;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.apache.log4j.Logger;

import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectReader;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;

public class CsvUtils {
	private static final Logger log = Logger.getLogger(CsvUtils.class);
    public static <T> List<T> read(Class<T> clazz, InputStream stream) throws IOException {
    	
    	log.info("Log Info CsvUtils.read => Mapping is starting");
    	
    	CsvMapper mapper = new CsvMapper();
    	ObjectReader reader = null;
    	//need to test this case
    	mapper.configure(MapperFeature.ACCEPT_CASE_INSENSITIVE_PROPERTIES, true);
		try {
			log.info("Log Info CsvUtils.read => Mapping Configuration is done");
			CsvSchema schema =  mapper.schemaFor(clazz).withHeader().withColumnReordering(true);
	        reader = mapper.readerFor(clazz).with(schema);
	        log.info("Log Info CsvUtils.read => Mapping is done and retruning mapped list obj");
	        return reader.<T>readValues(stream).readAll();
		} catch (Exception e) {
			System.out.println("Error in CsvUtils.read " + e);
			log.info("Log Error CsvUtils.read => Error while mapping the fields " + e);
			return null;
		}    	
    }
    
  
}