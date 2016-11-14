package net.zhxm.frame.base.factory;

import org.springframework.jdbc.core.JdbcTemplate;


public interface Factory {
	
	public JdbcTemplate generateFactory();

}
