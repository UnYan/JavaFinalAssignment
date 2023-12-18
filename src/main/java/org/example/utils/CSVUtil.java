package org.example.utils;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.example.entry.APIEntry;
import org.example.entry.MashupEntry;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.List;
import java.util.Properties;

public class CSVUtil {
    final static String properties_name = "csv.properties";
    final static String api_properties = "api_file_path";
    final static String mashup_properties = "mashup_file_path";

    private static boolean init = false;
    private static boolean init_api = false;
    private static boolean init_mashup = false;

    public static String get_path(String s){
        Properties properties = new Properties();
        String path = null;
        try {
            properties.load(CSVUtil.class.getClassLoader().getResourceAsStream(properties_name));
            path = properties.getProperty(s);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return path;
    }

    public static void init(){
        readApiCsv();
        readMashupCsv();
    }
    public static void readApiCsv(){
        if(init_api)
            return;
        String file_path = get_path(api_properties);
        try {
            Reader reader = new FileReader(file_path);

            CSVParser csvParser = CSVFormat.DEFAULT.parse(reader);

            boolean headerRead = false;
            for (CSVRecord csvRecord : csvParser) {
                if (!headerRead) {
                    String[] headers = csvRecord.iterator().next().split(",");
                    headerRead = true;
                    continue;
                }

                String idx = csvRecord.get(0).strip();
                String name = csvRecord.get(1).strip();
                String primaryCategory = csvRecord.get(3).strip();
                String secondaryCategories = csvRecord.get(5).strip();

                APIEntry apiEntry = new APIEntry(idx, name, primaryCategory, secondaryCategories);
//                System.out.println(apiEntry);
            }

            csvParser.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        APIEntry.size = APIEntry.apiList.size();
        init_api = true;
    }

    public static void readMashupCsv(){
        if(!init_api)
            readApiCsv();
        if(init_mashup)
            return;
        String file_path = get_path(mashup_properties);
        try {
            Reader reader = new FileReader(file_path);

            CSVParser csvParser = CSVFormat.DEFAULT.parse(reader);

            boolean headerRead = false;
            for (CSVRecord csvRecord : csvParser) {
                if (!headerRead) {
                    String[] headers = csvRecord.iterator().next().split(",");
                    headerRead = true;
                    continue;
                }

                String idx = csvRecord.get(0).strip();
                String name = csvRecord.get(1).strip();
                String relatedApis = csvRecord.get(2).strip();
                String categories = csvRecord.get(3).strip();

                MashupEntry mashupEntry = new MashupEntry(idx, name, relatedApis, categories);
//                System.out.println(mashupEntry);
            }

            csvParser.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        init_mashup = true;
    }
}
