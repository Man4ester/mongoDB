package it.bismark.central.blogic.services;

import org.bson.Document;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

import it.bismark.central.blogic.interfaces.IMongoDbConnectionService;

public class MongoDbConnectionService implements IMongoDbConnectionService {

	@Override
	public MongoDatabase getMongoDatabaseByName(String name) {
		@SuppressWarnings("resource")
		MongoClient client = new MongoClient();
		MongoDatabase db = client.getDatabase(name);
		return db;
	}

	@Override
	public MongoCollection<Document> getCollectionByName(String name, MongoDatabase db) {
		if (db == null) {
			throw new IllegalArgumentException("MongoDatabase is NULL");
		}
		return db.getCollection(name);
	}

}
