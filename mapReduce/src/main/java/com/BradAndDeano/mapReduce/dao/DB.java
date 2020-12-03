package com.BradAndDeano.mapReduce.dao;

import com.BradAndDeano.mapReduce.model.DataSet;
import com.BradAndDeano.mapReduce.model.MapReduceResult;
import com.BradAndDeano.mapReduce.model.ValueObject;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DB {
    List<ValueObject> tryMapReduce();
    void insertData(List<DataSet> dataSets);
    void deleteCollection();
}
