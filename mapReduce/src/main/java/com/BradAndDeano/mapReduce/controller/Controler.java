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
        return getDataSetAndStore.storeData();
    }

    @GetMapping("/mp")
    public List<ValueObject> doMp() {
        return getDataSetAndStore.doMp();
    }

    @GetMapping("/delete")
    public String dropCollection() {
        return getDataSetAndStore.deleteCollection();
    }
}
