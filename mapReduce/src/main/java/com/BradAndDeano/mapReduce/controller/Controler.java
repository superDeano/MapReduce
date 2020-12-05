package com.BradAndDeano.mapReduce.controller;

import com.BradAndDeano.mapReduce.model.ValueObject;
import com.BradAndDeano.mapReduce.service.GetDataSetAndStore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class Controler {

    @Autowired
    private GetDataSetAndStore getDataSetAndStore;

    @GetMapping("/store")
    public String storeDataInDb() {
        getDataSetAndStore.storeData("https://raw.githubusercontent.com/haniehalipour/Online-Machine-Learning-for-Cloud-Resource-Provisioning-of-Microservice-Backend-Systems/master/Workload%20Data/DVD-testing.csv", "testing");
        return getDataSetAndStore.storeData("https://raw.githubusercontent.com/haniehalipour/Online-Machine-Learning-for-Cloud-Resource-Provisioning-of-Microservice-Backend-Systems/master/Workload%20Data/DVD-training.csv", "training");
    }

    @GetMapping("/mp/testing")
    public List<ValueObject> doMpTest() {
        return getDataSetAndStore.doMp("testing", "testingMapped");
    }
    @GetMapping("/mp/training")
    public List<ValueObject> doMpTrain() {
        return getDataSetAndStore.doMp("training", "trainingMapped");
    }

    @GetMapping("/delete")
    public String dropCollection() {
        getDataSetAndStore.deleteCollection("training");
        return getDataSetAndStore.deleteCollection("testing");
    }
}
