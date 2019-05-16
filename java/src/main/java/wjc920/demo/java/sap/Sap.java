package wjc920.demo.java.sap;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;

public class Sap {

    public static void main(String[] args) {
        // TODO Auto-generated method stub
        DataOutputStream dataOut = null;
        BufferedReader in = null;
        PrintWriter out = null;
        try {

            //API endpoint for API sandbox
            String url = "https://sandbox.api.sap.com/s4hanacloud/sap/opu/odata/sap/API_SALES_ORDER_SRV/A_SalesOrder('5272900')";
            String params = "{\"IncotermsTransferLocation\": \"suzhou\",\"IncotermsLocation1\":\"suzhou\"}";

            //Available API Endpoints
            //https://{host}:{port}/sap/opu/odata/sap/API_SALES_ORDER_SRV

            URL urlObj = new URL(url);
            HttpURLConnection connection = (HttpURLConnection) urlObj.openConnection();
            //setting request method
            connection.setRequestProperty("X-HTTP-Method-Override", "PATCH");
            connection.setRequestMethod("POST");

            //adding headers
            connection.setRequestProperty("Content-Type", "application/json");
            connection.setRequestProperty("Accept", "application/json");
            //API Key for API Sandbox
            connection.setRequestProperty("APIKey", "YE0lpqM2cEVRJMTdlPIsGmGtO8Axqq4y");
            connection.setRequestProperty("x-csrf-token", "yY0zoCa-TEMw5ZGCSenrlw==");
            connection.setRequestProperty("If-Match", "W/\"datetimeoffset'2019-05-10T07%3A44%3A16.7944700Z'\"");


            //Available Security Schemes for productive API Endpoints
            //Basic Authentication

            //Basic Auth : provide username:password in Base64 encoded in Authorization header
            //connection.setRequestProperty("Authorization","Basic <Base64 encoded value>");

            connection.setDoInput(true);

            //sending POST request
            connection.setDoOutput(true);

            connection.connect();
            out = new PrintWriter(new OutputStreamWriter(connection.getOutputStream(), "UTF-8"));
            System.out.println(params);
            out.print(params);
            out.flush();

            int responseCode = connection.getResponseCode();
            in = new BufferedReader(new InputStreamReader(connection.getInputStream(), "UTF-8"));
            String inputLine;
            StringBuffer response = new StringBuffer();
            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }

            //printing response
            System.out.println(response.toString());

        } catch (Exception e) {
            //do something with exception
            e.printStackTrace();
        } finally {
            try {
                if (dataOut != null) {
                    dataOut.close();
                }
                if (in != null) {
                    in.close();
                }

            } catch (IOException e) {
                //do something with exception
                e.printStackTrace();
            }
        }
    }
}
