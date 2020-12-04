package com.java.rollercoaster.service.impl;

import com.java.rollercoaster.errorenum.BusinessException;
import com.java.rollercoaster.errorenum.ErrorEnum;
import com.java.rollercoaster.response.CommonReturnType;
import com.java.rollercoaster.service.WeatherService;
import com.java.rollercoaster.service.model.WeatherModel;


import kong.unirest.json.JSONArray;
import kong.unirest.json.JSONObject;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;


@Service
public class WeatherServiceImpl implements WeatherService {

    private RestTemplate restTemplate = new RestTemplate();

    @Override
    public WeatherModel queryWeather(Date date) throws ParseException, BusinessException {

        String url = "https://api.openweathermap.org/data/2.5/onecall";
        String param = "lat=40.43&lon=-74&exclude=hourly,minutely,current"
                + "&appid=36ecbb1aa6c1f51110ee49fe989c46bf&units=imperial";

        String result = restTemplate.getForObject(url + "?" + param, String.class);

        JSONObject jsonObject = new JSONObject(result);
        JSONArray jsonArray = (JSONArray) jsonObject.get("daily");

        SimpleDateFormat sdfTest = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date wantedDateTest = sdfTest.parse(sdfTest.format(date));
        System.out.println("wantedDate without formatting to yyyy-MM-dd is " + wantedDateTest);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date wantedDate = sdf.parse(sdf.format(date));
        System.out.println("wantedDate is " + wantedDate);

        //We can get i = 0 value which is today's weather, i=1,2,3,4,...,7
        for (int i = 0; i < jsonArray.length(); ++i) {
            JSONObject singleJsonObject = (JSONObject)jsonArray.get(i);
            long singleDate = ((Number)singleJsonObject.get("dt")).longValue();
            singleDate *= 1000;
            singleDate -= 39600000L;
            //This line will give a 00:00:00 of that day
            //add three 0 after millis value
            Date eachResultDate = new Date(singleDate);
            System.out.println(eachResultDate);
            int eachResultHours = (int) eachResultDate.getTime() / 1000 / 3600;
            int wantedHours = (int) wantedDate.getTime() / 1000 / 3600;
            //Time zone difference is 13 hours
            if (eachResultHours <= wantedHours + 13 && eachResultHours > wantedHours - 11) {
                JSONArray singleArrayWeather = (JSONArray) singleJsonObject.get("weather");
                JSONObject singleWeatherObject = singleArrayWeather.getJSONObject(0);
                String singleWeather = singleWeatherObject.getString("main");
                System.out.println(singleWeather);
                String tempMin = ((JSONObject)singleJsonObject.get("temp")).get("min").toString();
                String tempMax = ((JSONObject)singleJsonObject.get("temp")).get("max").toString();
                Float temMinFloat = Float.parseFloat(tempMin);
                Float temMaxFloat = Float.parseFloat(tempMax);
                System.out.println(temMinFloat);
                System.out.println(temMaxFloat);
                WeatherModel weatherModel = new WeatherModel();
                weatherModel.setWeather(singleWeather);
                weatherModel.setDate(eachResultDate);
                weatherModel.setMaxTemp(temMaxFloat);
                weatherModel.setMinTemp(temMinFloat);
                return weatherModel;
            }
        }
        //temperature in Fahrenheit and wind speed in miles/hour
        throw new BusinessException(ErrorEnum.WRONG_DATE);
    }
}
