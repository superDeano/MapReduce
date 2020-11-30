package service;

import java.io.*;
import java.net.URL;
import java.net.URLConnection;
import com.mongodb.MongoClient;


public class StoreData {
    private static String fileUrl = "https://raw.githubusercontent.com/haniehalipour/Online-Machine-Learning-for-Cloud-Resource-Provisioning-of-Microservice-Backend-Systems/master/Workload%20Data/DVD-training.csv";
    private static BufferedReader br = null;
    MongoClient mongoClient;
    public static void storeData() {
        String line = "";
        String byComma = ",";

        try {
            URL url = new URL(fileUrl);
            URLConnection urlConnection = url.openConnection();

            urlConnection.setRequestProperty("X-Requested-With", "Curl");

            File csvFile;
            br = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
            while ((line = br.readLine()) != null) {

                // use comma as separator
                String[] lines = line.split(byComma);

                System.out.println("Data [cpu= " + lines[0] + " , name=" + lines[1] + "]");

            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (br != null) {
                try {
                    br.close();

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
