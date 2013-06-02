package ligangty.common.pagination.web.tag;

import java.util.Collection;

import ligangty.common.pagination.PageBean;

/**
 * Used to generate page items.
 * 
 * @author gli@redhat.com
 * 
 */
public class PaginationGenUtil {
	private static final PaginationGenUtil singleton = new PaginationGenUtil();

	public static final String LINE_SEPARATOR = System
			.getProperty("line.separator");

	private PaginationGenUtil() {
		// do nothing
	}

	public static final PaginationGenUtil getInstance() {
		return singleton;
	}

	/**
	 * Generates an anchor for html page items
	 * 
	 * @param pageIndex
	 * @param showText
	 * @return
	 */
	public String generatePageAnchor(int pageIndex, String showText) {
		return "<a href=\"#\" onclick=\"this.parentNode." + PageBean.PAGE_ATTR
				+ ".value=" + pageIndex + ";this.parentNode.submit()\"><span>"
				+ showText + "</span></a>" + LINE_SEPARATOR;
	}

	/**
	 * Generates the common html part for both items page style and down list
	 * page style
	 * 
	 * @param pageBean
	 * @return
	 */
	public String generateCommonPagingPart(PageBean pageBean) {
		StringBuffer htmlBuf = new StringBuffer();
		htmlBuf.append(
				"<span>Total Records: " + pageBean.getTotalRecords()
						+ "</span>").append(LINE_SEPARATOR);
		htmlBuf.append(
				"<span>page: " + pageBean.getCurrentPage() + "/"
						+ pageBean.getTotalPage() + "</span>").append(
				LINE_SEPARATOR);
		htmlBuf.append(
				"<span>records in page: " + pageBean.getPageSize() + "</span>")
				.append(LINE_SEPARATOR);

		htmlBuf.append(
				"<input type=\"hidden\" name=\"" + PageBean.PAGE_ATTR + "\"/>")
				.append(LINE_SEPARATOR);

		htmlBuf.append(
				"<input type=\"hidden\" name=\"" + PageBean.TOTALRECS_ATTR
						+ "\" value=\"" + pageBean.getTotalRecords() + "\" />")
				.append(LINE_SEPARATOR);

		htmlBuf.append(generateSearchingParams(pageBean));

		return htmlBuf.toString();
	}

	/**
	 * Generates the searching parameters as html parameters for the continuing
	 * searching
	 * 
	 * @param pageBean
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public String generateSearchingParams(PageBean pageBean) {
		StringBuffer htmlBuf = new StringBuffer();
		for (String param : pageBean.getSearchingParamsMap().keySet()) {
			Object value = pageBean.getSearchingParamsMap().get(param);
			if (value != null) {
				if (value instanceof String) {
					htmlBuf.append(
							"<input type=\"hidden\" name=\"" + param
									+ "\" value=\"" + value + "\" />").append(
							LINE_SEPARATOR);
				} else if (value instanceof String[]) {
					htmlBuf.append(generateMultiParams(param, (String[]) value));
				} else if (value instanceof Collection<?>) {
					Object[] vals = ((Collection<Object>) value).toArray();
					htmlBuf.append(generateMultiParams(param, vals));
				}
			}
		}

		return htmlBuf.toString();
	}

	/**
	 * Generates mutiple parameters in request parameters map.
	 * 
	 * @param param
	 * @param paramsValue
	 * @return
	 */
	private String generateMultiParams(String param, Object[] paramsValue) {
		StringBuffer htmlBuf = new StringBuffer();
		if (paramsValue.length == 1) {
			htmlBuf.append(
					"<input type=\"hidden\" name=\"" + param + "\" value=\""
							+ paramsValue[0].toString() + "\" />").append(
					LINE_SEPARATOR);
		} else if (paramsValue.length > 1) {
			htmlBuf.append(
					"<select name=\""
							+ param
							+ "\" style=\"display:none\" multiple=\"multiple\">")
					.append(LINE_SEPARATOR);
			for (Object val : paramsValue) {
				htmlBuf.append(
						"<option selected=\"selected\" value=\""
								+ val.toString() + "\"></option>").append(
						LINE_SEPARATOR);
			}
			htmlBuf.append("</select>").append(LINE_SEPARATOR);
		}
		return htmlBuf.toString();
	}

}
