package lightwolf.lwspit;
import org.bukkit.plugin.java.JavaPlugin;

public final class LwSpit extends JavaPlugin {
    public void onEnable() {
        this.getCommand("spit").setExecutor(new Spit(this));
        this.getConfig().options().copyDefaults(true);
        this.saveConfig();
        this.getLogger().info("§e╔══════════════════════════════════");
        this.getLogger().info("§e╠ §alwSpit");
        this.getLogger().info("§e╠ §aПлагин включился!");
        this.getLogger().info("§e╚══════════════════════════════════");
    }

    public void onDisable() {
        this.saveDefaultConfig();
        this.getLogger().info("§c╔══════════════════════════════════");
        this.getLogger().info("§c╠ §clwSpit");
        this.getLogger().info("§c╠ §cПлагин выключился!");
        this.getLogger().info("§c╚══════════════════════════════════");
    }
}