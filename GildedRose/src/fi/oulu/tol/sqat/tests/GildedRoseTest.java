package fi.oulu.tol.sqat.tests;

import static org.junit.Assert.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.List;

import org.junit.Test;

import fi.oulu.tol.sqat.GildedRose;
import fi.oulu.tol.sqat.Item;

public class GildedRoseTest {

	@Test
	public void testTheTruth() {
		assertTrue(true);
	}
	@Test
	public void exampleTest() {
		//create an inn, add an item, and simulate one day
		GildedRose inn = new GildedRose();
		inn.setItem(new Item("+5 Dexterity Vest", 10, 20));
		inn.oneDay();
		
		//access a list of items, get the quality of the one set
		List<Item> items = inn.getItems();
		int quality = items.get(0).getQuality();
		
		//assert quality has decreased by one
		assertEquals("Failed quality for Dexterity Vest", 19, quality);
	}
	
	/*
	 * 
	 * Tests with normal items
	 */
	
	@Test
	public void testNormalItemSellIn() {
		
		GildedRose inn = new GildedRose();
		inn.setItem(new Item("+5 Dexterity Vest", 10, 20));
		inn.oneDay();
		
		List<Item> items = inn.getItems();
		int sellIn = items.get(0).getSellIn();
		
		assertEquals("SellIn didn't decrease", 9, sellIn);
	}
	
	@Test
	public void testNormalItemQualityNotNegative() {
		
		GildedRose inn = new GildedRose();
		inn.setItem(new Item("+5 Dexterity Vest", 10, 0));
		inn.oneDay();
		
		List<Item> items = inn.getItems();
		
		assertEquals("SellIn didn't decrease", 9, items.get(0).getSellIn());
		assertEquals("Quality cant be negative", 0, items.get(0).getQuality());
	}
	
	@Test
	public void testNormalItemQualityAfterSellIn() {
		
		GildedRose inn = new GildedRose();
		inn.setItem(new Item("+5 Dexterity Vest", 0, 10));
		inn.oneDay();
		
		List<Item> items = inn.getItems();
		
		assertEquals("SellIn didn't decrease", -1, items.get(0).getSellIn());
		assertEquals("Quality should decrase by two", 8, items.get(0).getQuality());
	}
	
	/*
	 * 
	 * Test Aged Brie
	 */
	
	@Test
	public void testAgedBrieQuality() {
		
		GildedRose inn = new GildedRose();
		inn.setItem(new Item("Aged Brie", 2, 0));
		inn.oneDay();
		
		List<Item> items = inn.getItems();
		
		assertEquals("SellIn didn't decrease", 1, items.get(0).getSellIn());
		assertEquals("Aged Brie quality should increase", 1, items.get(0).getQuality());
	}
	
	@Test
	public void testAgedBrieQualityAfterSellIn() {
		
		GildedRose inn = new GildedRose();
		inn.setItem(new Item("Aged Brie", 0, 0));
		inn.oneDay();
		
		List<Item> items = inn.getItems();
		
		assertEquals("SellIn didn't decrease", -1, items.get(0).getSellIn());
		assertEquals("Aged Brie quality should increase", 2, items.get(0).getQuality());
	}
	
	@Test
	public void testAgedBrieQualitylimit() {
		
		GildedRose inn = new GildedRose();
		inn.setItem(new Item("Aged Brie", 10, 49));
		
		
		inn.setItem(new Item("Aged Brie", 10, 50)); 
	    inn.oneDay();
		
		List<Item> items = inn.getItems();
		
		assertEquals("SellIn didn't decrease", 9, items.get(0).getSellIn());
		assertEquals("Aged Brie quality cannot be over 50", 50, items.get(0).getQuality());
	
		assertEquals("SellIn didn't decrease", 9, items.get(1).getSellIn());
		assertEquals("Aged Brie quality cannot be over 50", 50, items.get(1).getQuality());
	
		
	}
	
	
	
	/*
	 * 
	 * Test Sulfuras
	 */
	
	@Test
	public void testSulfuraQuality() {
		
		GildedRose inn = new GildedRose();
		inn.setItem(new Item("Sulfuras, Hand of Ragnaros", 0, 80));
		inn.oneDay();
		
		List<Item> items = inn.getItems();
		
		assertEquals("Quality should be constant", 80, items.get(0).getQuality());
	}
	
	@Test
	public void testSulfuraSellIn() {
		
		GildedRose inn = new GildedRose();
		inn.setItem(new Item("Sulfuras, Hand of Ragnaros", 0, 80));
		inn.oneDay();
		
		List<Item> items = inn.getItems();
		
		assertEquals("SellIn should be constant", 0, items.get(0).getSellIn());
	}
	
	/*
	 * 
	 * Test Backstage passes
	 */
		
	@Test
	public void testBackstageQualityPlusOne() {
		
		GildedRose inn = new GildedRose();
		inn.setItem(new Item("Backstage passes to a TAFKAL80ETC concert", 11, 10));
		inn.oneDay();
		
		List<Item> items = inn.getItems();
		
		assertEquals("Quality should increase by one", 11, items.get(0).getQuality());
	}
	
	@Test
	public void testBackstageQualityPlusTwo() {
		
		GildedRose inn = new GildedRose();
		inn.setItem(new Item("Backstage passes to a TAFKAL80ETC concert", 6, 10));
		inn.oneDay();
		
		List<Item> items = inn.getItems();
		
		assertEquals("Quality should increase by two", 12, items.get(0).getQuality());
	}
	
	@Test
	public void testBackstageQualityPlusThree() {
		
		GildedRose inn = new GildedRose();
		inn.setItem(new Item("Backstage passes to a TAFKAL80ETC concert", 5, 10));
		inn.oneDay();
		
		List<Item> items = inn.getItems();
		
		assertEquals("Quality should increase by three", 13, items.get(0).getQuality());
	}
	
	@Test
	public void testBackstageQualitydropsToZero() {
		
		GildedRose inn = new GildedRose();
		inn.setItem(new Item("Backstage passes to a TAFKAL80ETC concert", 0, 10));
		inn.oneDay();
		
		List<Item> items = inn.getItems();
		
		assertEquals("Sell in didn't decrease", -1, items.get(0).getSellIn());
		assertEquals("Quality should ne 0", 0, items.get(0).getQuality());
	}
	
	@Test
	public void testBackstageQualityPlusTwoQualityLimit() {
		
		GildedRose inn = new GildedRose();
		inn.setItem(new Item("Backstage passes to a TAFKAL80ETC concert", 8, 49));
		inn.setItem(new Item("Backstage passes to a TAFKAL80ETC concert", 10, 50));
		inn.oneDay();
		
		List<Item> items = inn.getItems();
		
		assertEquals("Quality should not be over 50", 50, items.get(0).getQuality());
		assertEquals("Quality should remain at 50", 50, items.get(1).getQuality());
	}
	
	
	
	@SuppressWarnings("static-access")
	@Test
	public void testMain() {
	    
	    GildedRose inn = new GildedRose();
	    
	    inn.main(null); 
	    
	    // Access the items list through the instance
	    List<Item> items = inn.getItems();
	    assertNotNull("Items list should not be null", items);
	    assertEquals("Items list should contain 6 items", 6, items.size());
	}
	
	@Test
	public void testConsoleOutput() {
		
	    ByteArrayOutputStream outPut = new ByteArrayOutputStream();
	    System.setOut(new PrintStream(outPut));
	    GildedRose.main(new String[]{});
	    assertTrue(outPut.toString().contains("OMGHAI!"));
	}
	
	/*
	 * 
	 * Loop Tests
	 */
	
	@Test
	public void loopTestZeroIterations() {
		
		GildedRose inn = new GildedRose();
	    inn.oneDay(); // No items in the inn
	    
	    List<Item> items = inn.getItems();
	    assertEquals("Items list should be empty", 0, items.size());
	}
	
	@Test
	public void loopTestThreeIterations() {
		
	    GildedRose inn = new GildedRose();
	    inn.setItem(new Item("Aged Brie", 2, 0));
	    inn.setItem(new Item("+5 Dexterity Vest", 10, 20));
	    inn.setItem(new Item("Sulfuras, Hand of Ragnaros", 0, 80));
	    inn.oneDay(); 
	    List<Item> items = inn.getItems();
	    
	    
	    assertEquals("Aged Brie SellIn didn't decrease", 1, items.get(0).getSellIn());
	    assertEquals("Aged Brie quality should increase", 1, items.get(0).getQuality());
	    
	    
	    assertEquals("Dexterity Vest SellIn didn't decrease", 9, items.get(1).getSellIn());
	    assertEquals("Dexterity Vest quality should decrease by 1", 19, items.get(1).getQuality());
	    
	    
	    assertEquals("Sulfuras SellIn should be constant", 0, items.get(2).getSellIn());
	    assertEquals("Sulfuras quality should be constant", 80, items.get(2).getQuality());
	}
	
	@Test
	public void loopTestHundredItems() {
		
	    GildedRose inn = new GildedRose();
	    
	    
	    for (int i = 0; i < 100; i++) {
	        inn.setItem(new Item("Item", 10, 20));
	    }
	    inn.oneDay();
	    
	    List<Item> items = inn.getItems();
	    assertEquals("List should have 100 items", 100, items.size());
	    
	    
	    for (Item item : items) {
	        assertEquals("SellIn should decrease", 9, item.getSellIn());
	        assertEquals("Quality should decrease", 19, item.getQuality());
	    }
	    
	}
	
	@Test
	public void testLargeNumberOfItems() {
	    
	    GildedRose inn = new GildedRose();
	    int n = 10000;
	    
	    for (int i = 0; i < n; i++) {
	        inn.setItem(new Item("Item", 10, 20));
	    }
	    inn.oneDay();
	    List<Item> items = inn.getItems();
	    
	    assertEquals("List size should be n", n, items.size());
	    
	}

	
}
