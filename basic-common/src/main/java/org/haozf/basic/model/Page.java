package org.haozf.basic.model;

public class Page<T> {
	
	public static final int[] ARR_PAGE_SIZE = new int[] {10, 15, 20};

	private Pager<T> pager;

	/** 页码,从1开始 */
	private int pageIndex;

	/** 每页多少行 */
	private int pageSize;

	/** 数据总行数 */
	private int totalCount = 0;

	private int pageCount;

	private boolean hasPageSize = true;

	private int first;// 首页索引

	private int last;// 尾页索引

	private int prev;// 上一页索引

	private int next;// 下一页索引

	private static final int length = 7;// 显示页面长度

	private static final int slider = 1;// 前后显示页面长度

	private String funcName = "_gotPage"; // 设置点击页码调用的js函数名称，默认为page，在一页有多个分页对象时使用。

	public Page() {

	}

	public Page(Pager<T> pager) {
		this.pager = pager;
		this.pageIndex = pager.getOffset();
		this.pageSize = pager.getSize();
		this.totalCount = (int)pager.getTotal();
		this.pageCount = totalCount / pageSize + (totalCount % pageSize == 0 ? 0 : 1);
	}

	public Pager<T> getPager() {
		return pager;
	}

	public void setPager(Pager<T> pager) {
		this.pager = pager;
	}

	public int getPageIndex() {
		if (pageIndex < 1)
			pageIndex = 1;
		if (pageSize < 1)
			pageSize = Pager.DEFAULT_PAGE_SIZE;
		return pageIndex;
	}

	public void setPageIndex(int pageIndex) {
		this.pageIndex = pageIndex;
	}

	public int getPageSize() {
		if (pageSize == 0)
			pageSize = Pager.DEFAULT_PAGE_SIZE;
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
		pageCount = totalCount / pageSize + (totalCount % pageSize == 0 ? 0 : 1);
	}

	public int getPageCount() {
		return pageCount;
	}

	public void setPageCount(int pageCount) {
		this.pageCount = pageCount;
	}

	public boolean isHasPageSize() {
		return hasPageSize;
	}

	public void setHasPageSize(boolean hasPageSize) {
		this.hasPageSize = hasPageSize;
	}

	/**
	 * 是否是第一页
	 * 
	 * @return boolean
	 */
	public boolean getFirstPage() {
		if (pageCount > 0 && getPageIndex() != 1) {
			return true;
		} else {
			return false;
		}

	}

	/**
	 * 是否有上一页
	 * 
	 * @return boolean
	 */
	public boolean getHasPrev() {
		if (getPageIndex() > 1)
			return true;

		return false;
	}

	/**
	 * 是否是最后一页
	 * 
	 * @return boolean
	 */
	public boolean getLastPage() {
		if (pageIndex >= pageCount)
			return false;
		return true;
	}

	/**
	 * 是否有下一页
	 * 
	 * @return boolean
	 */
	public boolean getHasNext() {
		if (pageIndex > (pageCount - 1)) {
			return false;
		}
		return true;
	}

	/**
	 * 初始化参数
	 */
	public void initialize() {

		// 1
		this.first = 1;

		// System.out.println("initialize pageCount="+pageCount);
		// System.out.println("initialize pageSize="+pageSize);
		// System.out.println("initialize DEFAULT_PAGE_SIZE="+DEFAULT_PAGE_SIZE);
		// System.out.println("initialize first="+first);

		this.last = (int) (getTotalCount() / (this.pageSize < 1 ? Pager.DEFAULT_PAGE_SIZE : this.pageSize) + first - 1);

		// System.out.println("this.last="+last);

		if (this.getTotalCount() % this.pageSize != 0 || this.last == 0) {
			this.last++;
		}

		if (this.last < this.first) {
			this.last = this.first;
		}

		if (this.pageIndex <= 1) {
			this.pageIndex = this.first;
		}

		if (this.pageIndex >= this.last) {
			this.pageIndex = this.last;
		}

		if (this.pageIndex < this.last - 1) {
			this.next = this.pageIndex + 1;
		} else {
			this.next = this.last;
		}

		if (this.pageIndex > 1) {
			this.prev = this.pageIndex - 1;
		} else {
			this.prev = this.first;
		}

		// 2
		if (this.pageIndex < this.first) {// 如果当前页小于首页
			this.pageIndex = this.first;
		}

		if (this.pageIndex > this.last) {// 如果当前页大于尾页
			this.pageIndex = this.last;
		}

	}

	/**
	 * 默认输出当前分页标签 <div class="page">${page}</div>
	 */
	@Override
	public String toString() {

		initialize();

		StringBuilder sb = new StringBuilder();
		sb.append("");

		// <div class="pagination">
		// <ul>
		// <li><a href="#">首页</a></li>
		// <li><a href="#">上一页</a></li>
		// <li><a href="#">1</a></li>
		// <li><a href="#">2</a></li>
		// <li class="disabled"><a href="#">3</a></li>
		// <li class="active"><a href="#">4</a></li>
		// <li><a href="#">5</a></li>
		// <li><a href="#">下一页</a></li>
		// <li><a href="#">末页</a></li>
		// </ul>
		// <ul class="text">
		// <li>
		// 共500条，
		// 每页<select name="pageSize" onchange="page(1,this.value)">
		// <option value="10">10</option>
		// <option value="15">15</option>
		// <option value="20">20</option>
		// </select>条，
		// 当前 5 / 100 页
		// <input id="page" name="page" type="hidden" value="5"/>
		// </li>
		// </ul>
		// </div>

		sb.append("\n<div class=\"pagination\">");
		sb.append("\n<ul>");

		if (!getFirstPage()) {// 如果是首页
			sb.append("\n<li class=\"disabled\"><a href=\"javascript:;\">首页</a></li>");
			sb.append("\n<li class=\"disabled\"><a href=\"javascript:;\">上一页</a></li>");
			sb.append("");
			sb.append("");
			sb.append("");
		} else {
			sb.append("\n<li><a href=\"javascript:" + funcName + "(1," + pageSize + ");\">首页</a></li>");
			sb.append("\n<li><a href=\"javascript:" + funcName + "(" + prev + "," + pageSize + ");\">上一页</a></li>");
			sb.append("");
			sb.append("");
			sb.append("");
		}

		int begin = getPageIndex() - (length / 2);

		if (begin < first) {
			begin = first;
		}

		int end = begin + length - 1;

		if (end >= last) {
			end = last;
			begin = end - length + 1;
			if (begin < first) {
				begin = first;
			}
		}

		if (begin > first) {
			int i = 0;
			for (i = first; i < first + slider && i < begin; i++) {
				sb.append("\n<li><a href=\"javascript:" + funcName + "(" + i + "," + pageSize + ");\">" + (i + 1 - first) + "</a></li>");
				sb.append("");
				sb.append("");
			}
			if (i < begin) {
				sb.append("\n<li><a href=\"javascript:;\">...</a></li>");
			}
		}

		for (int i = begin; i <= end; i++) {
			if (i == getPageIndex()) {
				sb.append("\n<li class=\"active\"><a href=\"javascript:;\">" + (i + 1 - first) + "</a></li>");
				sb.append("");
				sb.append("");

			} else {
				sb.append("\n<li><a href=\"javascript:" + funcName + "(" + i + "," + pageSize + ");\">" + (i + 1 - first) + "</a></li>");
				sb.append("");
				sb.append("");
			}
		}

		if (last - end > slider) {
			sb.append("\n<li><a href=\"javascript:;\">...</a></li>");
			end = last - slider;
		}

		for (int i = end + 1; i <= last; i++) {
			sb.append("\n<li><a href=\"javascript:" + funcName + "(" + i + "," + pageSize + ");\">" + (i + 1 - first) + "</a></li>");
			sb.append("");
			sb.append("");
		}

		if (getPageIndex() == last) {
			sb.append("\n<li class=\"disabled\"><a href=\"javascript:;\">下一页</a></li>");
			sb.append("\n<li class=\"disabled\"><a href=\"javascript:;\">末页</a></li>");
			sb.append("");
			sb.append("");
			sb.append("");
		} else {
			sb.append("\n<li><a href=\"javascript:" + funcName + "(" + next + "," + pageSize + ");\">下一页</a></li>");
			sb.append("\n<li><a href=\"javascript:" + funcName + "(" + last + "," + pageSize + ");\">末页</a></li>");
			sb.append("");
			sb.append("");
			sb.append("");
		}
		sb.append("\n</ul>");

		// 信息展示
		sb.append("\n<ul class=\"text\">");
		sb.append("\n<li>");
		sb.append("\n共" + totalCount + "条");
		sb.append("，每页");

		if (isHasPageSize()) {
			sb.append("\n<select name=\"page_item\" id=\"page_item\" onchange=\"" + funcName + "(1,this.value)\">");
			for (int i = 0; i < ARR_PAGE_SIZE.length; i++) {
				String sel = "";
				if (ARR_PAGE_SIZE[i] == pageSize) {
					sel = "selected";
				}
				sb.append("\n<option value=\"" + ARR_PAGE_SIZE[i] + "\" " + sel + ">" + ARR_PAGE_SIZE[i] + "</option>");
			}
			sb.append("\n</select>");
		} else {
			sb.append(pageSize);
		}
		sb.append("\n条，当前 " + getPageIndex() + "/" + last + " 页");
		sb.append("");
		sb.append("\n</li>");
		sb.append("\n</ul>");

		sb.append("\n<input id=\"page\" name=\"page\" type=\"hidden\" value=\"" + getPageIndex() + "\"/>");
		sb.append("\n<input id=\"pageSize\" name=\"pageSize\" type=\"hidden\" value=\"" + pageSize + "\"/>");
		sb.append("\n<input id=\"orderProperty\" name=\"orderProperty\" type=\"hidden\" value=\"" + SystemContext.getOrder() + "\"/>");
		sb.append("\n<input id=\"orderDirection\" name=\"orderDirection\" type=\"hidden\" value=\"" + SystemContext.getSort() + "\"/>");
		sb.append("");
		sb.append("\n<script type='text/javascript'>");
		sb.append("\nfunction " + funcName + "(n,s){");
		sb.append("\n$(\"#page\").val(n);");
		sb.append("\n$(\"#pageSize\").val(s);");
		sb.append("\n$(\"form:last\").submit();");
		sb.append("\n}");
		sb.append("\n</script>");

		sb.append("\n</div>");
		sb.append("");

		return sb.toString();
	}

}
