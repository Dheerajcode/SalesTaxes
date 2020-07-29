package com.decorator;

import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class ShoppingCart {

	private final Map<Item, Integer> itemMap = new HashMap<>();

	DecimalFormat df = new DecimalFormat("###.00");

	public void put(Item item, int count) {
		if (item.isImported())
			item = new ImportTaxDecorator(item);
		if (!item.isExempt())
			item = new SalesTaxDecorator(item);
		Integer i = this.itemMap.get(item);
		if (i != null)
			count += i;
		this.itemMap.put(item, count);
	}

	public void remove(Item item) {
		this.itemMap.remove(item);
	}

	public void clear() {
		this.itemMap.clear();
	}

	public Set<Item> getItems() {
		return itemMap.keySet();
	}

	public int getQuantity(Item item) {
		return itemMap.get(item);
	}

	public double getTaxtotal() {
		double taxtotal = 0;
		for (Item item : itemMap.keySet()) {
			double subTotal = item.getPrice() * getQuantity(item);
			double subInitTotal = item.getInitPrice() * getQuantity(item);
			taxtotal += subTotal - subInitTotal;
		}
		return taxtotal;
	}

	public double getTotal() {
		double total = 0;
		for (Item item : itemMap.keySet()) {
			double subTotal = item.getPrice() * getQuantity(item);
			total += subTotal;
		}
		return Util.roundPrice(total);
	}

	public void printOrderInput() {
		StringBuilder builder = new StringBuilder();
		builder.append("Order input: " + "\n");
		Set<Item> itemsKeySet = itemMap.keySet();
		for (Item item : itemsKeySet) {
			builder.append(itemMap.get(item) + " " + item.getName() + " at " + df.format(item.getInitPrice()) + "\n");
		}
		Logger.instance.log(builder.toString());
	}

	public void printOrderResults() {
		double taxtotal = 0;
		double total = 0;
		StringBuilder builder = new StringBuilder();
		builder.append("Order results: " + "\n");
		Set<Item> taxedItems = itemMap.keySet();
		for (Item item : taxedItems) {
			double subTotal = item.getPrice() * getQuantity(item);
			double subInitTotal = item.getInitPrice() * getQuantity(item);
			taxtotal += subTotal - subInitTotal;
			total += subTotal;
			builder.append(getQuantity(item) + " " + item.getName() + ": " + df.format(subTotal) + "\n");
		}
		total = Util.roundPrice(total);
		builder.append("Sales Taxes: " + df.format(taxtotal) + "\n");
		builder.append("Total: " + df.format(total) + "\n");
		Logger.instance.log(builder.toString());
	}

	public static void main(String[] args) {
		// Check how many arguments were passed in

		if (args.length == 0) {
			Logger.instance.log("Please pass file names input1.txt / input2.txt / input3.txt");
			System.exit(0);
		}
		for (String fileName : args) {
			ShoppingCart shoppinCart = Util.getShoppingCart(fileName);
			shoppinCart.printOrderInput();
			shoppinCart.printOrderResults();
		}
	}

}
