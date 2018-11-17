// Ric's Sample Weather API Application

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Iterator;

import org.json.JSONArray;
import org.json.JSONObject;

public class openWeatherLondon {

    public static void main(String[] args) {
            try{
                openWeatherLondon.api_Call();
            } catch(Exception error){
                System.out.println("Something went wrong!");
            }
    }


    public static void api_Call() throws Exception {
        //Valid URL
        String url ="https://samples.openweathermap.org/data/2.5/find?q=London&appid=b6907d289e10d714a6e88b30761fae22";

        //Bad URL Example
       // String url = "https://notworking.com";

        // New url/establish connection
        URL obj = new URL(url);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();

        // optional default is GET
        con.setRequestMethod("GET");

        //add request header
        con.setRequestProperty("User-Agent", "Chrome/70.0.3538.102");
        int responseCode = con.getResponseCode();
        System.out.println("\nSending 'GET' request to URL : " + url);
        System.out.println("Response Code : " + responseCode);

        // opens stream to read in the data
        BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
        String inputLine;

        //creates StringBuffer Object to store the data that is to be received
        StringBuffer response = new StringBuffer();
        while ((inputLine = in.readLine()) != null) {
            //updates StringBuffer objects/stores the data
            response.append(inputLine);
        }
        //closes Stream once all the data is read and stored to object
        in.close();

        // toString of entire JSON
        System.out.println("\nApi Response: ");
        System.out.println(response.toString());

        //Read JSON response and print
        JSONObject myResponse = new JSONObject(response.toString());
        System.out.println("\nApi Results: ");
        System.out.println("Message: "+myResponse.getString("message"));
        System.out.println("Cod: "+myResponse.getString("cod"));
        System.out.println("Count: "+myResponse.getInt("count"));


        // Prints the contents in the List from the Api
        System.out.println("List: "+myResponse.getJSONArray("list")+"\n");
        JSONArray results = myResponse.getJSONArray("list");

        System.out.println("\nDetails within the List: \n ");
        for (int i=0; i<results.length(); i++){
            JSONObject contents = results.getJSONObject(i);
            for(Iterator iterator = contents.keys(); iterator.hasNext();){
                String name = (String)iterator.next();
                System.out.println(name+" : "+contents.get(name));
            }
        }
    }
}
