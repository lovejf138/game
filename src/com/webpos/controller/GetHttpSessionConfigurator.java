package com.webpos.controller;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpSession;
import javax.websocket.HandshakeResponse;
import javax.websocket.server.HandshakeRequest;
import javax.websocket.server.ServerEndpointConfig;

public class GetHttpSessionConfigurator extends ServerEndpointConfig.Configurator
{
    @Override
    public void modifyHandshake(ServerEndpointConfig config, 
                                HandshakeRequest request, 
                                HandshakeResponse response)
    {
    
    	Map<String,List<String>> maps = request.getHeaders();
    	
    	List<String> os = maps.get("origin");
    	if(os==null||os.size()<=0) {
    		return;
    	}
    	String orign = os.get(0);
    	System.out.println("origin:"+orign);
    	if(!orign.equals("http://eth-game.club"))//
    	{
    		return;
    	}
  
        HttpSession httpSession = (HttpSession)request.getHttpSession();
        config.getUserProperties().put(HttpSession.class.getName(),httpSession);
    }
}