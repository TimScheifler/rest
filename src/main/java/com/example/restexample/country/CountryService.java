package com.example.restexample.country;

import com.opencsv.CSVReader;
import org.springframework.stereotype.Service;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

@Service
public class CountryService implements ICountryService{

    private final ArrayList<Country> countries;

    public CountryService(){
        countries = new ArrayList<>();
    }

    @Override
    public ArrayList<Country> findAll() {

        FileInputStream fileInputStream = null;

        try{
            String fileName = "src/main/resources/largeFile.csv";
            fileInputStream = new FileInputStream(fileName);
            CSVReader reader = new CSVReader(new InputStreamReader(fileInputStream));
            String[] nextLine;

            while((nextLine = reader.readNext()) != null){
                Country newCountry = new Country(
                        nextLine[0],
                        nextLine[1],
                        nextLine[2],
                        nextLine[3],
                        nextLine[4],
                        nextLine[5],
                        nextLine[6]
                );
                countries.add(newCountry);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return countries;
    }
}
