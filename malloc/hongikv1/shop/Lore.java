package malloc.hongikv1.shop;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;

import org.bukkit.ChatColor;

public class Lore {

	public static HashMap<String,List<String>> items = new HashMap<String,List<String>>();
	public static List<String> shopLore = new ArrayList<String>();
	public static List<String> menuLore = new ArrayList<String>();
	public static List<String> WIPLore = new ArrayList<String>();
	public static List<String> shopDia = new ArrayList<String>();
	public static List<String> etcShop = new ArrayList<String>();
	public static List<String> etcDegg = new ArrayList<String>();
	public static List<String> etcWipe = new ArrayList<String>();
	public static List<String> warpShop = new ArrayList<String>();
	public static List<String> warpEnd = new ArrayList<String>();


	//Lore and item register should be optimized, but not planned
	public static void setupItems() {
		shopLore.add("광물 상점");
		menuLore.add("클릭하여 메뉴 열기");
		WIPLore.add("Work In Progress");
		shopDia.add(ChatColor.WHITE + "10,000₩");
		shopDia.add("");
		shopDia.add(ChatColor.WHITE + "[좌클릭] 판매");
		shopDia.add(ChatColor.WHITE + "[우클릭] 추가예정");
		shopDia.add(ChatColor.WHITE + "[쉬프트+클릭] 1세트 거래");
		etcShop.add(ChatColor.WHITE + "기타 아이템");
		etcDegg.add(ChatColor.WHITE + "2,000,000₩");
		etcDegg.add("");
		etcDegg.add(ChatColor.WHITE + "[좌클릭] 구매");
		etcWipe.add(ChatColor.WHITE + "" + ChatColor.STRIKETHROUGH + "10,000,000₩");
		etcWipe.add(ChatColor.WHITE + "1,000,000₩");
		etcWipe.add("");
		etcWipe.add(ChatColor.WHITE + "[좌클릭] 구매");
		warpShop.add(ChatColor.WHITE + "월드 워프");
		warpEnd.add(ChatColor.WHITE + "100,000₩");
		warpEnd.add("");
		warpEnd.add(ChatColor.WHITE + "[좌클릭] 구매");
		
		items.put("shop",shopLore);
		items.put("menu", menuLore);
		items.put("WIP", WIPLore);
		items.put("shopDia", shopDia);
		items.put("etcShop", etcShop);
		items.put("etcDegg", etcDegg);
		items.put("etcWipe", etcWipe);
		items.put("warpShop", warpShop);
		items.put("warpEnd", warpEnd);
	}
	
	public static List<String> getItemLore(String key) {
		if(items.containsKey(key)) {
			return items.get(key);
		} else {
			return null;
		}
	}
	
	public static Collection<List<String>> getAllLores(){
		return items.values();
	}
	
}
