package net.zhxm.utils;

import java.io.File;
import java.io.FileReader;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public enum ProgramPropertiesUtil {
	INSTACE;
	Map<String, String> properties;

	private ProgramPropertiesUtil() {
		this.properties = new HashMap<String, String>(0);
		String basepath = Thread.currentThread().getContextClassLoader()
				.getResource("").toString();
		basepath = basepath.substring(6);
		Properties properties = new Properties();
		FileReader fileReader = null;
		try {
			fileReader = new FileReader(new File(basepath + "\\config\\"
					+ "program.properties"));
			properties.load(fileReader);
			String projectPath = properties.getProperty("projectPath");
			String qrcodeSize = properties.getProperty("qrcodeSize");
			String projectUrl = properties.getProperty("projectUrl");

			this.properties.put("basepath", basepath);
			this.properties.put("projectPath", projectPath);
			this.properties.put("qrcodeSize", qrcodeSize);
			this.properties.put("projectUrl", projectUrl);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public Map<String, String> getProperties() {
		return properties;
	}
}
