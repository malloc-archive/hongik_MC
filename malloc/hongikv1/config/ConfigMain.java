package malloc.hongikv1.config;


import java.io.File;
import java.io.IOException;
import java.util.HashMap;

import org.bukkit.Bukkit;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerJoinEvent;

import malloc.hongikv1.event.ScoreBoard;
import malloc.hongikv1.main.Main;


public class ConfigMain {
	
	private static File file;
	private static FileConfiguration customFile;
	
	public static void setup() {
		file = new File(Main.getMain().getServer().getPluginManager().getPlugin("hongik_v1").getDataFolder(), "customconfig.yml");
		
		if(!file.exists()) {
			try {
				file.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
			
		}
		
		customFile = YamlConfiguration.loadConfiguration(file);
		if(!customFile.contains("nickname")) {
			customFile.addDefault("nickname", new HashMap<String, String>());
		}
	}
	public static Integer getMoney(String name) {
		return Integer.parseInt((String)customFile.get(name));
	}
	
	public static void registerAccount(PlayerJoinEvent e) {

		String name = e.getPlayer().getName();
		if(!customFile.contains(name)) {
			customFile.addDefault(name, Integer.toString(0));
			save();
		}
		
	
	}
	
	public static void registerNickname(String uuid, String nickName) {
		if(getNameMap().containsKey("uuid")) {
			
		} else {
			
		}
	}
	
	public static HashMap<String,String> getNameMap(){
		HashMap<String,String> nameMap = new HashMap<>();
		
		if(customFile.isConfigurationSection("nickname")) {
			ConfigurationSection section = customFile.getConfigurationSection("nickname");
			
			for(String key : section.getKeys(false)) {
				nameMap.put(key, (String)section.get(key));
			}
		
		}
		return nameMap;
	}
	
	public static void putName(String uuid, String name) {
	    customFile.set("nickname." + uuid, name);
	    save();
	}
	
	
	//Overload for String and Player type.
	public static void deposit(Player p, int amount) {
		customFile.set(p.getName(), Integer.toString(Integer.parseInt((String)customFile.get(p.getName())) + amount));
		save();
		if(Main.isOnline(p))
			ScoreBoard.reload(p);
	}
	public static void withdraw(Player p, int amount) {
		customFile.set(p.getName(), Integer.toString(Integer.parseInt((String)customFile.get(p.getName())) - amount));
		save();
		if(Main.isOnline(p))
			ScoreBoard.reload(p);
	}
	
	public static void deposit(String p, int amount) {
		customFile.set(p, Integer.toString(Integer.parseInt((String)customFile.get(p)) + amount));
		Bukkit.getConsoleSender().sendMessage("돈"+(String)customFile.get(p)+"수량"+Integer.toString(amount));
		save();
		if(Main.isOnline(p))
			ScoreBoard.reload(p);
	}
	public static void withdraw(String p, int amount) {
		customFile.set(p, Integer.toString(Integer.parseInt((String)customFile.get(p)) - amount));
		save();
		if(Main.isOnline(p))
			ScoreBoard.reload(p);
	}
	
	public static void songgem(String from, String to, int amount) {
		withdraw(from, amount);
		deposit(to, amount);
		save();
	}
	
	public static void save() {
		try {
			customFile.save(file);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public static FileConfiguration get() {
		return customFile;
	}
	public static void reload() {
		customFile = YamlConfiguration.loadConfiguration(file);
	}
}

