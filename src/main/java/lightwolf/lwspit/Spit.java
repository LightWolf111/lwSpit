package lightwolf.lwspit;
import java.util.Iterator;
import java.util.List;
import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.util.Vector;

public class Spit implements CommandExecutor {
    private final JavaPlugin plugin;

    public Spit(JavaPlugin plugin) {
        this.plugin = plugin;
    }

    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage("Эта команда доступна только игрокам.");
            return true;
        } else {
            Player player = (Player)sender;
            FileConfiguration config = this.plugin.getConfig();
            double spitDistance = config.getDouble("spit.spitDistance", 2.0D);
            Location eyeLocation = player.getEyeLocation();
            Vector direction = eyeLocation.getDirection();
            Location location = eyeLocation.add(direction.multiply(spitDistance));
            Entity spitmonster = player.getWorld().spawnEntity(location, EntityType.LLAMA_SPIT);
            spitmonster.setVelocity(direction);
            spitmonster.getWorld().playSound(spitmonster.getLocation(), Sound.ENTITY_LLAMA_SPIT, 1.0F, 1.0F);
            List<Entity> nearbyEntities = spitmonster.getNearbyEntities(0.5D, 0.5D, 0.5D);
            Iterator iterator = nearbyEntities.iterator();

            while(iterator.hasNext()) {
                Entity entity = (Entity)iterator.next();
                if (entity instanceof Player && entity != player) {
                    Player targetPlayer = (Player)entity;
                    String hitTargetMessage = config.getString("spit.hitTarget");
                    hitTargetMessage = hitTargetMessage.replace("%player%", player.getName());
                    targetPlayer.sendMessage(Coloriser.coloriser(hitTargetMessage));
                    String hitPlayerMessage = config.getString("spit.hitPlayer");
                    hitPlayerMessage = hitPlayerMessage.replace("%target%", targetPlayer.getName());
                    player.sendMessage(Coloriser.coloriser(hitPlayerMessage));
                    break;
                }
            }

            return true;
        }
    }
}
