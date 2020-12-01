package com.BradAndDeano.mapReduce.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Map;

@Getter
@Service
@AllArgsConstructor
@NoArgsConstructor
public class ValueObject {
    private String id;
    private MapReduceResult value;
}
