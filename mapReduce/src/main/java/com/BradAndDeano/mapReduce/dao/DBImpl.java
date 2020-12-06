package com.BradAndDeano.mapReduce.dao;

import com.BradAndDeano.mapReduce.model.DataSet;
import com.BradAndDeano.mapReduce.model.MapReduceResult;
import com.BradAndDeano.mapReduce.model.ValueObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.mapreduce.MapReduceOptions;
import org.springframework.data.mongodb.core.mapreduce.MapReduceResults;
import org.springframework.data.mongodb.core.query.Query;

import java.util.List;

public class DBImpl implements DB {
    @Autowired
    private MongoTemplate mongoOperations;

    @Override
    public List<ValueObject> tryMapReduce(String sourceCollection, String targetCollection) {
        String mapFn =
                "function() {\n" +
                        "    if (this.cpuUtilization >= 0 && this.cpuUtilization<= 10) {\n" +
                        "        emit('(0, 10]', this.cpuUtilization);\n" +
                        "    } else if (this.cpuUtilization > 10 && this.cpuUtilization <= 20) {\n" +
                        "        emit('(10, 20]', this.cpuUtilization);\n" +
                        "    } else if (this.cpuUtilization > 20 && this.cpuUtilization <= 30) {\n" +
                        "        emit('(20, 30]', this.cpuUtilization);\n" +
                        "    } else if (this.cpuUtilization > 30 && this.cpuUtilization <= 40) {\n" +
                        "        emit('(30, 40]', this.cpuUtilization);\n" +
                        "    } else if (this.cpuUtilization > 40 && this.cpuUtilization <= 50) {\n" +
                        "        emit('(40, 50]', this.cpuUtilization);\n" +
                        "    } else if (this.cpuUtilization > 50 && this.cpuUtilization <= 60) {\n" +
                        "        emit('(50, 60]', this.cpuUtilization);\n" +
                        "    } else if (this.cpuUtilization > 60 && this.cpuUtilization <= 70) {\n" +
                        "        emit('(60, 70]', this.cpuUtilization);\n" +
                        "    } else if (this.cpuUtilization > 70 && this.cpuUtilization <= 80) {\n" +
                        "        emit('(70, 80]', this.cpuUtilization);\n" +
                        "    } else if (this.cpuUtilization > 80 && this.cpuUtilization <= 90) {\n" +
                        "        emit('(80, 90]', this.cpuUtilization);\n" +
                        "    } else {\n" +
                        "        emit('(90, 100]', this.cpuUtilization);\n" +
                        "    }\n" +
                        "};";

        String reduceFn =
                "function(range, cpuUtils) {\n" +
                        "    var numSamples = cpuUtils.length;\n" +
                        "    var maxCpuUtil =0; var total = 0; var median = 0;var mean = 0; var stdDev = 0;\n" +
                        "    var minCpuUtil = 100;\n" +
                        "cpuUtils.sort();" +
                        "var middleIndex = Math.floor(numSamples/2);" +
                        "if (numSamples % 2 === 0){" +
                        "median = (cpuUtils[middleIndex] + cpuUtils[middleIndex - 1])/2;" +
                        "}else {" +
                        "median = cpuUtils[middleIndex];" +
                        "}" +
                        "    for (var i = 0; i < cpuUtils.length; i++) {\n" +
                        "        if (cpuUtils[i] > maxCpuUtil) {\n" +
                        "            maxCpuUtil = cpuUtils[i];\n" +
                        "        }\n" +
                        "        if (cpuUtils[i] < minCpuUtil) {\n" +
                        "            minCpuUtil = cpuUtils[i];\n" +
                        "        }\n" +
                        "        total += cpuUtils[i];\n" +
                        "    }\n" +
                        "    mean = total / numSamples;\n" +
                        "    stdDev = Math.sqrt(cpuUtils.map(x => Math.pow(x - mean, 2)).reduce((a, b) => a + b) / numSamples);\n" +
                        "    return {\n" +
                        "        \"numberOfSamples\": numSamples,\n" +
                        "        \"maximum\": maxCpuUtil,\n" +
                        "        \"minimum\": minCpuUtil,\n" +
                        "        \"median\": median,\n" +
                        "        \"standardDeviation\": stdDev\n" +
                        "    };\n" +
                        "};";

        MapReduceResults<MapReduceResult> result = mongoOperations.mapReduce(sourceCollection, mapFn, reduceFn, MapReduceOptions.options().actionReplace()
                        .outputCollection(targetCollection)
                , MapReduceResult.class);
        return mongoOperations.find(new Query(), ValueObject.class, targetCollection);
    }

    @Override
    public void insertData(List<DataSet> dataSets, String collectionName) {
        mongoOperations.insert(dataSets, collectionName);
    }

    @Override
    public void deleteCollection(String targetCollection){
        mongoOperations.dropCollection(targetCollection);
    }


}
