package wjc920.demo.spark.sap;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class Sap {
    public static void main(String[] args) {
        DataOutputStream dataOut = null;
        BufferedReader in =null;

        try {

            //API endpoint for API sandbox
            String url = "https://sandbox.api.sap.com/s4hanacloud/sap/opu/odata/sap/API_SALES_ORDER_SRV/A_SalesOrder";
            //API endpoint with optional query parameters
            //String url = "https://sandbox.api.sap.com/s4hanacloud/sap/opu/odata/sap/API_SALES_ORDER_SRV/A_SalesOrder?$filter=string&$orderby=array";
            //To view the complete list of query parameters, see its API definition.


            //Available API Endpoints
            //https://{host}:{port}/sap/opu/odata/sap/API_SALES_ORDER_SRV

            URL urlObj = new URL(url);
            HttpURLConnection connection = (HttpURLConnection) urlObj.openConnection();
            //setting request method
            connection.setRequestMethod("GET");

            //adding headers
            connection.setRequestProperty("Content-Type","application/json");
            connection.setRequestProperty("Accept","application/json");
            //API Key for API Sandbox
            connection.setRequestProperty("APIKey","1VkwCWkLA00F5nzvA5034Qk6uGAyXyVr");


            //Available Security Schemes for productive API Endpoints
            //Basic Authentication

            //Basic Auth : provide username:password in Base64 encoded in Authorization header

            connection.setRequestProperty("Authorization","UDIwMDEyODg3MjMlM0FZY2Q5MjAuLg==");

            connection.setDoInput(true);

            int responseCode = connection.getResponseCode();
            in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
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
                if(dataOut != null) {
                    dataOut.close();
                }
                if(in != null) {
                    in.close();
                }

            } catch (IOException e) {
                //do something with exception
                e.printStackTrace();
            }
        }
    }
}
