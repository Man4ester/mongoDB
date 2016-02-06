package it.bismark.central.operations;

import static com.mongodb.client.model.Filters.and;
import static com.mongodb.client.model.Filters.eq;
import static com.mongodb.client.model.Filters.or;
import static java.util.Arrays.asList;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;
import org.bson.Document;

import com.mongodb.Block;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;

import it.bismark.central.blogic.interfaces.IMongoDbConnectionService;
import it.bismark.central.blogic.services.MongoDbConnectionService;
import it.bismark.central.models.Person;

public class ReadRecords {

	private static final Logger LOGGER = Logger.getLogger(ReadRecords.class);

	public static void main(String[] args) {
		BasicConfigurator.configure();
		LOGGER.debug("Start point");
		IMongoDbConnectionService connService = new MongoDbConnectionService();
		try {
			MongoDatabase db = connService.getMongoDatabaseByName("testDB");
			readAll(db);
			readUsingDocument(db);
			readUsingFilters(db);
		} catch (Exception e) {
			LOGGER.error(e.getLocalizedMessage());
		}
	}

	public static void readAll(MongoDatabase db) {
		LOGGER.info("read all records");
		MongoCollection<Document> person = db.getCollection(Person.TABLE_NAME);
		for (Document p : person.find()) {
			System.out.println(String.format("Read record ID %1$s NAME %2$s", p.get("_id"), p.get("name")));
		}
	}

	public static void readUsingDocument(MongoDatabase db) {
		LOGGER.info("readUsingDocument");
		MongoCollection<Document> person = db.getCollection(Person.TABLE_NAME);
		FindIterable<Document> iterable = person.find(new Document("name", "Petya"));
		iterable.forEach(new Block<Document>() {
			@Override
			public void apply(final Document p) {
				System.out.println(String.format("Read record ID %1$s NAME %2$s", p.get("_id"), p.get("name")));
			}
		});

		MongoCursor<Document> cAnd = person.find(new Document("name", "Petya").append("_id", 11)).iterator();
		while (cAnd.hasNext()) {
			Document p = cAnd.next();
			System.out.println(String.format("Read record ID %1$s NAME %2$s", p.get("_id"), p.get("name")));
		}

		MongoCursor<Document> cOr = person
				.find(new Document("$or", asList(new Document("name", "Petya"), new Document("name", "Sasha"))))
				.iterator();
		while (cOr.hasNext()) {
			Document p = cOr.next();
			System.out.println(String.format("Read record ID %1$s NAME %2$s", p.get("_id"), p.get("name")));
		}
	}

	public static void readUsingFilters(MongoDatabase db) {
		LOGGER.info("readUsingFilters");
		MongoCollection<Document> person = db.getCollection(Person.TABLE_NAME);

		MongoCursor<Document> cFilters = person.find(eq("name", "Petya")).iterator();
		while (cFilters.hasNext()) {
			Document p = cFilters.next();
			System.out.println(String.format("Read record ID %1$s NAME %2$s", p.get("_id"), p.get("name")));
		}

		MongoCursor<Document> cFiltersAnd = person.find(and(eq("name", "Petya"), eq("_id", 11))).iterator();
		while (cFiltersAnd.hasNext()) {
			Document p = cFiltersAnd.next();
			System.out.println(String.format("Read record ID %1$s NAME %2$s", p.get("_id"), p.get("name")));
		}

		MongoCursor<Document> cFiltersOr = person.find(or(eq("name", "Petya"), eq("name", "Sasha"))).iterator();
		while (cFiltersOr.hasNext()) {
			Document p = cFiltersOr.next();
			System.out.println(String.format("Read record ID %1$s NAME %2$s", p.get("_id"), p.get("name")));
		}
	}

}
