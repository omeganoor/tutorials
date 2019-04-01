package com.example.onlinetutorials;

public class atlassn {

	public static void main(String[] args) {
		String input = "|name|address|~n|Patrick|patrick@com.exampl.general.test.com|pat@com.exampl.general.test.com|~n|Annie|annie@com.exampl.general.test.com|~n|Zoe|~n";
		
		StringBuilder result = new StringBuilder();
        String [] dataRow = null;
        String [] dataField = null;
        int numOfRecord = 0;
        int numOfField = 0;
        int numOfEmpField = 0;
        int numOfFieldData = 0;
        String lastFieldName = "format_error";
        String rowSpliter = "~n";
        String colSpliter = "\\|";
        if (!input.substring(0,3).equals("~~~")){
        	dataRow = input.split(rowSpliter);
        	
            for (int i = 0 ;i<dataRow.length; i++) {
    			dataField = dataRow[i].split(colSpliter);    			
    			if (i>0) {
    				numOfFieldData += dataField.length-1;
    			}
    			
    			if(numOfField < dataField.length - 1) {
    				numOfField = dataField.length - 1;
    			}
    		}
            numOfRecord = dataRow.length-1;
            numOfEmpField = (numOfField * numOfRecord) - numOfFieldData; 
            if(numOfField > 2) {
            	lastFieldName = "address_".concat(String.valueOf(numOfField-2));
            }else {
            	lastFieldName = "address";
            }
        }
		        
        result.append(numOfRecord);
        result.append(':');
        result.append(numOfField);
        result.append(':');
        result.append(numOfEmpField);
        result.append(':');
        result.append(lastFieldName);
        
        System.out.println("res : "+result);
	}

}
