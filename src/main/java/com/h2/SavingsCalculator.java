package com.h2;

import java.time.LocalDate;
import java.time.Month;
import java.time.YearMonth;

public class SavingsCalculator {

	private float[] credits;
	private float[] debits;
	
	public SavingsCalculator(float[] credits, float[] debits) {
		this.credits = credits;
		this.debits = debits;
	}
	
	private float sumOfCredits() {
		float sum = 0.0f;
		
		for (float credit : credits) {
    		sum = sum + credit;
    	}
    	return sum;
	}
	
	private float sumOfDebits() {
		float sum = 0.0f;
		
		for (float debit : debits) {
    		sum = sum + debit;
    	}
    	return sum;
	}
	
	private static int remainingDaysInMonth(LocalDate date) {
		int year = date.getYear();
		int month = date.getMonthValue();

		YearMonth yearMonth = YearMonth.of(year , month );
		
		int totalDaysInMonth = yearMonth.lengthOfMonth();
		int remainingDays = totalDaysInMonth - date.getDayOfMonth();
		
		return remainingDays;
	}
	
	public float calculate() {
		float netSaving = sumOfCredits() - sumOfDebits();
		return netSaving;
	}
	
	public static void main( String[] args )
    {
		String[] creditsAsString = args[0].split(",");
		String[] debitsAsString = args[1].split(",");
		
		final float[] credits = new float[creditsAsString.length];
		final float[] debits = new float[debitsAsString.length];
		
		for(int i=0; i<creditsAsString.length; i++) {
			credits[i] = Utilities.getFloatValue(creditsAsString[i]);
		}
		
		for(int i=0; i<debitsAsString.length; i++) {
			debits[i] = Utilities.getFloatValue(debitsAsString[i]);
		}
		
		SavingsCalculator calculator = new SavingsCalculator(credits, debits);
		
		float netSavings = calculator.calculate();
		System.out.println("Net Savings = " + netSavings + ", remaining days in month = " + remainingDaysInMonth(LocalDate.now()));
    }
	
}
