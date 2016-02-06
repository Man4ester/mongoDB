package it.bismark.central.operations;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;
import org.bson.Document;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

import it.bismark.central.blogic.interfaces.IMongoDbConnectionService;
import it.bismark.central.blogic.services.MongoDbConnectionService;
import it.bismark.central.models.Person;

public class WriteRecords {

	private static final Logger LOGGER = Logger.getLogger(WriteRecords.class);

	public static void main(String[] args) {
		BasicConfigurator.configure();
		LOGGER.debug("Start point");
		IMongoDbConnectionService connService = new MongoDbConnectionService();
		try {
			MongoDatabase db = connService.getMongoDatabaseByName("testDB");
			writeAll(db);
			writeOne(db);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void writeAll(MongoDatabase db) {
		BasicConfigurator.configure();
		LOGGER.debug("writeAll");
		try {
			MongoCollection<Document> person = db.getCollection(Person.TABLE_NAME);

			Document p1 = new Document();
			p1.append("_id", 3);
			p1.append("name", "Olya");

			Document p2 = new Document();
			p2.append("_id", 4);
			p2.append("name", "Andrey");

			List<Document> lstPersons = new ArrayList<>();
			lstPersons.add(p1);
			lstPersons.add(p2);

			person.insertMany(lstPersons);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public static void writeOne(MongoDatabase db) {
		BasicConfigurator.configure();
		LOGGER.debug("writeOne");
		try {
			MongoCollection<Document> person = db.getCollection(Person.TABLE_NAME);

			Document p1 = new Document();
			p1.append("_id", 3);
			p1.append("name", "Olya");

			person.insertOne(p1);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
