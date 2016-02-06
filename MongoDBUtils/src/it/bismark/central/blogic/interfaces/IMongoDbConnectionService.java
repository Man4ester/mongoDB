package it.bismark.central.blogic.interfaces;

import org.bson.Document;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

public interface IMongoDbConnectionService {

	MongoDatabase getMongoDatabaseByName(String name);

	MongoCollection<Document> getCollectionByName(String name, MongoDatabase db) throws IllegalArgumentException;
	
	

}
