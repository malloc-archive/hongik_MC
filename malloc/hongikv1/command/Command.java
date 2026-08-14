package malloc.hongikv1.command;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import malloc.hongikv1.config.ConfigMain;
import malloc.hongikv1.sori.Anony;

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
			if (args.length == 1) {
				Anony.sendDiscord(args[0]);
			}
		}

		return false;
	}

}
