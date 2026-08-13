package malloc.hongikv1.main;


import org.bukkit.Bukkit;
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
		getCommand("송금").setExecutor(new Command());
		getCommand("인출").setExecutor(new Command());
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
	
	
}
	
	
