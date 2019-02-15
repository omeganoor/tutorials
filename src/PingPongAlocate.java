import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class PingPongAlocate {

	public static void main(String[] args) {	
        int [][] arrTime = {{9,10},{9,12}};

//        int [][] arrTime = {{9,14},{8,13},{10,12},{10,15}};
//        int [][]arrTime = {{9 ,14 },
//        		{15, 17},
//        		{12, 13},
//        		{16, 18},
//        		{10, 15},
//        		{14, 16},
//        		{9 ,13 },
//        		{9 ,15 }};
//        	{{9,10},
//        		{9 ,12},
//        		{14,15},
//        		{16,18},
//        		{14,18},
//        		{10,13},
//        		{15,18},
//        		{10,14},
//        		{9 ,13}};
        		
//        	{{9, 11 } ,
//        		{12, 14} , 
//        		{13, 15} ,
//        		{11, 18} , 
//        		{14, 18} , 
//        		{10, 14} , 
//        		{15, 18}};
        
        int n = arrTime.length;
        System.out.println(getPlayedTimeNUmber(n, arrTime));
        for(int i : getPlayedTimeNUmber(n, arrTime)) {
        	System.out.println(i+", ");
        }
//        for (int i : alocPlayer(arrTime, n)){
//        	System.out.print(i+"  ");
//        }
	}
	static int[] getPlayedTimeNUmber(int n, int[][] time){
	    int [] playedTime = {0, 0};
	    
	    List<String> matchedTime = new ArrayList<>();
	    Map<String, Integer> playTime = new HashMap<>();
	    for(int i = 0; i < n; i++){
	      for(int j = i+1; j < n; j++){
	    	  System.out.println(time[i][0]+","+time[i][1]+","+time[j][0]+","+time[j][1]);
	        matchedTime.addAll(getMatchedTime(time[i][0], time[i][1], time[j][0], time[j][1]));
	      }
	    }
	    for (String mt : matchedTime) {
			if (playTime.get(mt) != null) {
				playTime.put(mt, playTime.get(mt)+1);
			}else {
				playTime.put(mt, 1);
			}
		}
	    for (String key : playTime.keySet()) {
			if(playTime.get(key) < 4) {
				playedTime[0] += 1;
			}else {
				playedTime[1] += 1;
			}
		}
	    
	    System.out.println(matchedTime);

	    return playedTime;
	  }

	  static List<String> getMatchedTime(int start1, int end1, int start2, int end2){
	    List<String> result = new ArrayList<>();
	    int startTime = Math.max(start1, start2);
	    int endTime = Math.min(end1, end2);
	    while(startTime < endTime){
	      result.add(String.valueOf(startTime)+", "+String.valueOf(startTime+1));
	      startTime++;
	    }
	    return result;
	  }

	public static int[] alocPlayer(int[][] availTime, int nPlayer){
		int [] timeplaying = new int[2];		
		int [] startTime = new int [nPlayer];
		int [] endTime = new int [nPlayer];
		int Tstart = 0;
		int Tend = 0;
		List<String> timePlay = new ArrayList<String>();
		int singlePlayerTime = 0;
		int doublePlayerTime = 0;
		
		for (int i = 0; i < nPlayer; i++) {
			startTime[i] = availTime[i][0];
			endTime[i]	= availTime[i][1];
		}
		
		for (int i = 0; i < endTime.length; i++) {
			for (int j = i+1; j < nPlayer; j++) {
				if (endTime[i] > startTime[j]) {
					Tstart = ((startTime[i]>startTime[j])?startTime[i]:startTime[j]);
					Tend = ((endTime[i]>endTime[j]?endTime[j]:endTime[i]));
					for (int j2 = Tstart; j2 < Tend; j2++) {
						timePlay.add(String.valueOf(j2).concat(",").concat(String.valueOf(j2+1)));
					}
				}
			} 
		}
		
		List<String> matchedTime = new ArrayList<>();
		Set<String> uniqueSet = new HashSet<String>(timePlay);
		for (String temp : uniqueSet) {
			System.out.println(temp + ": " + Collections.frequency(timePlay, temp));
			if (Collections.frequency(timePlay, temp)<4){
				singlePlayerTime ++;
			}else{
				doublePlayerTime++;
			}
		}
		
		timeplaying[0] = singlePlayerTime;
		timeplaying[1] = doublePlayerTime;
		return timeplaying;
	}
}
