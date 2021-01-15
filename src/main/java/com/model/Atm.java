package com.model;


/**
 * @author Seenivasan Chandrasekaran
 */
public class Atm {
	
    private int hundreds;
    private int fiveHundreds;
    private int twoThousands;

	public int getHundreds() {
		return hundreds;
	}

	public void setHundreds(int hundreds) {
		this.hundreds = hundreds;
	}

	public int getFiveHundreds() {
		return fiveHundreds;
	}

	public void setFiveHundreds(int fiveHundreds) {
		this.fiveHundreds = fiveHundreds;
	}

	public int getTwoThousands() {
		return twoThousands;
	}

	public void setTwoThousands(int twoThousands) {
		this.twoThousands = twoThousands;
	}

	
    public Atm(int hundreds,int fiveHundreds,int twoThousands) {
        this.hundreds = hundreds;
        this.fiveHundreds = fiveHundreds;
        this.twoThousands = twoThousands;
    }

  
}
