package com.lins.linslogapi.cinfig;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties("lins")
public class LinsApiProperty {
	
	private final Seguranca seguranca = new Seguranca();	

	public Seguranca getSeguranca() {
		return seguranca;
	}
	
	

	public static class Seguranca {

		private boolean enableHttps;
		
		public boolean isEnableHttps() {
			return enableHttps;
		}

		public void setEnableHttps(boolean enableHttps) {
			this.enableHttps = enableHttps;
		}

	}

}
