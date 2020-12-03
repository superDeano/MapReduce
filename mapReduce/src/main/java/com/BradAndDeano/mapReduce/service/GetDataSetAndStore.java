package com.BradAndDeano.mapReduce.service;

import com.BradAndDeano.mapReduce.dao.DB;
import com.BradAndDeano.mapReduce.model.DataSet;
import com.BradAndDeano.mapReduce.model.ValueObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;

@Service
public class GetDataSetAndStore {
    @Autowired
    private DB db;

    private String fileUrl = "https://raw.githubusercontent.com/haniehalipour/Online-Machine-Learning-for-Cloud-Resource-Provisioning-of-Microservice-Backend-Systems/master/Workload%20Data/DVD-training.csv";

    private BufferedReader br = null;

    public void storeData() {
        String line = "";
        String byComma = ",";
        List<DataSet> dataSets = new ArrayList<>();

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
                DataSet dataSet = new DataSet();
                String[] lines = line.split(byComma);
                dataSet.setId(id++);
                dataSet.setCpuUtilization(Integer.parseInt(lines[0]));
                dataSet.setNetworkIn(Integer.parseInt(lines[1]));
                dataSet.setNetworkOut(Integer.parseInt(lines[2]));
                dataSet.setMemoryUtilization(Float.parseFloat(lines[3]));

                dataSets.add(dataSet);
            }
            db.insertData(dataSets);

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

    public List<ValueObject> doMp() {
        return db.tryMapReduce();
    }

    public String deleteCollection(){
        db.deleteCollection();
        return "Deleted";
    }


}
