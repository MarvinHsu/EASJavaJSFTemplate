package com.hsuforum.easjavatemplate.ws.client;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;

import com.hsuforum.easjavatemplate.ws.vo.ModuleWSVO2;
import com.hsuforum.easjavatemplate.ws.vo.UserWSVO;
/**
 * Get user info from portal 
 * Rest WS Client
 * @author Marvin
 *
 */
public class PortalClient {
	//Portal WS URI
	private String portalWSURI;
	
	public UserWSVO findUserById(String systemCode, String account){
		
		Client client = ClientBuilder.newClient();
    	
    	WebTarget target= client.target(this.getPortalWSURI()).path("userResource");
    	
    	target=target.queryParam("systemCode", systemCode);
    	target=target.queryParam("account", account);
    	
    	Invocation.Builder builder = target
    			.request(MediaType.APPLICATION_JSON)
    			.accept(MediaType.APPLICATION_JSON)
    			.header("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/75.0.3770.100 Safari/537.36");
    	UserWSVO userWSVO;
    	try{
    		userWSVO=builder.get(UserWSVO.class);
    	}catch(Exception e) {
    		e.printStackTrace();
    		userWSVO = null;
    	}
    	
    	return userWSVO;
    	
	}
	
	public ModuleWSVO2[] findModuleBySystem(String systemCode){

		Client client = ClientBuilder.newClient();
    	
    	WebTarget target= client.target(this.getPortalWSURI()).path("moduleResource");
    	
    	target=target.queryParam("systemCode", systemCode);
    	Invocation.Builder builder = target
    			.request(MediaType.APPLICATION_JSON)
    			.accept(MediaType.APPLICATION_JSON)
    			.header("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/75.0.3770.100 Safari/537.36");
    	ModuleWSVO2[] moduleWSVO2s;
    	try{
    		moduleWSVO2s = builder.get(ModuleWSVO2[].class);
    	}catch(Exception e) {
    		e.printStackTrace();
    		moduleWSVO2s = null;
    	}
    	
    	return moduleWSVO2s;
    	
	}
	/**
	 * @return the portalWSURI
	 */
	public String getPortalWSURI() {
		return portalWSURI;
	}
	/**
	 * @param portalWSURI the portalWSURI to set
	 */
	public void setPortalWSURI(String portalWSURI) {
		this.portalWSURI = portalWSURI;
	}
	
	
}
