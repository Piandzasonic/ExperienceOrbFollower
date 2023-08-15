package vexsmp.plugin.Listeners;

import org.bukkit.Location;
import org.bukkit.entity.Entity;
import org.bukkit.entity.ExperienceOrb;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;

import vexsmp.plugin.Main;

public class EntityDeathListener implements Listener{

	private Main plugin = Main.getInstance();
	
	@EventHandler
	public void onEntityDeath(EntityDeathEvent event) {
		LivingEntity entity = event.getEntity();
		Player killer = entity.getKiller();
		
		if(killer != null && killer instanceof Player) {
			new BukkitRunnable() {
				@Override
				public void run() {
					for (Entity nearbyEntity : killer.getNearbyEntities(10, 10, 10)) {
                        if (nearbyEntity instanceof ExperienceOrb) {
                            ExperienceOrb orb = (ExperienceOrb) nearbyEntity;

                            Vector velocity = calculateVelocity(orb.getLocation(), killer.getLocation(), 0.3);
                            orb.setVelocity(velocity);
                        }
                    }
				}
			}.runTaskTimer(plugin, 0L, 2L);
		}
	}
	
	private Vector calculateVelocity(Location from, Location to, double speed) {
		Vector direction = to.toVector().subtract(from.toVector());
		if(direction.lengthSquared() == 0) {
			return direction;
		}
        return direction.normalize().multiply(speed);
    }
}
