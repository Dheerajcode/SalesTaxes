package com.decorator;

import java.io.BufferedReader;
import java.io.FileReader;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.HashSet;
import java.util.Set;

public class Util {

	private static Set<String> exemptItems = null;
	static {
		exemptItems = new HashSet<>();
		exemptItems.add("book");
		exemptItems.add("headache pills");
		exemptItems.add("packet of headache pills");
		exemptItems.add("box of imported chocolates");
		exemptItems.add("imported box of chocolates");
		exemptItems.add("box of chocolates");
		exemptItems.add("chocolate");
		exemptItems.add("chocolate bar");
		exemptItems.add("pills");
	}

	private Util() {
	}

	public static double nearest5Percent(double amount) {

		return BigDecimal.valueOf((Math.ceil(amount * 20) / 20)).setScale(2, RoundingMode.HALF_UP).doubleValue();

	}

	public static double roundPrice(double amount) {

		return BigDecimal.valueOf((amount)).setScale(2, RoundingMode.HALF_UP).doubleValue();

	}

	public static boolean isExempt(String name) {
		return exemptItems.contains(name);
	}

	public static ShoppingCart getShoppingCart(String fileName) {
		String filePath = "assets/".concat(fileName);
		ShoppingCart sc = new ShoppingCart();

		try (BufferedReader reader = new BufferedReader(new FileReader(filePath));) {

			String str;
			while ((str = reader.readLine()) != null) {
				if (ItemParser.matches(str) && !str.isEmpty())
					sc.put(ItemParser.parser(str), ItemParser.count(str));
				else if (!str.isEmpty())
					Logger.instance.log("unknown line format: " + str);
			}
		} catch (Exception e) {
			Logger.instance.log("error:" + e.getMessage());
		}
		return sc;
	}
}