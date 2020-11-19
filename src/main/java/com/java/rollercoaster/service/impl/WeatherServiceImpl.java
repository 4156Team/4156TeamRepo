package com.java.rollercoaster.service.impl;

import com.java.rollercoaster.errorenum.BusinessException;
import com.java.rollercoaster.errorenum.ErrorEnum;
import com.java.rollercoaster.service.WeatherService;
import com.java.rollercoaster.service.model.WeatherModel;


import kong.unirest.json.JSONArray;
import kong.unirest.json.JSONObject;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
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


    @Override
    public WeatherModel queryWeather(Date date) throws ParseException, BusinessException {

        String url = "https://api.openweathermap.org/data/2.5/onecall";
        String param = "lat=40.43&lon=-74&exclude=hourly,minutely,current"
                + "&appid=36ecbb1aa6c1f51110ee49fe989c46bf&units=imperial";
        String result = sendGet(url, param);
        JSONObject jsonObject = new JSONObject(result);
        JSONArray jsonArray = (JSONArray) jsonObject.get("daily");

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date wantedDate = sdf.parse(sdf.format(date));

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
            if (eachResultDate.equals(wantedDate)) {
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

    private static String sendGet(String url, String param) {
        String result = "";
        BufferedReader in = null;
        try {
            String urlNameString = url + "?" + param;
            URL realUrl = new URL(urlNameString);
            // 打开和URL之间的连接
            URLConnection connection = realUrl.openConnection();
            // 设置通用的请求属性
            connection.setRequestProperty("accept", "*/*");
            connection.setRequestProperty("connection", "Keep-Alive");
            connection.setRequestProperty("user-agent",
                    "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
            // 建立实际的连接
            connection.connect();
            // 获取所有响应头字段
            Map<String, List<String>> map = connection.getHeaderFields();

            // 定义 BufferedReader输入流来读取URL的响应
            in = new BufferedReader(new InputStreamReader(
                    connection.getInputStream()));
            String line;
            while ((line = in.readLine()) != null) {
                result += line;
            }
        } catch (Exception getException) {
            System.out.println("Exception occurred in sending get request" + getException);
            getException.printStackTrace();
        } finally {
            try {
                if (in != null) {
                    in.close();
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return result;
    }
}
