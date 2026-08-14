package malloc.hongikv1.main;


import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import malloc.hongikv1.command.Command;
import malloc.hongikv1.config.ConfigMain;
import malloc.hongikv1.event.Join_Dis;

public class Main extends JavaPlugin{
	
	private static Main instance;
	
	@Override
	public void onEnable() {
		Bukkit.getConsoleSender().sendMessage("---------------------------\n ENABLED\n ---------------------------");
		
		instance = this;
		

		getCommand("입금").setExecutor(new Command());
		getCommand("송금op").setExecutor(new Command());
		getCommand("송금").setExecutor(new Command());
		getCommand("인출").setExecutor(new Command());
		getCommand("의견제출").setExecutor(new Command());
		getServer().getPluginManager().registerEvents(new Join_Dis(), this);
		
		getConfig().options().copyDefaults();
		saveDefaultConfig();
		
		ConfigMain.setup();
		ConfigMain.get().options().copyDefaults(true);
		
		
	}
	
	@Override
	public void onDisable() {
		Bukkit.getConsoleSender().sendMessage("---------------------------\n DISABLED\n ---------------------------");
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
	
	
