package com.example.filereader;

import java.io.IOException;
import java.net.UnknownHostException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.ParseException;
import java.util.stream.Stream;

import com.mongodb.BasicDBObject;
import com.mongodb.BasicDBObjectBuilder;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.Mongo;
import com.mongodb.MongoException;
import com.mongodb.util.JSON;

public class InsertDocumentApp {

	public static void main(String[] args) throws UnknownHostException {
		
	}

	public static void insertData(DB db, String data, String table, String batchNo) {

//		try {
//			int dataType = Integer.valueOf(data.substring(8, 9));
//			switch (dataType) {
//			case 1:
//				table = table.concat("_Header");
//				break;
//			case 2:
//				table = table.concat("_Details");
//				break;
//			case 3:
//				table = table.concat("_Restriction");
//				break;
//			case 4:
//				table = table.concat("_Text_Restriction");
//				break;
//			default:
//				break;
//			}
//		} catch (Exception e) {
//			System.out.println(data.toString());
//		}
		
		DBCollection collection = db.getCollection(table);
		BasicDBObject document = new BasicDBObject();
		
		try {
//			document = DataParser.parsingADDsOnData(data, batchNo);
			document = DataParser.parsingFaresData(data, batchNo);
//			document = DataParser.routingDataParser(data, batchNo);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		collection.insert(document);
	}

	public static BasicDBObject parsingData(BasicDBObject document, String data) {
		BasicDBObject documentDetails = new BasicDBObject();

		document.put("ACTION", data.substring(0, 1).trim());
		document.put("CXR CD", data.substring(1, 4).trim());
		document.put("MARKET ORIG", data.substring(4, 9).trim());
		document.put("MARKET DEST", data.substring(9, 14).trim());
		document.put("FARE CLASS CD", data.substring(14, 22).trim());
		document.put("TAR NO", data.substring(22, 25).trim());
		document.put("MCN", data.substring(25, 30).trim());
		document.put("BATCH CI", data.substring(30, 32).trim());
		document.put("BATCH NO", data.substring(32, 37).trim());
		document.put("PROP", data.substring(37, 38).trim());
		document.put("ADD-ON AMOUNT", data.substring(38, 47).trim());
		document.put("ADD-ON CUR CD", data.substring(47, 50).trim());
		document.put("ADD-ON DEC", data.substring(50, 51).trim());
		document.put("DATES EFF", data.substring(51, 58).trim());
		document.put("DISC", data.substring(58, 65).trim());
		document.put("TAR EFF", data.substring(65, 72).trim());
		document.put("LINK NO", data.substring(72, 75).trim());
		document.put("LINK SEQ", data.substring(75, 80).trim());
		document.put("RECS", data.substring(80, 83).trim());
			documentDetails.put("tags_1", data.substring(83, 84).trim());
			documentDetails.put("tags_2", data.substring(84, 85).trim());
			documentDetails.put("tags_3", data.substring(85, 86).trim());
			documentDetails.put("tags_4", data.substring(86, 87).trim());
			documentDetails.put("tags_5", data.substring(87, 88).trim());
			documentDetails.put("tags_6", data.substring(88, 89).trim());
			documentDetails.put("tags_7", data.substring(89, 90).trim());
			documentDetails.put("tags_8", data.substring(90, 91).trim());
			documentDetails.put("tags_9", data.substring(91, 92).trim());
			documentDetails.put("tags_10", data.substring(92, 93).trim());
			documentDetails.put("tags_11", data.substring(93, 94).trim());
			documentDetails.put("tags_12", data.substring(94, 95).trim());
			documentDetails.put("tags_13", data.substring(95, 96).trim());
			documentDetails.put("tags_14", data.substring(96, 97).trim());
			documentDetails.put("tags_15", data.substring(97, 98).trim());
			documentDetails.put("tags_16", data.substring(98, 99).trim());
			documentDetails.put("tags_17", data.substring(99, 100).trim());
			documentDetails.put("tags_18", data.substring(100, 101).trim());
		document.put("TYPE TAG", documentDetails);
		document.put("OW/RT", data.substring(101, 102).trim());
		document.put("RTG NO", data.substring(102, 106).trim());
		document.put("FTNT", data.substring(106, 109).trim());
		document.put("GEO ZONES ORIG", data.substring(109, 112).trim());
		document.put("GEO ZONES DEST", data.substring(112, 115).trim());
		document.put("CNTRY CODE ORIG", data.substring(115, 117).trim());
		document.put("CNTRY CODE DEST", data.substring(117, 119).trim());
		document.put("ADD-ON ZONE", data.substring(119, 122).trim());
		document.put("GFS FA DATES", data.substring(122, 128).trim());
		document.put("GFS FA NO", data.substring(128, 131).trim());
		document.put("FILLER", data.substring(131, 138).trim());
//		System.out.println(document.toJson());

		return document;
	}

}

class RunnableDemo implements Runnable {
	private Thread t;
	private String threadName;
	private String filePath;
	private int threadId ;
	private int batchNo;
	Mongo mongo = new Mongo("localhost", 27017);
	DB db = mongo.getDB("yourdb");
	String collection = "testTread";
	
	RunnableDemo(int id, String url, int batchNo) {
		threadId = id;
		threadName = "Thread ".concat(String.valueOf(threadId));
		filePath = url;
		batchNo = batchNo;
		System.out.println("Creating thread " + threadName);
	}

	public void run() {
		System.out.println("inserting data using "+ threadName);
		long startTime = System.currentTimeMillis();
		System.out.println("Start..." + startTime);
		Stream<String> stream = null;
		try {
			stream = Files.lines(Paths.get(filePath));
			System.out.println("start insert...");
//			stream.forEach(data -> InsertDocumentApp.insertData(db, data, collection, batchNo));
		} catch (IOException e) {
			e.printStackTrace();
		}
		t.currentThread().stop();
		System.out.println("Thread " + threadName + " exiting.");
		
		long stopTime = System.currentTimeMillis();
		long elapsedTime = stopTime - startTime;
		System.out.println("Done..." + stopTime);
		System.out.println("elapsedTime=" + elapsedTime);
	}

	public void start() {
		System.out.println("Starting Thread " + threadName);
		if (t == null) {
			t = new Thread(this, threadName);
			t.start();
		}
	}
}
