package com.exampl.filereader;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Time;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.mongodb.DB;
import com.mongodb.Mongo;

public class readfile {

	public static void main(String[] args) {
//		String url = "C:\\Project\\FMP\\Parse File\\QGA.PROD.S02AFAD.D180429.T1701.txt";
//		String url = "D:\\Project\\FMP\\DATA-SUBS\\Parse File\\QGA.PROD.S02AFPD.D180429.T1701.txt";
//		String url = "D:\\Project\\FMP\\DATA-SUBS\\Parse File\\QGA.PROD.S02AREC.D180429.T1700.txt";
		String url = "C:\\Project\\FMP\\Parse File\\QGA.PROD.S02AFPD.D180429.T1701.txt";
//		String url = "C:\\Project\\FMP\\Parse File\\QGA.PROD.S02AFPC.D180430.T2000.txt";
//		String url = "C:\\Project\\FMP\\Parse File\\qga.prod.s00sraa.d180429.t2359.txt";
//		String dirPath = "D:\\DataSubs\\FMP\\fares\\";
		String dirPath = "D:\\DataSubs\\FMP\\Routing";

		String filePath = "D:\\DataSubs\\FMP\\fares\\QGA.PROD.S02AFPD.D180429.T1701.txt";

		
		List<String> faresFile = GetFolderFile.getTotalFiles(dirPath);

		Mongo mongo = new Mongo("localhost", 27017);
//		Mongo mongo = new Mongo("10.90.29.104", 27001);

		DB db = mongo.getDB("ATPCO_Temp_Data");
		String collection = "fares_data";
//
//		long startTime = 0;
//		long stopTime = 0;
		
		final AtomicInteger counter = new AtomicInteger(0);
		int size = 0;		
//		try {
//			size = Files.lines(Paths.get(url)).count();
//			System.out.println(size);
//		} catch (IOException e1) {
//			e1.printStackTrace();
//		}
//		stopTime = System.currentTimeMillis();
//		System.out.println(stopTime);

		int maxSize = 1000000;
		int maxThread = 1;
		int active = 0;
		active = Thread.activeCount(); 
		int count = 0;
		int recSize = 0 ;
        System.out.println("total thread... "+active);
//        for (String filePath : faresFile) {
        	counter.incrementAndGet();
        	System.out.println("inserting file "+ filePath);
        	try {
        		recSize = Integer.parseInt(Files.lines(Paths.get(filePath)).findFirst().get().substring(29, 38));
				System.out.println("total data : " + recSize);
			} catch (Exception e) {
				// TODO: handle exception
			}
        	
        	try {
    			System.out.println("start insert record "+counter.get()+" at "+new Date());
				System.out.println("Inserting....");
    			Files.lines(Paths.get(filePath))
    			.skip(1).limit(recSize)
    			.forEach(data -> {
//    				System.out.println(data);
    				InsertDocumentApp.insertData(db, data, collection, String.valueOf(counter.get()));
    			});	
    		} catch (IOException e) {
    			e.printStackTrace();
    		}		
    		System.out.println("Finish Inserting at "+new Date());
//        }
        
        
		/*try {
//			System.out.println("Start..." + startTime);
			size = Integer.parseInt(Files.lines(Paths.get(url)).findFirst().get().substring(29, 38));			
			System.out.println("start inserting... "+size+" record");
			maxSize = size/maxThread;
			System.out.println("rows per thread..." +maxSize);
			for (int i = 0; i < maxThread; i++) {
				RunnableDemo1 thread = new RunnableDemo1(i, url, maxSize);
				thread.start();
			}
//			Files.lines(Paths.get(url))
//			.skip(1)
//			.limit(maxSize)
//			.forEach(data -> {
//				InsertDocumentApp.insertData(db, data, collection);
//			});
//			mongo.close();
		} catch (IOException e) {
			e.printStackTrace();
		}*/
		
//        active = Thread.activeCount();
//        System.out.println("total thread... "+active);
//        System.out.println(Thread.currentThread());
//		stopTime = System.currentTimeMillis();
//		long elapsedTime = stopTime - startTime;
//		System.out.println("Done..." + stopTime);
//		System.out.println("total rows " + maxSize);
//		System.out.println("elapsedTime=" + elapsedTime);
	}

}

class RunnableDemo1 implements Runnable {
	private Thread t;
	private String threadName;
	private String filePath;
	private int threadId ;
	private int maxRecord;
	private int startRecord = 1;
	private int batchNo;
	Mongo mongo = new Mongo("localhost", 27017);
//	Mongo mongo = new Mongo("10.90.29.23", 27017);

//	Mongo mongo = new Mongo("10.90.29.104", 27001);
	DB db = mongo.getDB("ATPCO_Temp_Data");
	String collection = "Atpco_Data_Cahnges";
	
	
	RunnableDemo1(int id, String url, int maxRec, int batchNo) {
		this.threadId = id;
		this.threadName = "Thread ".concat(String.valueOf(threadId));
		this.filePath = url;
		this.maxRecord = maxRec;
		this.batchNo = batchNo;
		System.out.println("Creating thread " + threadName);
	}

	public void run() {
		System.out.println("inserting data using "+ threadName);
		long startTime = System.currentTimeMillis();
		System.out.println("Start... "+threadId+" => " + startTime);
		try {
			System.out.println("start insert...");
			Files.lines(Paths.get(filePath))
			.skip(startRecord+(threadId*maxRecord))
			.limit(maxRecord)
			.forEach(data -> {
				InsertDocumentApp.insertData(db, data, collection, String.valueOf(batchNo));
			});	
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println("Thread " + threadName + " exiting.");
		
		long stopTime = System.currentTimeMillis();
		long elapsedTime = stopTime - startTime;
		System.out.println("Done..."+threadId+" => " + stopTime);
		System.out.println("elapsedTime for threadId "+ threadId+" = " + elapsedTime);
        System.out.println("total thread... "+Thread.activeCount());
        mongo.close();
		t.currentThread().stop();

	}

	public void start() {
		System.out.println("Starting Thread " + threadName);
		if (t == null) {
			t = new Thread(this, threadName);
			t.start();
		}
	}
}
