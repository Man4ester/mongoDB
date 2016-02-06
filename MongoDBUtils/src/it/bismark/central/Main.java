package it.bismark.central;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;
import org.bson.Document;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

import it.bismark.central.blogic.interfaces.IMongoDbConnectionService;
import it.bismark.central.blogic.services.MongoDbConnectionService;
import it.bismark.central.models.Person;

public class Main {

	private static final Logger LOGGER = Logger.getLogger(Main.class);

	public static void main(String[] args) {
		BasicConfigurator.configure();
		LOGGER.debug("Start point");
		IMongoDbConnectionService connService = new MongoDbConnectionService();
		try {
			MongoDatabase db = connService.getMongoDatabaseByName("testDB");
			MongoCollection<Document> person = db.getCollection(Person.TABLE_NAME);
			Document p = new Document();
			p.append("_id", 11);
			p.append("name", "Petya");
			person.insertOne(p);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
