package com.aconex.codingchallenge.vehiclesurvey.utils;

import com.aconex.codingchallenge.vehiclesurvey.constants.App;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

public class FileUtils {

    /*
     * read words from file in Resource folder - 1 word per line
     *
     * @param fileName name of file in resource folder
     *
     * @return stack of words in file
     */
    public List<String> readResourceFile(String fileName) {

        List<String> result = new ArrayList<>();
        List<String> emptyList = new ArrayList<>();

        URL url = getClass().getClassLoader().getResource(fileName);
        try {
            BufferedReader br = new BufferedReader(
                    new InputStreamReader(url.openStream()));
            String line;
            while ((line = br.readLine()) != null) {
                if (isValid(line)) {
                    result.add(line);
                }
                else {
                    System.out.println("Invalid input line: " + line);
                    return emptyList;
                }
            }
            br.close();

        } catch (IOException e) {
            System.out.println("Error reading file: " + fileName);
            return emptyList;
        }

        return result;
    }

    private boolean isValid(String line) {
        Pattern validator = Pattern.compile(App.VALID_REGEX_INPUT);

        return validator.matcher(line).matches();
    }
}
