package it.bismark.central;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;
import org.bson.Document;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

public class Main {

	private static final Logger LOGGER = Logger.getLogger(Main.class);

	public static void main(String[] args) {
		BasicConfigurator.configure();
		LOGGER.debug("Start point");
		MongoClient client = new MongoClient();
		try {
			MongoDatabase db = client.getDatabase("test");
			MongoCollection<Document> person = db.getCollection("person");
			Document p = new Document();
			p.append("_id", 12);
			p.append("name", "Petya");
			person.insertOne(p);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			client.close();
		}

	}

}
