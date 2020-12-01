package com.BradAndDeano.mapReduce.model;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MapReduceResult {
    private int numberOfSamples;
    private int maximum;
    private int minimum;
    private float median;
    private float standardDeviation;
}
