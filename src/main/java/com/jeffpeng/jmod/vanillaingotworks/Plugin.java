package com.jeffpeng.jmod.vanillaingotworks;

import java.util.ArrayList;
import java.util.Map;

import com.jeffpeng.jmod.JMODPlugin;
import com.jeffpeng.jmod.JMODPluginContainer;

public class Plugin extends JMODPlugin {

	public Plugin(JMODPluginContainer container) {
		super(container);
		// TODO Auto-generated constructor stub
	}
	
	public void initConfig(Map<String,Object> config){
		config.put("vanillaingotworks:alloymap", new ArrayList<AlloyDescriptor>());
	}

}
