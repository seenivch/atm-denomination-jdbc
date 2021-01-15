package com.database;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.model.Atm;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * @author Seenivasan Chandrasekaran
 */
@Component
public class AtmDenominationDao {

    private final Logger logger = LoggerFactory.getLogger(AtmDenominationDao.class);

    @Autowired
    AtmJdbcRepository atmJdbcRepository;

    public Map<String,int[]> getValuesFromDB(){
    	 List<Atm> atm = atmJdbcRepository.findAll();
    	 Map<String,int[]> dataMap = new HashMap<String,int[]>();
    	 if(Objects.nonNull(atm)) {
    		 int hundred[] = new int[atm.size()];
    		 for (int i = 0; i< atm.size(); i++) {
    			 hundred[i] = atm.get(i).getHundreds();
    		 }
    		 int fiveHundred[] = new int[atm.size()];
    		 for (int i = 0; i< atm.size(); i++) {
    			 fiveHundred[i] = atm.get(i).getFiveHundreds();
    		 }
    		 int twoThousand[] = new int[atm.size()];
    		 for (int i = 0; i< atm.size(); i++) {
    			 twoThousand[i] = atm.get(i).getTwoThousands();
    		 }
    		 dataMap.put("hundred", hundred);
    		 dataMap.put("fiveHundred", fiveHundred);
    		 dataMap.put("twoThousand", twoThousand);
    	 }
    	 return dataMap;
    }
}
