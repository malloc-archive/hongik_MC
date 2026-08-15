package malloc.hongikv1.shop;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.WorldCreator;
import org.bukkit.boss.DragonBattle;
import org.bukkit.entity.EnderCrystal;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerInteractEvent;

import malloc.hongikv1.config.ConfigMain;

public class ShopMain implements Listener {

	public void openMenu(InventoryClickEvent e) {
		e.getWhoClicked().openInventory(Menu.getMenu());
	}

	public void openMenu(PlayerInteractEvent e) {
		e.getPlayer().openInventory(Menu.getMenu());
	}

	@EventHandler
	public void menuClick(InventoryClickEvent e) {
		if (e.getCurrentItem() == null) {
			return;
		}
		if (e.getView().getTitle() == "메뉴") {
			e.setCancelled(true);
			if (e.getCurrentItem() == null) {

			} else {
				if (e.getCurrentItem().getItemMeta().getLore() == null) {

				} else {
					if (e.getCurrentItem().getItemMeta().getLore().equals(Lore.getItemLore("shop"))) {
						e.getWhoClicked().openInventory(Menu.getShop());
					}
					if (e.getCurrentItem().getItemMeta().getLore().equals(Lore.getItemLore("etcShop"))) {
						e.getWhoClicked().openInventory(Menu.getetc());
					}
				}

			}
		}
		if (e.getView().getTitle() == "광물 상점") {
			e.setCancelled(true);
			if (e.getCurrentItem().getItemMeta().getLore() == null) {

			} else {
				if (e.getCurrentItem().getItemMeta().getLore().equals(Lore.getItemLore("shopDia"))) {
					if (e.getClick().equals(ClickType.LEFT)) {
						if (e.getWhoClicked().getInventory().contains(Material.DIAMOND)) {
							e.getWhoClicked().getInventory()
									.getItem(e.getWhoClicked().getInventory().first(Material.DIAMOND))
									.setAmount(e.getWhoClicked().getInventory()
											.getItem(e.getWhoClicked().getInventory().first(Material.DIAMOND))
											.getAmount() - 1);
							ConfigMain.deposit((Player) e.getWhoClicked(), 10000);
						}
					} else if (e.getClick().equals(ClickType.SHIFT_LEFT)) {
						int amount = 0;

						for (int key : e.getWhoClicked().getInventory().all(Material.DIAMOND).keySet()) {
							amount += e.getWhoClicked().getInventory().all(Material.DIAMOND).get(key).getAmount();
						}

						if (amount >= 64) {
							e.getWhoClicked().getInventory()
									.getItem(e.getWhoClicked().getInventory().first(Material.DIAMOND))
									.setAmount(e.getWhoClicked().getInventory()
											.getItem(e.getWhoClicked().getInventory().first(Material.DIAMOND))
											.getAmount() - 64);
							ConfigMain.deposit((Player) e.getWhoClicked(), 64 * 10000);
						} else {
							e.getWhoClicked().getInventory()
									.getItem(e.getWhoClicked().getInventory().first(Material.DIAMOND))
									.setAmount(e.getWhoClicked().getInventory()
											.getItem(e.getWhoClicked().getInventory().first(Material.DIAMOND))
											.getAmount() - amount);
							ConfigMain.deposit((Player) e.getWhoClicked(), amount * 10000);
						}
					}

				}
			}
		}
		if (e.getView().getTitle() == "기타 상점") {
			e.setCancelled(true);
			if (e.getCurrentItem() == null) {

			} else {
				if (e.getCurrentItem().getItemMeta().getLore() == null) {

				} else {
					if (e.getCurrentItem().getItemMeta().getLore().equals(Lore.getItemLore("etcDegg"))) {
						if (ConfigMain.getMoney(e.getWhoClicked().getName()) >= 2000000) {
							ConfigMain.withdraw((Player) e.getWhoClicked(), 2000000);

							World world = Bukkit.getWorld("world_the_end");
							Location c1 = new Location(world, 0.5, 65, 3.5);
							Location c2 = new Location(world, -2.5, 65, 0.5);
							Location c3 = new Location(world, 0.5, 65, -2.5);
							Location c4 = new Location(world, 3.5, 65, 0.5);

							EnderCrystal cry1 = (EnderCrystal) world.spawnEntity(c1, EntityType.END_CRYSTAL);
							EnderCrystal cry2 = (EnderCrystal) world.spawnEntity(c2, EntityType.END_CRYSTAL);
							EnderCrystal cry3 = (EnderCrystal) world.spawnEntity(c3, EntityType.END_CRYSTAL);
							EnderCrystal cry4 = (EnderCrystal) world.spawnEntity(c4, EntityType.END_CRYSTAL);

							cry1.setShowingBottom(false);
							cry2.setShowingBottom(false);
							cry3.setShowingBottom(false);
							cry4.setShowingBottom(false);

							List<EnderCrystal> crystals = new ArrayList<>();
							crystals.add(cry1);
							crystals.add(cry2);
							crystals.add(cry3);
							crystals.add(cry4);

							DragonBattle battle = world.getEnderDragonBattle();

							if (battle != null) {
								battle.initiateRespawn(crystals);
								Bukkit.broadcastMessage(e.getWhoClicked().getName() + " 님이 엔더드래곤을 부활시켰습니다!");
							}

						}
					} else if (e.getCurrentItem().getItemMeta().getLore().equals(Lore.getItemLore("etcWipe"))) {
						if (ConfigMain.getMoney(e.getWhoClicked().getName()) >= 10000000) {
							ConfigMain.withdraw(e.getWhoClicked().getName(), 10000000);
							World end = Bukkit.getWorld("world_the_end");
							if (end == null) {
								Bukkit.getConsoleSender().sendMessage("couldn't find world \"world_the_end\"");
								return;
							}
							World overworld = Bukkit.getWorld("world");
							if (overworld == null) {
								Bukkit.getConsoleSender().sendMessage("couldn't find world \"overworld\"");
								return;
							}
							for (Player player : new ArrayList<>(end.getPlayers())) {
								player.teleport(player.getRespawnLocation());
							}
							File worldFolder = end.getWorldFolder();
							if (!Bukkit.unloadWorld(end, false)) {
								Bukkit.getConsoleSender().sendMessage("failed to unload the world \"world_the_end\"");
								return;
							}
							try {
								deleteDirectory(worldFolder);
							} catch (IOException e1) {
								e1.printStackTrace();
								return;
							}
							World newEnd = new WorldCreator("world_the_end").environment(World.Environment.THE_END)
									.generateStructures(true).createWorld();

							if (newEnd == null) {
								Bukkit.getConsoleSender().sendMessage("failed to generate world_the_end");
								return;
							}

							Bukkit.getConsoleSender().sendMessage("World wiped successfully");
							Bukkit.broadcastMessage(e.getWhoClicked().getName() + " 님이 엔드월드를 초기화 했습니다!");
						}

					}
				}

			}
		}
	}

	@EventHandler
	public void handClick(PlayerInteractEvent e) {
		if (e.getAction().equals(Action.LEFT_CLICK_AIR) || e.getAction().equals(Action.LEFT_CLICK_BLOCK)
				|| e.getAction().equals(Action.RIGHT_CLICK_AIR) || e.getAction().equals(Action.RIGHT_CLICK_BLOCK)) {
			if (e.getItem() == null) {
				return;
			}
			if (e.getItem().getItemMeta().getLore().equals(Lore.getItemLore("menu"))) {
				openMenu(e);
			}
		}
	}

	public static void giveMenu(Player p) {
		p.getInventory().addItem(Menu.getClock());
	}

	private void deleteDirectory(File directory) throws IOException {
		if (!directory.exists()) {
			return;
		}

		File[] files = directory.listFiles();

		if (files == null) {
			throw new IOException("디렉터리를 읽을 수 없습니다: " + directory);
		}

		for (File file : files) {
			if (file.isDirectory()) {
				deleteDirectory(file);
			} else if (!file.delete()) {
				throw new IOException("파일 삭제 실패: " + file);
			}
		}

		if (!directory.delete()) {
			throw new IOException("디렉터리 삭제 실패: " + directory);
		}
	}

}
