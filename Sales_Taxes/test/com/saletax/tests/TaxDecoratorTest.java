package com.saletax.tests;


import static org.junit.Assert.*;

import org.junit.Test;

import com.decorator.ImportTaxDecorator;
import com.decorator.Item;
import com.decorator.SalesTaxDecorator;
import com.decorator.WorkingItem;

public class TaxDecoratorTest {

	@Test
	public void testGetPrice() {
		Item item = new WorkingItem("Test", 100.00);
		assertEquals(item.getName(), "Test");
		assertTrue(Math.abs((item.getPrice() - 100.00)) < 0.001);
		
		item = new ImportTaxDecorator(item);
		assertTrue(Math.abs((item.getPrice() - 105.00)) < 0.001);

		item = new SalesTaxDecorator(item);
		System.out.println(item.getPrice());
		assertTrue(Math.abs((item.getPrice() - 115.00)) < 0.001);

		assertTrue(Math.abs((item.getInitPrice() - 100.00)) < 0.001);
	}

}
