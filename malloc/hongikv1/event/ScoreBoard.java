package malloc.hongikv1.event;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.scoreboard.Criteria;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.ScoreboardManager;

import malloc.hongikv1.config.ConfigMain;

public class ScoreBoard {
	
	public static void setScoreboard(PlayerJoinEvent e) {
		ScoreboardManager manager = Bukkit.getScoreboardManager();
		Scoreboard board = manager.getNewScoreboard();
		
		Objective obj = board.registerNewObjective("Title", Criteria.DUMMY, "=======홍익인간=======");
		obj.setDisplaySlot(DisplaySlot.SIDEBAR);
		
		obj.getScore("돈").setScore(ConfigMain.getMoney(e.getPlayer().getName()));
		e.getPlayer().setScoreboard(board);
	}
	public static void reload(Player p) {
		ScoreboardManager manager = Bukkit.getScoreboardManager();
		Scoreboard board = manager.getNewScoreboard();
		
		Objective obj = board.registerNewObjective("Title", Criteria.DUMMY, "=======홍익인간=======");
		obj.setDisplaySlot(DisplaySlot.SIDEBAR);
		
		obj.getScore("돈").setScore(ConfigMain.getMoney(p.getName()));
		p.setScoreboard(board);
	}
	public static void reload(String p) {
		ScoreboardManager manager = Bukkit.getScoreboardManager();
		Scoreboard board = manager.getNewScoreboard();
		
		Objective obj = board.registerNewObjective("Title", Criteria.DUMMY, "=======홍익인간=======");
		obj.setDisplaySlot(DisplaySlot.SIDEBAR);
		
		obj.getScore("돈").setScore(ConfigMain.getMoney(p));
		Bukkit.getPlayer(p).setScoreboard(board);
	}
}
