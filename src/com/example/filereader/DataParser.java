package com.example.filereader;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.bson.codecs.DoubleCodec;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.Mongo;

public class DataParser {

	public static void main(String[] args) throws ParseException {
		String data = "I3Q PNH  REP  A*****  008181109900002000000000{USD201804210000000018042100100001002X                 10001   320320KHKH001180429960       ";
		String faresData = "0019W ABQ  USAMS  NLU2KWEU2 0000009999999W0205022ATP 00000172200USD200000000000   000000000000   0          4PFAT170803061620                    00000000000    0                    00000000000    0   00000000000   0000000000000I17214000009900002000100004        X            180429960";
		String rtgHdrData = "000000011I00001TL00001001AA 00000100000000010030917999999      ";
		String rtgDetails = "00000008200000001CABI  100000201              ";
		String rtgResrt = "0000000230103           R      N            ";
		String rtgText = "000000034MUST BE VIA CHI ONLY.                                                           ";

		Mongo mongo = new Mongo("10.90.29.104", 27001);
		DB db = mongo.getDB("ATPCO_Temp_Data");
		DBCollection collection = db.getCollection("Fares_Data_D290418");
		BasicDBObject document = new BasicDBObject();
		DBCursor obj = collection.find().limit(1);
		DBObject cur = collection.find().limit(1).next();
		DBObject outLine = (DBObject) cur.get("change_tags");
		System.out.println(cur);
		System.out.println("change_tags_1 :: " + outLine.get("change_tags_1"));
		// String [] changeTags = outLine.split(",");
		// for (String string : changeTags) {
		// if (string.contains("change_tags_1")) {
		// System.out.println(string);
		// System.out.println(string.charAt(21));
		// }
		// }

		// Iterator<DBObject> fields = obj.iterator();
		// while(fields.hasNext()){
		// DBObject field = (DBObject) fields.next().get("change_tags");
		// System.out.println(field);
		// System.out.println(field.get("change_tags_1"));
		// }
		// DBCursor obj = collection.find().limit(10);
		// System.out.println();
		// for (DBObject dbObject : obj) {
		// System.out.println(dbObject.toMap());
		// }
		// collection.insert(document);
		// mongo.close();
		// System.out.println(routingDataParser(rtgHdrData).toJson());
		// System.out.println(routingDataParser(rtgDetails));
		// System.out.println(routingDataParser(rtgResrt));
		// System.out.println(routingDataParser(rtgText));

	}

	public static BasicDBObject name() throws ParseException {
		BasicDBObject document = new BasicDBObject();
		BasicDBObject documentdet = new BasicDBObject();

		document.put("database", "mkyongDB");
		document.put("table", "hosting");
		document.put("date", convertDate("180423"));
		document.put("amount", getConvertedAmount("215000", "2"));

		List<BasicDBObject> documentDetail = new ArrayList<BasicDBObject>();
		for (int i = 0; i < 3; i++) {
			documentdet.put("records", 99);
			documentdet.put("index", "vps_index1");
			documentdet.put("active", "true");
			documentDetail.add(documentdet);
		}
		document.put("detail", documentDetail);
		return document;
	}

	public static BasicDBObject routingDataParser(String data, String batchNo) throws ParseException {
		BasicDBObject document = new BasicDBObject();
		int dataType = Integer.valueOf(data.substring(8, 9));
		switch (dataType) {
		case 1:
			document = parseHeaderRoutingData(data, batchNo);
			break;
		case 2:
			document = parseDDetailsRoutingData(data, batchNo);
			break;
		case 3:
			document = parseRestrictionRoutingData(data, batchNo);
			break;
		case 4:
			document = parseTextRestrictionRoutingData(data, batchNo);
			break;

		default:
			break;
		}

		return document;
	}

	private static BasicDBObject parseTextRestrictionRoutingData(String data, String batchNo) {
		BasicDBObject document = new BasicDBObject();
		document.put("batch_number", Integer.parseInt(batchNo));
		document.put("link_no", data.substring(0, 8).trim());
		document.put("type", data.substring(8, 9).trim());
		document.put("text", data.substring(9, 89).trim());
		return document;
	}

	private static BasicDBObject parseRestrictionRoutingData(String data, String batchNo) {
		BasicDBObject document = new BasicDBObject();
		document.put("batch_number", Integer.parseInt(batchNo));
		document.put("record_id",data.substring(0, 8).trim().concat(data.substring(9, 11).trim()).concat(data.substring(11, 13).trim()));
		document.put("link_no", data.substring(0, 8).trim());
		document.put("type", data.substring(8, 9).trim());
		document.put("seq_no", data.substring(9, 11).trim());
		document.put("rest_no", data.substring(11, 13).trim());
		document.put("market_info_ind", data.substring(13, 14).trim());
		document.put("market_info_city_1", data.substring(14, 19).trim());
		document.put("market_info_city_2", data.substring(19, 24).trim());
		document.put("appl", data.substring(24, 25).trim());
		document.put("via_ind", data.substring(25, 26).trim());
		document.put("via_value", data.substring(27, 31).trim());
		document.put("via_non_dir", data.substring(31, 32).trim());
		document.put("via_air_sur", data.substring(32, 33).trim());
		document.put("mpm", data.substring(33, 38).trim());
		document.put("reserved", data.substring(38, 44).trim());

		return document;
	}

	private static BasicDBObject parseDDetailsRoutingData(String data, String batchNo) {
		BasicDBObject document = new BasicDBObject();
		document.put("batch_number", Integer.parseInt(batchNo));
		document.put("record_id", data.substring(0, 8).trim().concat(data.substring(9, 17).trim()));
		document.put("link_no", data.substring(0, 8).trim());
		document.put("type", data.substring(8, 9).trim());
		document.put("city_1_no", data.substring(9, 17).trim());
		document.put("city_1_id", data.substring(17, 18).trim());
		document.put("city_1_name", data.substring(18, 23).trim());
		document.put("city_1_tag", data.substring(23, 24).trim());
		document.put("next_city", data.substring(24, 32).trim());
		document.put("alternate_city", data.substring(32, 40).trim());
		document.put("reserved", data.substring(40, 46).trim());

		return document;
	}

	private static BasicDBObject parseHeaderRoutingData(String data, String batchNo) throws ParseException {
		BasicDBObject document = new BasicDBObject();
		document.put("batch_number", Integer.parseInt(batchNo));
		document.put("record_id", data.substring(22, 25).trim().concat(data.substring(25, 28).trim()).concat(data.substring(28, 32).trim()));
		document.put("link_no", data.substring(0, 8).trim());
		document.put("type", data.substring(8, 9).trim());
		document.put("action", data.substring(9, 10).trim());
		document.put("mcn", data.substring(10, 15).trim());
		document.put("batch_ci", data.substring(15, 17).trim());
		document.put("batch_no", data.substring(17, 22).trim());
		document.put("tar_no", data.substring(22, 25).trim());
		document.put("cxr_cd", data.substring(25, 28).trim());
		document.put("rtg_no", data.substring(28, 32).trim());
		document.put("counts_vers", data.substring(32, 34).trim());
		document.put("count_type_2", data.substring(34, 42).trim());
		document.put("count_type_3", data.substring(42, 44).trim());
		document.put("count_type_4", data.substring(44, 45).trim());
		document.put("dates_effective", (getDate(data.substring(45, 51).trim()) == "indef" ? "indef"
				: convertDate(data.substring(45, 51).trim())));
		document.put("dates_discontinue", (getDate(data.substring(51, 57).trim()) == "indef" ? "indef"
				: convertDate(data.substring(51, 57).trim())));
		document.put("di", data.substring(57, 58).trim());
		document.put("drv", data.substring(58, 59).trim());
		document.put("cp", data.substring(59, 60).trim());
		document.put("int_pt", data.substring(60, 61).trim());
		document.put("unt_pt", data.substring(61, 62).trim());
		document.put("reserved", data.substring(62, 63).trim());

		return document;
	}

	public static BasicDBObject parsingFaresData(String data, String batchNo) throws ParseException {
		BasicDBObject document = new BasicDBObject();
		BasicDBObject documentDetails = new BasicDBObject();

		document.put("batch_number", Integer.parseInt(batchNo));
		document.put("record_id",
				data.substring(0, 3).trim().concat(data.substring(3, 6).trim()).concat(data.substring(20, 28).trim())
						.concat(data.substring(6, 11).trim()).concat(data.substring(13, 18).trim())
						.concat(data.substring(246, 249).trim()));
		document.put("tar_no", data.substring(0, 3).trim());
		document.put("cxr_cd", data.substring(3, 6).trim());
		document.put("origin_city", data.substring(6, 11).trim());
		document.put("origin_country", data.substring(11, 13).trim());
		document.put("destination_city", data.substring(13, 18).trim());
		document.put("destination_country", data.substring(18, 20).trim());
		document.put("fare_class_cd", data.substring(20, 28).trim());
		document.put("dates_effective", (getDate(data.substring(28, 34).trim()) == "indef" ? "indef"
				: convertDate(data.substring(28, 34).trim())));
		document.put("dates_discontinue", (getDate(data.substring(34, 40).trim()) == "indef" ? "indef"
				: convertDate(data.substring(34, 40).trim())));
		document.put("rules_no", data.substring(40, 44).trim());
		document.put("rtg_no", data.substring(44, 48).trim());
		document.put("ow_rt", data.substring(48, 49).trim());
		document.put("source", data.substring(49, 53).trim());
		document.put("fare_origin_amount",
				getConvertedAmount(data.substring(53, 64).trim(), data.substring(67, 68).trim()));
		document.put("fare_origin_cur_cd", data.substring(64, 67).trim());
		document.put("fare_origin_dec", data.substring(67, 68).trim());
		document.put("fare_destination_amount",
				getConvertedAmount(data.substring(68, 79).trim(), data.substring(82, 83).trim()));
		document.put("fare_destination_cur_cd", data.substring(79, 82).trim());
		document.put("fare_destination_dec", data.substring(82, 83).trim());
		document.put("fare_other_amount",
				getConvertedAmount(data.substring(83, 94).trim(), data.substring(97, 98).trim()));
		document.put("fare_other_cur_cd", data.substring(94, 97).trim());
		document.put("fare_other_dec", data.substring(97, 98).trim());
		document.put("airport_origin", data.substring(98, 103).trim());
		document.put("airport_destination", data.substring(103, 108).trim());
		document.put("ftnt", data.substring(108, 110).trim());
		document.put("directional_indicator", data.substring(110, 111).trim());
		document.put("global_indicator", data.substring(111, 113).trim());
		document.put("tar_eff_date", (getDate(data.substring(113, 119).trim()) == "indef" ? "indef"
				: convertDate(data.substring(113, 119).trim())));
		document.put("mpm", data.substring(119, 124).trim());
		document.put("cab", data.substring(124, 125).trim());
		document.put("origin_addon_fare_class_cd", data.substring(125, 133).trim());
		document.put("origin_addon_rtg", data.substring(133, 137).trim());
		document.put("origin_addon_ftnt", data.substring(137, 140).trim());
		document.put("origin_addon_gateway", data.substring(131, 145).trim());
		document.put("origin_addon_amount",
				getConvertedAmount(data.substring(145, 156).trim(), data.substring(160, 161).trim()));
		document.put("origin_addon_sign", data.substring(156, 157).trim());
		document.put("origin_addon_cur", data.substring(157, 160).trim());
		document.put("origin_addon_dec", data.substring(160, 161).trim());
		document.put("destination_addon_fare_class_cd", data.substring(161, 169).trim());
		document.put("destination_addon_rtg", data.substring(169, 173).trim());
		document.put("destination_addon_ftnt", data.substring(173, 176).trim());
		document.put("destination_addon_gateway", data.substring(176, 181).trim());
		document.put("destination_addon_amount",
				getConvertedAmount(data.substring(181, 192).trim(), data.substring(196, 197).trim()));
		document.put("destination_addon_sign", data.substring(192, 193).trim());
		document.put("destination_addon_cur", data.substring(193, 196).trim());
		document.put("destination_addon_dec", data.substring(196, 197).trim());
		document.put("pub_fare_ftnt", data.substring(197, 200).trim());
		document.put("pub_fare_amount",
				getConvertedAmount(data.substring(200, 211).trim(), data.substring(214, 215).trim()));
		document.put("pub_fare_cur", data.substring(211, 214).trim());
		document.put("pub_fare_dec", data.substring(214, 215).trim());
		document.put("sale_dates_first", (getDate(data.substring(215, 221).trim()) == "indef" ? "indef"
				: convertDate(data.substring(215, 221).trim())));
		document.put("sale_dates_last", (getDate(data.substring(221, 227).trim()) == "indef" ? "indef"
				: convertDate(data.substring(221, 227).trim())));
		document.put("action", data.substring(227, 228).trim());
		document.put("mcn", data.substring(228, 233).trim());
		document.put("old_mcn", data.substring(233, 238).trim());
		document.put("batch_ci", data.substring(238, 240).trim());
		document.put("batch_no", data.substring(240, 245).trim());
		document.put("prop", data.substring(245, 246).trim());
		document.put("link_no", data.substring(246, 249).trim());
		document.put("link_seq", data.substring(249, 254).trim());
		document.put("type", data.substring(254, 257).trim());
		documentDetails.put("change_tags_1", data.substring(257, 258).trim());
		documentDetails.put("change_tags_2", data.substring(258, 259).trim());
		documentDetails.put("change_tags_3", data.substring(259, 260).trim());
		documentDetails.put("change_tags_4", data.substring(260, 261).trim());
		documentDetails.put("change_tags_5", data.substring(261, 262).trim());
		documentDetails.put("change_tags_6", data.substring(262, 263).trim());
		documentDetails.put("change_tags_7", data.substring(264, 265).trim());
		documentDetails.put("change_tags_8", data.substring(265, 267).trim());
		documentDetails.put("change_tags_9", data.substring(267, 268).trim());
		documentDetails.put("change_tags_10", data.substring(266, 267).trim());
		documentDetails.put("change_tags_11", data.substring(267, 268).trim());
		documentDetails.put("change_tags_12", data.substring(268, 269).trim());
		documentDetails.put("change_tags_13", data.substring(269, 270).trim());
		documentDetails.put("change_tags_14", data.substring(270, 271).trim());
		documentDetails.put("change_tags_15", data.substring(271, 272).trim());
		documentDetails.put("change_tags_16", data.substring(272, 273).trim());
		documentDetails.put("change_tags_17", data.substring(273, 274).trim());
		documentDetails.put("change_tags_18", data.substring(274, 275).trim());
		document.put("change_tags", documentDetails);
		document.put("gfs_date", (getDate(data.substring(275, 281).trim()) == "indef" ? "indef"
				: convertDate(data.substring(275, 281).trim())));
		document.put("gfs_number", data.substring(281, 284).trim());
		// System.out.println(document.toString());

		return document;
	}

	public static BigDecimal getConvertedAmount(String amount, String dec) {
		BigDecimal fixAmount = new BigDecimal("0");
		if (amount.length() > 0) {
			int insertDec = amount.length() - Integer.parseInt(dec.equals("") ? "0" : dec);
			fixAmount = new BigDecimal(new StringBuilder(amount).insert(insertDec, ".").toString());
			return fixAmount;
		} else {
			return fixAmount;
		}
	}

	public static String getDate(String date) {

		if (date.equals("999999") || date.equals("000000") || date.equals("")) {
			return "indef";
		} else {
			return date;
		}
	}

	public static Date convertDate(String date) throws ParseException {
		SimpleDateFormat formatter = new SimpleDateFormat("yyMMdd HH:mm:ss");
		return formatter.parse(date.concat(" 00:00:00"));
	}

	public static BasicDBObject parsingADDsOnData(String data, String batchNo) throws ParseException {
		BasicDBObject document = new BasicDBObject();
		BasicDBObject documentDetails = new BasicDBObject();

		document.put("batch_number", Integer.parseInt(batchNo));
		document.put("record_id",
				data.substring(22, 25).trim().concat(data.substring(1, 4).trim()).concat(data.substring(14, 22).trim())
						.concat(data.substring(4, 9).trim()).concat(data.substring(9, 14).trim())
						.concat(data.substring(72, 75).trim()));
		document.put("action", data.substring(0, 1));
		document.put("cxr_cd", data.substring(1, 4));
		document.put("market_origin_city", data.substring(4, 9));
		document.put("market_dest_city", data.substring(9, 14));
		document.put("fare_class_cd", data.substring(14, 22));
		document.put("tar_no", data.substring(22, 25));
		document.put("mcn", data.substring(25, 30));
		document.put("batch_ci", data.substring(30, 32));
		document.put("batch_no", data.substring(32, 37));
		document.put("prop_no", data.substring(37, 38));
		document.put("addon_amount", data.substring(38, 47));
		document.put("addon_curr", data.substring(47, 50));
		document.put("addon_dec", data.substring(50, 51));
		document.put("dates_effective", (getDate(data.substring(51, 58).trim()) == "indef" ? "indef"
				: convertDate(data.substring(51, 58).trim())));
		document.put("dates_discontinue", (getDate(data.substring(58, 65).trim()) == "indef" ? "indef"
				: convertDate(data.substring(58, 65).trim())));
		document.put("tar_eff_date", (getDate(data.substring(65, 72).trim()) == "indef" ? "indef"
				: convertDate(data.substring(65, 72).trim())));
		document.put("link_no", data.substring(72, 75));
		document.put("link_seq", data.substring(75, 80));
		document.put("recs", data.substring(80, 83));
		// document.put("TYPE TAG", data.substring(83, 101));
		documentDetails.put("tags_1", data.substring(83, 84));
		documentDetails.put("tags_2", data.substring(84, 85));
		documentDetails.put("tags_3", data.substring(85, 86));
		documentDetails.put("tags_4", data.substring(86, 87));
		documentDetails.put("tags_5", data.substring(87, 88));
		documentDetails.put("tags_6", data.substring(88, 89));
		documentDetails.put("tags_7", data.substring(89, 90));
		documentDetails.put("tags_8", data.substring(90, 91));
		documentDetails.put("tags_9", data.substring(91, 92));
		documentDetails.put("tags_10", data.substring(92, 93));
		documentDetails.put("tags_11", data.substring(93, 94));
		documentDetails.put("tags_12", data.substring(94, 95));
		documentDetails.put("tags_13", data.substring(95, 96));
		documentDetails.put("tags_14", data.substring(96, 97));
		documentDetails.put("tags_15", data.substring(97, 98));
		documentDetails.put("tags_16", data.substring(98, 99));
		documentDetails.put("tags_17", data.substring(99, 100));
		documentDetails.put("tags_18", data.substring(100, 101));
		document.put("change_tags", documentDetails);
		document.put("ow_rt", data.substring(101, 102));
		document.put("rtg_no", data.substring(102, 106));
		document.put("ftnt", data.substring(106, 109));
		document.put("geo_zone_origin", data.substring(109, 112));
		document.put("geo_zone_dest", data.substring(112, 115));
		document.put("origin_country", data.substring(115, 117));
		document.put("destination_country", data.substring(117, 119));
		document.put("addon_zone", data.substring(119, 122));
		document.put("gfs_date", (getDate(data.substring(122, 128).trim()) == "indef" ? "indef"
				: convertDate(data.substring(122, 128).trim())));
		document.put("gfs_no", data.substring(128, 131));
		document.put("filler", data.substring(131, 138));
		// System.out.println(document.toJson());
		return document;
	}

}
