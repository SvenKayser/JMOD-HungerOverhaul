package com.jeffpeng.jmod.hungeroverhaul;

import iguanaman.hungeroverhaul.HungerOverhaul;
import iguanaman.hungeroverhaul.config.IguanaConfig;

import java.util.Map;

import net.minecraft.entity.passive.EntityCow;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.potion.Potion;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.player.EntityInteractEvent;

import com.jeffpeng.jmod.JMODPlugin;
import com.jeffpeng.jmod.JMODPluginContainer;
import com.jeffpeng.jmod.crafting.ToolUnbreaker;
import com.jeffpeng.jmod.forgeevents.JMODAddBuffsEvent;
import com.jeffpeng.jmod.types.items.CoreBucket;

import cpw.mods.fml.common.Loader;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.eventhandler.EventPriority;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;

public class Plugin extends JMODPlugin {

	public Plugin(JMODPluginContainer container) {
		super(container);
		// TODO Auto-generated constructor stub
	}
	
	@SubscribeEvent
	public void addBuffs(JMODAddBuffsEvent e){
		if(Loader.isModLoaded("HungerOverhaul")) e.buffMap.put("wellfed", HungerOverhaul.potionWellFed);
	}
	
	@SubscribeEvent(priority=EventPriority.HIGH)
	public void milkCow(EntityInteractEvent event){
		if(!Loader.isModLoaded("HungerOverhaul")) return;
		EntityPlayer ep = event.entityPlayer;
		ItemStack is = event.entityPlayer.getCurrentEquippedItem();
		if(is == null) return;
		Item item = is.getItem();
		if(!(item instanceof CoreBucket) || !((CoreBucket)item).hasBucketsOf("milk")) return;
		
		if((event.target instanceof EntityCow) && is.getItemDamage() == 0){
			NBTTagCompound tags = event.target.getEntityData();
			if (tags.hasKey("Milked"))
			{
				System.out.println(tags.getInteger("Milked"));
				event.setCanceled(true);
				if (!ep.worldObj.isRemote)
				event.target.playSound("mob.cow.hurt",  0.4F, 0.9F);
				return;
			} else tags.setInteger("Milked", IguanaConfig.milkedTimeout * 60);
				
			ep.getCurrentEquippedItem().stackSize--;
			if (!ep.inventory.addItemStackToInventory(((CoreBucket)item).getBucketOf("milk")))
            {
                ep.dropPlayerItemWithRandomChoice(((CoreBucket)item).getBucketOf("milk"), false);
            }
		}
	}
	

	
	@Override
	public void on(FMLPostInitializationEvent event) {
		if(Loader.isModLoaded("HungerOverhaul")) ToolUnbreaker.hoeUseCost = IguanaConfig.hoeToolDamageMultiplier;
	}
	
	

}
