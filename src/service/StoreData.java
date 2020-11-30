package service;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;


public class StoreData {
    private static String fileUrl = "https://raw.githubusercontent.com/haniehalipour/Online-Machine-Learning-for-Cloud-Resource-Provisioning-of-Microservice-Backend-Systems/master/Workload%20Data/DVD-training.csv";
    private static BufferedReader br = null;
    private static MongoClient mongoClient;

    public static void storeData() {
        String line = "";
        String byComma = ",";
        List<Document> dataSets = new ArrayList<>();

        try {
            URL url = new URL(fileUrl);
            URLConnection urlConnection = url.openConnection();

            urlConnection.setRequestProperty("X-Requested-With", "Curl");

            br = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
            //Skip the header
            br.readLine();
            long id = 0;
            while ((line = br.readLine()) != null) {

                // use comma as separator
                Document document = new Document();
                String[] lines = line.split(byComma);
                document.put("_id", id++);
                document.put("cpuUsage", Integer.parseInt(lines[0]));
                document.put("networkIn", Integer.parseInt(lines[1]));
                document.put("networkOut", Integer.parseInt(lines[2]));
                document.put("memoryUtilization", Float.parseFloat(lines[3]));

                dataSets.add(document);
            }
            storeDataInDb(dataSets);

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

    private static void storeDataInDb(List<Document> dataSets) {

        MongoClientURI uri = new MongoClientURI("mongodb://root:root@localhost:27017/?authSource=admin");
        mongoClient = new MongoClient(uri);
        MongoDatabase db = mongoClient.getDatabase("DataSets");
        MongoCollection<Document> documentMongoCollection = db.getCollection("dvd");

        documentMongoCollection.insertMany(dataSets);
    }

}
