package wjc920.demo.java.sap;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

public class Sap {

    public static void main(String[] args) {
        Map<String, String> headerMap=getToken();

        DataOutputStream dataOut = null;
        BufferedReader in = null;

        try {

            //API endpoint for API sandbox
            String url = "https://sandbox.api.sap.com/s4hanacloud/sap/opu/odata/sap/API_SALES_ORDER_SRV/A_SalesOrder";
            String orderStr = "{\"SalesOrderType\":\"OR\",\"SalesOrganization\":\"1010\",\"DistributionChannel\":\"10\"," +
                    "\"OrganizationDivision\":\"00\",\"SoldToParty\":\"10100001\"," +
                    "\"PurchaseOrderByCustomer\":\"Create Sales Order Header\",\"to_Item\":[]}";


            //Available API Endpoints
            //https://{host}:{port}/sap/opu/odata/sap/API_SALES_ORDER_SRV

            URL urlObj = new URL(url);
            HttpURLConnection connection = (HttpURLConnection) urlObj.openConnection();
            //setting request method
            connection.setRequestMethod("POST");

            //adding headers
            connection.setRequestProperty("Content-Type", "application/json");
            connection.setRequestProperty("Accept", "application/json");
            //API Key for API Sandbox
            connection.setRequestProperty("APIKey", "1VkwCWkLA00F5nzvA5034Qk6uGAyXyVr");
            connection.setRequestProperty("Cookie", headerMap.get("Cookie"));
            connection.setRequestProperty("x-csrf-token", headerMap.get("x-csrf-token"));


            //Available Security Schemes for productive API Endpoints
            //Basic Authentication

            //Basic Auth : provide username:password in Base64 encoded in Authorization header
            //connection.setRequestProperty("Authorization","Basic <Base64 encoded value>");

            connection.setDoInput(true);

            //sending POST request
            connection.setDoOutput(true);
            OutputStream output = connection.getOutputStream();
            output.write(orderStr.getBytes("UTF-8"));
            output.flush();
            output.close();
            int responseCode = connection.getResponseCode();
            if (responseCode == 201) {
                in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            } else {
                in = new BufferedReader(new InputStreamReader(connection.getErrorStream()));
            }
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
    public static Map<String, String> getToken(){
        Map<String, String> result = new HashMap<>();
        DataOutputStream dataOut = null;
        BufferedReader in =null;

        try {

            //API endpoint for API sandbox
            String url = "https://sandbox.api.sap.com/s4hanacloud/sap/opu/odata/sap/API_SALES_ORDER_SRV/A_SalesOrder";
            //API endpoint with optional query parameters
            //String url = "https://sandbox.api.sap.com/s4hanacloud/sap/opu/odata/sap/API_SALES_ORDER_SRV/A_SalesOrder?$top=integer&$skip=integer";
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
            connection.setRequestProperty("x-csrf-token","Fetch");


            //Available Security Schemes for productive API Endpoints
            //Basic Authentication

            //Basic Auth : provide username:password in Base64 encoded in Authorization header
            //connection.setRequestProperty("Authorization","Basic <Base64 encoded value>");

            connection.setDoInput(true);

            int responseCode = connection.getResponseCode();
            result.put("x-csrf-token",connection.getHeaderField("x-csrf-token"));
            result.put("Cookie",connection.getHeaderField("Set-Cookie"));
            if (responseCode == 201) {
                in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            } else {
                in = new BufferedReader(new InputStreamReader(connection.getErrorStream()));
            }
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
        return result;
    }
}
