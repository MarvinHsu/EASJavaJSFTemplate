package com.hsuforum.easjavatemplate.ws.client;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

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
		

		UserWSVO userWSVO;
    	
    	final HttpHeaders headers = new HttpHeaders();
        headers.set("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/75.0.3770.100 Safari/537.36");
    	
        final HttpEntity<String> entity = new HttpEntity<String>(headers);
    	
        String targetUri=this.getPortalWSURI()+"/userResource?systemCode={systemCode}&account={account}";
    	RestTemplate restTemplate = new RestTemplate();
    	
    	try{
    		ResponseEntity<UserWSVO> userWSVOResponseEntity=restTemplate.exchange(targetUri, HttpMethod.GET,entity,UserWSVO.class, systemCode, account);
    		userWSVO=userWSVOResponseEntity.getBody();
    	}catch(Exception e) {
    		e.printStackTrace();
    		userWSVO = null;
    	}
    	
    	return userWSVO;
    	
	}
	
	public ModuleWSVO2[] findModuleBySystem(String systemCode){

    	final HttpHeaders headers = new HttpHeaders();
        headers.set("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/75.0.3770.100 Safari/537.36");
    	
        final HttpEntity<String> entity = new HttpEntity<String>(headers);
    	
        String targetUri=this.getPortalWSURI()+"/moduleResource?systemCode={systemCode}";
    	RestTemplate restTemplate = new RestTemplate();
    	
    	
    	ModuleWSVO2[] moduleWSVO2s;
    	try{
    		
    		ResponseEntity<ModuleWSVO2[]> userWSVOResponseEntity=restTemplate.exchange(targetUri, HttpMethod.GET,entity,ModuleWSVO2[].class, systemCode);
    		moduleWSVO2s=userWSVOResponseEntity.getBody();
    		
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
