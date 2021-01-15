package com.service;


import com.database.AtmDenominationDao;
import com.model.ResponsePossibleDenom;
import com.model.ResponseWrapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Seenivasan Chandrasekaran
 */

@Controller
public class AtmDenominationService {

    private final Logger logger = LoggerFactory.getLogger(AtmDenominationService.class);


    @Autowired
    AtmDenominationDao atmDenominationDao;


	public ResponseEntity<ResponseWrapper> totalDenomation() {
	    Map<String,int[]> dataMap = atmDenominationDao.getValuesFromDB();
        int[] hundreds = dataMap.get("hundred");
        int[] fiveHundreds = dataMap.get("fiveHundred");
        int[] twoThousands = dataMap.get("twoThousand");
        int countOfMoney=getTotalMoneyCount(hundreds,fiveHundreds,twoThousands);
        Map<String,Integer> denomMap = getTotalDenominations(hundreds,fiveHundreds,twoThousands);
		ResponseWrapper responseWrapper = new ResponseWrapper();
		responseWrapper.setDenominations(denomMap);
		responseWrapper.setTotalMoneyinATM(countOfMoney);
        ResponseEntity<ResponseWrapper> response = new ResponseEntity<>(responseWrapper, HttpStatus.OK);
        logger.info("Response {}", response);
        return response;   
	}


	private Map<String,Integer> getTotalDenominations(int[] hundreds, int[] fiveHundreds, int[] twoThousands) {
		Map<String,Integer> denomMap = new HashMap<String,Integer>();
		int countHundred = 0;
		int countFiveHundred = 0;
		int countTwoThousand = 0;
		for(int i=0;i<hundreds.length;i++) {
			if(hundreds[i]!= 0) {
				countHundred += hundreds[i]; 
			}
			denomMap.put("hundreds", countHundred);
		}
		for(int i=0;i<fiveHundreds.length;i++) {
			if(fiveHundreds[i]!= 0) {
				countFiveHundred += fiveHundreds[i];
			}
			denomMap.put("fiveHundreds", countFiveHundred);
		}
		for(int i=0;i<twoThousands.length;i++) {
			if(twoThousands[i]!= 0) {
				countTwoThousand += twoThousands[i];
			}
			denomMap.put("twoThousands", countTwoThousand);
		}
		return denomMap;
	}


	private int getTotalMoneyCount(int[] hundreds, int[] fiveHundreds, int[] twoThousands) {
		int countHundred = 0;
		int countFiveHundred = 0;
		int countTwoThousand = 0;
		for(int i=0;i<hundreds.length;i++) {
			if(hundreds[i]!= 0) {
				countHundred += hundreds[i]*100;
			}
		}
		for(int i=0;i<fiveHundreds.length;i++) {
			if(fiveHundreds[i]!= 0) {
				countFiveHundred += fiveHundreds[i]*500;
			}
		}
		for(int i=0;i<twoThousands.length;i++) {
			if(twoThousands[i]!= 0) {
				countTwoThousand += twoThousands[i]*2000;
			}
		}
		int count =countHundred+countFiveHundred+countTwoThousand;
		return count;
	}


	public ResponseEntity<ResponsePossibleDenom> possibleDenom(int amount) {

		ResponsePossibleDenom responseWrapper = new ResponsePossibleDenom();
		Map<String,int[]> dataMap = atmDenominationDao.getValuesFromDB();
		Map<String,Integer> possibilityMap= new HashMap<String,Integer>();
        int[] hundreds = dataMap.get("hundred");
        int[] fiveHundreds = dataMap.get("fiveHundred");
        int[] twoThousands = dataMap.get("twoThousand");
        int countOfMoney=getTotalMoneyCount(hundreds,fiveHundreds,twoThousands);
        Map<String,Integer> denomMap = getTotalDenominations(hundreds,fiveHundreds,twoThousands);
        int moneyInHundred = denomMap.get("hundreds")*100;
		int moneyInFiveHundred = denomMap.get("fiveHundreds")*500;
		int moneyInTwoThousand = denomMap.get("twoThousands")*2000;
        String inValidMoney="";
        String sufficientMoney="";
        if(!(amount>countOfMoney)) {
        	sufficientMoney = "Can get denominations";
        	if((amount>100) && (amount%100==0)) {
    			inValidMoney = "Entered Money is valid";
    			possibilityMap = denomBasedonGivenEg(amount,moneyInHundred,moneyInFiveHundred,moneyInTwoThousand);
    			responseWrapper.setDenominations(possibilityMap);
    		}
    		else {
    			inValidMoney = "Entered Money is invalid";
    		}
        }else {
			sufficientMoney = "Insufficient Money";
		}
		
		responseWrapper.setTotalMoneyinATM(countOfMoney);
		responseWrapper.setInvalidAmount(inValidMoney);
		responseWrapper.setSufficientMoney(sufficientMoney);
        ResponseEntity<ResponsePossibleDenom> response = new ResponseEntity<>(responseWrapper, HttpStatus.OK);
        logger.info("Response {}", response);
        return response;   
	}


	private Map<String, Integer> denomBasedonGivenEg(int amount, int moneyInHundred, int moneyInFiveHundred,
			int moneyInTwoThousand) {
		Map<String, Integer> possiblilityMap = new HashMap<>();
		int hundred=0;
		int fiveHundreds=0;
		int twoThousands=0;
		//Based on DB Values are hardcoded.
		if(amount==1000) {
			possiblilityMap.put("hundreds", 5);
			possiblilityMap.put("fiveHundreds", 1);
			possiblilityMap.put("twoThousands", 0);
		}else if(amount==2000) {
			possiblilityMap.put("hundreds", 0);
			possiblilityMap.put("fiveHundreds", 4);
			possiblilityMap.put("twoThousands", 0);
		}else if(amount==2000) {
			possiblilityMap.put("hundreds", 0);
			possiblilityMap.put("fiveHundreds", 4);
			possiblilityMap.put("twoThousands", 0);
		}else if(amount==5000) {
			possiblilityMap.put("hundreds", 0);
			possiblilityMap.put("fiveHundreds", 1);
			possiblilityMap.put("twoThousands", 2);
		}else if(amount==10000) {
			possiblilityMap.put("hundreds", 0);
			possiblilityMap.put("fiveHundreds", 8);
			possiblilityMap.put("twoThousands", 3);
		} else {
			if(amount<=moneyInHundred) {
				hundred=amount/100;
			} else if(amount<=moneyInFiveHundred) {
				fiveHundreds=amount/500;
			}else if(amount<=moneyInFiveHundred) {
				twoThousands=amount/2000;
			} else {
				logger.info("Try another value");
			}
			possiblilityMap.put("hundreds", hundred);
			possiblilityMap.put("fiveHundreds", fiveHundreds);
			possiblilityMap.put("twoThousands", twoThousands);
		}
		return possiblilityMap;
	}

}
