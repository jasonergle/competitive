package com.erglesoft.startup;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.apache.log4j.Logger;

public class AppConfig {
	Logger  log = Logger.getLogger(AppConfig.class);
	private String dbHost;
	private String dbUser;
	private String dbPassword;
	private String appName;
	private Boolean showSql;
	
	public AppConfig() {
		Properties configRead = new Properties();
		InputStream is = null;
		try {
			is = new FileInputStream("/var/erglesoft/h2h/app.properties");
			configRead.load(is);
		} catch ( IOException e) {
			e.printStackTrace();
		}
		finally{
			try {
				is.close();
			} catch (IOException e) {
			}
		}

        dbHost = configRead.getProperty("dbhost");
        dbUser = configRead.getProperty("dbuser");
        dbPassword = configRead.getProperty("dbpassword");
        showSql = Boolean.valueOf(configRead.getProperty("dbshowsql", "true"));
        appName = configRead.getProperty("appname");
        log.info(this.toString());
	}

	public String getDbHost() {
		return dbHost;
	}

	public String getDbUser() {
		return dbUser;
	}

	public String getDbPassword() {
		return dbPassword;
	}

	public String getAppName() {
		return appName;
	}

	public Boolean getShowSql() {
		return showSql;
	}

	@Override
	public String toString() {
		return "AppConfig [dbHost=" + dbHost + ", dbUser=" + dbUser + ", dbPassword=" + dbPassword + ", appName=" + appName + ", showSql=" + showSql
				+ "]";
	}

}
