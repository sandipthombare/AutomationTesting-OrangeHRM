package com.example.utils;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.Duration;

public class Utils {
    public static void doScroll(WebDriver driver, int heightPixel){
        JavascriptExecutor executor = (JavascriptExecutor) driver;

        executor.executeScript("window.scrollBy(0,"+heightPixel+")");
    }
    public static JSONObject loadJSONFile(String filePath)throws IOException,ParseException{
      
     JSONParser parser = new JSONParser();
     Object obj = parser.parse(new FileReader(filePath));

     return (JSONObject) obj;
    }

    public static JSONObject loadJSONFileContainingArray(String filePath, int index)throws IOException,ParseException{

        try(FileReader reader = new FileReader(filePath)){
            JSONParser parser = new JSONParser();

            Object obj = parser.parse(reader);
            JSONArray jsonArray = (JSONArray) obj;

            JSONObject jsonObject =(JSONObject) jsonArray.get(index);

            return jsonObject;
        }
    }

  

    public static void waitForElement(WebDriver driver,WebElement element ,int TIME_UNIT_SECONDS){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(TIME_UNIT_SECONDS));
        wait.until(ExpectedConditions.visibilityOf(element));
    }

    public static int generateRandomNumber(int min,int max){

        return (int) Math.round(Math.random()*(max-min)+min);
    }

    @SuppressWarnings("unchecked")
    public static void addJsonArray(String firstName,String lastName,String employeeId,String username,String password)throws IOException,ParseException{
        
        String filePath = "demo\\src\\main\\java\\com\\example\\resources\\Employee.json";

        try(FileReader reader = new FileReader(filePath)){
        JSONParser parser = new JSONParser();

        Object obj = parser.parse(reader);

        JSONObject userObj = new JSONObject();
        JSONArray jsonArray = (JSONArray) obj;

        userObj.put("firstname",firstName);
        userObj.put("lastname",lastName);
        userObj.put("username", username);
        userObj.put("password", password);
        userObj.put("employeeId", employeeId);
        jsonArray.add(userObj);
            
        FileWriter file = new FileWriter(filePath);
        file.write(jsonArray.toJSONString());
        file.flush();
        file.close();
    
        }
    }

    @SuppressWarnings("unchecked")
    public static void updateJSONObject (String filePath,String key,String value,int index)throws IOException,ParseException{
        
        
        try(FileReader reader = new FileReader(filePath);){
            JSONParser parser = new JSONParser();

            Object object = parser.parse(reader);

            JSONArray jsonArray = (JSONArray) object;
            JSONObject jsonObject = (JSONObject) jsonArray.get(index);

            jsonObject.put(key,value);

            FileWriter file = new FileWriter(filePath);
            file.write(jsonArray.toJSONString());
            file.flush();
            file.close();
        }

    }
}
   