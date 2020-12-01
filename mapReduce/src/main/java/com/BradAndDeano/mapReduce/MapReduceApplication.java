package com.BradAndDeano.mapReduce;

import com.BradAndDeano.mapReduce.dao.DB;
import com.BradAndDeano.mapReduce.dao.DBImpl;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.MongoTemplate;

@SpringBootApplication
public class MapReduceApplication {

	@Bean
	public DB getDb() {
		return new DBImpl();
	}

	public static void main(String[] args) {

		SpringApplication.run(MapReduceApplication.class, args);

	}

}
