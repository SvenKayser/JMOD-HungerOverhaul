package com.jeffpeng.jmod.hungeroverhaul;

import iguanaman.hungeroverhaul.HungerOverhaul;
import iguanaman.hungeroverhaul.config.IguanaConfig;

import java.util.Map;

import net.minecraft.potion.Potion;

import com.jeffpeng.jmod.JMODPlugin;
import com.jeffpeng.jmod.JMODPluginContainer;
import com.jeffpeng.jmod.crafting.ToolUnbreaker;

import cpw.mods.fml.common.Loader;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;

public class Plugin extends JMODPlugin {

	public Plugin(JMODPluginContainer container) {
		super(container);
		// TODO Auto-generated constructor stub
	}
	
	public void addBuffs(Map<String, Potion> buffMap){
		if(Loader.isModLoaded("HungerOverhaul")) buffMap.put("wellfed", HungerOverhaul.potionWellFed);
	}
	
	@Override
	public void on(FMLPostInitializationEvent event) {
		if(Loader.isModLoaded("HungerOverhaul")){
			ToolUnbreaker.hoeUseCost = IguanaConfig.hoeToolDamageMultiplier;
		}


	}
	
	

}
