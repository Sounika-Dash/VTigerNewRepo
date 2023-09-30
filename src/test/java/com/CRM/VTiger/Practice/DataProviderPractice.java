package com.CRM.VTiger.Practice;


import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class DataProviderPractice {
	@Test(dataProvider = "dataSupplier")
	public void Create(String name, int age, char ch) {
		System.out.println(name + "-->" + age + "-->" + ch);

	}
	@DataProvider
	public Object[][] dataSupplier() {
		Object[][] data = new Object[3][3];
		data[0][0] = "Sriyanka";
		data[0][1] = 24;
		data[0][2] = 'S';
		data[1][0]="Priyanka";
		data[1][1]=26;
		data[1][2]='P';
		data[2][0]="Debasish";
		data[2][1]=22;
		data[2][2]='D';
		return data;
	}
}
