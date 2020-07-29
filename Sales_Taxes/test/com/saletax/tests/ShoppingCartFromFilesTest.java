package com.saletax.tests;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.decorator.ShoppingCart;
import com.decorator.Util;

public class ShoppingCartFromFilesTest {

	@Test
	public void testFileEntry1() {
		ShoppingCart shoppingCart = Util.getShoppingCart("input1.txt");
		assertTrue(shoppingCart.getItems().size() == 3);
		assertTrue(shoppingCart.getTotal() == 29.83);
	}

	@Test
	public void testFileEntry2() {
		ShoppingCart shoppingCart = Util.getShoppingCart("input2.txt");
		assertTrue(shoppingCart.getItems().size() == 2);
		assertTrue(shoppingCart.getTotal() == 65.15);
	}

	@Test
	public void testFileEntry3() {
		ShoppingCart shoppingCart = Util.getShoppingCart("input3.txt");
		assertTrue(shoppingCart.getItems().size() == 4);
		assertTrue(shoppingCart.getTotal() == 74.68);
	}

	@Test
	public void testFileEntry4() {
		ShoppingCart shoppingCart = Util.getShoppingCart("input4.txt");
		assertTrue(shoppingCart.getItems().isEmpty());
		assertTrue(shoppingCart.getTotal() == 0.0);
	}
}
