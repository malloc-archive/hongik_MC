package malloc.hongikv1.command;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import malloc.hongikv1.config.ConfigMain;
import malloc.hongikv1.main.Main;
import malloc.hongikv1.shop.ShopMain;
import malloc.hongikv1.thread.WebhookThread;

public class Command implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, org.bukkit.command.Command command, String label, String[] args) {

		if (!(sender instanceof Player)) {
			return true;
		}

		Player player = (Player) sender;

		Bukkit.getConsoleSender().sendMessage(command.getName());

		if (command.getName().equalsIgnoreCase("송금op")) {
			if (!sender.isOp()) {
				player.sendMessage(ChatColor.RED + "You don't have OP!");
				return false;
			}
			if (args.length == 3) {
				String fname = args[0];
				String tname = args[1];
				int amount = Integer.parseInt(args[2]);
				int curr = ConfigMain.getMoney(fname);

				if (amount < 0) {
					sender.sendMessage(ChatColor.RED + "어딜");
					return false;
				}

				if (amount <= curr) {
					ConfigMain.songgem(fname, tname, amount);
				} else {
					sender.sendMessage(ChatColor.RED + "돈이 충분하지 않습니다!");
				}
			}
		} else if (command.getName().equalsIgnoreCase("입금")) {
			if (!sender.isOp()) {
				player.sendMessage(ChatColor.RED + "You don't have OP!");
				return false;
			}
			if (args.length == 2) {
				String name = args[0];
				ConfigMain.deposit(name, Integer.parseInt(args[1]));
			} else {

			}
		} else if (command.getName().equalsIgnoreCase("인출")) {
			if (!sender.isOp()) {
				player.sendMessage(ChatColor.RED + "You don't have OP!");
				return false;
			}
			if (args.length == 2) {
				String name = args[0];
				ConfigMain.withdraw(name, Integer.parseInt(args[1]));
			} else {

			}
		} else if (command.getName().equalsIgnoreCase("송금")) {
			if (args.length == 2) {

				String name = args[0];
				int amount = Integer.parseInt(args[1]);
				int curr = ConfigMain.getMoney(player.getName());

				if (amount < 0) {
					sender.sendMessage(ChatColor.RED + "어딜");
					return false;
				}

				if (amount <= curr) {
					ConfigMain.songgem(sender.getName(), name, amount);
					sender.sendMessage(ChatColor.YELLOW + "성공적으로 송금되었습니다!  잔액:" + Integer.toString(ConfigMain.getMoney(sender.getName())));
				} else {
					sender.sendMessage(ChatColor.RED + "돈이 충분하지 않습니다!");
				}

			}
		} else if (command.getName().equalsIgnoreCase("의견제출")) {	
			
			if(args.length >= 1) {
				String formatted = "";
				for(int i = 0; i < args.length; i++) {
					formatted += args[i] + " ";
				}
				WebhookThread web = new WebhookThread(formatted);
				web.start();
			}
		} else if(command.getName().equalsIgnoreCase("메뉴")) {
			ShopMain.giveMenu(player);
		} else if(command.getName().equalsIgnoreCase("spawn")) {
			if(ConfigMain.getMoney(player.getName()) >= 1000) {
				ConfigMain.withdraw(player, 1000);
				player.teleport(player.getRespawnLocation());
			} else {
				player.sendMessage(ChatColor.RED + "돈이 부족합니다!");
			}
		} else if(command.getName().equalsIgnoreCase("tpa")) {
			if(ConfigMain.getMoney(player.getName()) < 10000) {
				player.sendMessage(ChatColor.RED + "돈이 부족합니다!");
				return false;
			}
			if(args.length == 1) {
				if(!Main.isOnline(args[0])) {
					player.sendMessage(ChatColor.RED + "해당 플레이어는 온라인이 아닙니다!");
					return false;
				}
				Main.getTpa().addRequest(player.getName(), args[0]);
				
			}
		} else if(command.getName().equalsIgnoreCase("tpaccept")) {
			Main.getTpa().tpAccept(player.getName());
		}

		return false;
	}

}
