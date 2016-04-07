package ca.como89.myapi;

import org.bukkit.plugin.java.JavaPlugin;

public class MyApiPlugin extends JavaPlugin {

	@Override
	public void onEnable(){
		this.getLogger().info(this.getDescription().getName() + " by " + this.getDescription().getAuthors());
	}
}
