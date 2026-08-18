package malloc.hongikv1.main;


import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import malloc.hongikv1.command.Command;
import malloc.hongikv1.config.ConfigMain;
import malloc.hongikv1.event.BotDetect;
import malloc.hongikv1.event.Chat;
import malloc.hongikv1.event.Join_Dis;
import malloc.hongikv1.shop.Menu;
import malloc.hongikv1.shop.ShopMain;
import malloc.hongikv1.thread.Tpa;

public class Main extends JavaPlugin{
	static Tpa thr;
	private static Main instance;
	
	@Override
	public void onEnable() {
		Bukkit.getConsoleSender().sendMessage("\n---------------------------\n ENABLED\n ---------------------------");
		
		instance = this;
		

		getCommand("입금").setExecutor(new Command());
		getCommand("송금op").setExecutor(new Command());
		getCommand("송금").setExecutor(new Command());
		getCommand("인출").setExecutor(new Command());
		getCommand("의견제출").setExecutor(new Command());
		getCommand("메뉴").setExecutor(new Command());
		getCommand("spawn").setExecutor(new Command());
		getCommand("tpa").setExecutor(new Command());
		getCommand("tpaccept").setExecutor(new Command());
		getCommand("칭호변경").setExecutor(new Command());
		getServer().getPluginManager().registerEvents(new Join_Dis(), this);
		getServer().getPluginManager().registerEvents(new Chat(), this);
		getServer().getPluginManager().registerEvents(new ShopMain(), this);
		getServer().getPluginManager().registerEvents(new BotDetect(), this);
		
		getConfig().options().copyDefaults();
		saveDefaultConfig();
		
		ConfigMain.setup();
		ConfigMain.get().options().copyDefaults(true);
		Menu.setupMenu();
		thr = new Tpa();
		thr.start();
	}
	
	public static Tpa getTpa() {
		return thr;
	}
	
	@Override
	public void onDisable() {
		Bukkit.getConsoleSender().sendMessage("\n---------------------------\n DISABLED\n ---------------------------");
	}
	
	public static Main getMain() {
		return instance;
	}
	
	public static boolean isOnline(String name) {
		for(Player p : Bukkit.getOnlinePlayers()) {
			if(p.getName().equals(name))
				return true;
		}
		return false;
	}
	
	public static boolean isOnline(Player name) {
		for(Player p : Bukkit.getOnlinePlayers()) {
			if(p.getName().equals(name.getName()))
				return true;
		}
		return false;
	}
}
	
	
