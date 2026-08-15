package malloc.hongikv1.shop;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class Menu {

	public static Inventory menuInv;
	public static Inventory shopInv;
	public static Inventory etcInv;
	public static ItemStack shop;
	public static ItemStack clock;
	public static ItemStack WIP;
	public static ItemStack shopDia;
	public static ItemStack etcShop;
	public static ItemStack etcDegg;
	public static ItemStack etcWipe;

	public static void setupMenu() {
		Lore.setupItems();
		setupShop();
		setupEtc();
		menuInv = Bukkit.createInventory(null, 45, "메뉴");

		shop = new ItemStack(Material.DIAMOND, 1);
		ItemMeta meta = shop.getItemMeta();
		meta.setItemName("상점");
		meta.setLore(Lore.getItemLore("shop"));
		shop.setItemMeta(meta);

		menuInv.setItem(20, shop);

		clock = new ItemStack(Material.CLOCK, 1);
		meta = clock.getItemMeta();
		meta.setItemName("메뉴");
		meta.setLore(Lore.getItemLore("menu"));
		clock.setItemMeta(meta);

		WIP = new ItemStack(Material.BEDROCK, 1);
		meta = WIP.getItemMeta();
		meta.setItemName("WIP");
		meta.setLore(Lore.getItemLore("WIP"));
		WIP.setItemMeta(meta);
		
		etcShop = new ItemStack(Material.NAME_TAG, 1);
		meta = etcShop.getItemMeta();
		meta.setItemName("기타 상점");
		meta.setLore(Lore.getItemLore("etcShop"));
		etcShop.setItemMeta(meta);

		menuInv.setItem(22, WIP);
		menuInv.setItem(24, etcShop);
		

	}

	public static void setupShop() {
		shopInv = Bukkit.createInventory(null, 45, "광물 상점");
		
		shopDia = new ItemStack(Material.DIAMOND, 1);
		ItemMeta meta = shopDia.getItemMeta();
		meta.setItemName("다이아몬드");
		meta.setLore(Lore.getItemLore("shopDia"));
		shopDia.setItemMeta(meta);
		
		shopInv.setItem(0, shopDia);
		
	}
	
	public static void setupEtc() {
		etcInv = Bukkit.createInventory(null, 45, "기타 상점");
		
		etcDegg = new ItemStack(Material.DRAGON_EGG, 1);
		ItemMeta meta = etcDegg.getItemMeta();
		meta.setItemName("엔더드래곤 부활");
		meta.setLore(Lore.getItemLore("etcDegg"));
		etcDegg.setItemMeta(meta);
		
		etcWipe = new ItemStack(Material.ENDER_EYE, 1);
		meta = etcWipe.getItemMeta();
		meta.setLore(Lore.getItemLore("etcWipe"));
		meta.setItemName("엔더월드 초기화");
		etcWipe.setItemMeta(meta);
		
		etcInv.setItem(0, etcDegg);
		etcInv.setItem(1, etcWipe);
	}

	public static Inventory getMenu() {
		return menuInv;
	}

	public static ItemStack getClock() {
		return clock;
	}
	
	public static Inventory getShop() {
		return shopInv;
	}
	public static Inventory getetc() {
		return etcInv;
	}
}
