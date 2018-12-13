package com.crmit.file_upload.File.Upload.Controller;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectReader;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;

public class CsvUtils {
    public static <T> List<T> read(Class<T> clazz, InputStream stream) throws IOException {
    	CsvMapper mapper = new CsvMapper();
    	ObjectReader reader = null;
    	//need to test this case
    	mapper.configure(MapperFeature.ACCEPT_CASE_INSENSITIVE_PROPERTIES, true);
		try {
			CsvSchema schema =  mapper.schemaFor(clazz).withHeader().withColumnReordering(true);
	        reader = mapper.readerFor(clazz).with(schema);
	        return reader.<T>readValues(stream).readAll();
		} catch (Exception e) {
			System.out.println(e);
			return null;
		}    	
    }
    
  
}