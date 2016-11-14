package org.haozf.basic.model;

import java.util.List;

/**
 * 分页对象
 * 
 * @author Administrator
 * 
 * @param <T>
 */
public class Pager<T> {

	public static int DEFAULT_PAGE_OFFSET = 1;
	public static int DEFAULT_PAGE_SIZE = 15;

	/**
	 * 分页的大小
	 */
	private int size;
	/**
	 * 分页的起始页
	 */
	private int offset;
	/**
	 * 总记录数
	 */
	private long total;
	/**
	 * 分页的数据
	 */
	private List<T> datas;

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}

	public int getOffset() {
		return offset;
	}

	public void setOffset(int offset) {
		this.offset = offset;
	}

	public long getTotal() {
		return total;
	}

	public void setTotal(long total) {
		this.total = total;
	}

	public List<T> getDatas() {
		return datas;
	}

	public void setDatas(List<T> datas) {
		this.datas = datas;
	}
	
	/**
	 * 默认输出当前分页标签 <div class="page">${page}</div>
	 */
	@Override
	public String toString() {
		return new Page<T>(this).toString();
	}
	
}
