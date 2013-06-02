package ligangty.common.pagination.web.tag;

import static org.junit.Assert.*;
import ligangty.common.pagination.PageBean;

import org.junit.Test;

public class PaginationGenUtilTest {
	private PaginationGenUtil util = PaginationGenUtil.getInstance();

	@Test
	public void testGeneratePageAnchor() {
		String expectResult= "<a href=\"#\" onclick=\"this.parentNode." + PageBean.PAGE_ATTR
				+ ".value=1;this.parentNode.submit()\"><span>ShowMe</span></a>" + PaginationGenUtil.LINE_SEPARATOR;
		assertEquals(expectResult,util.generatePageAnchor(1, "ShowMe"));
	}
	
	@Test
	public void testGenerateCommonPagingPart(){
		PageBean bean = new PageBean();
		bean.setTotalRecords(1000L);
		bean.setCurrentPage(2);
		bean.setTotalPage(20);
		bean.setPageSize(50);
		bean.addSearchingParams("param", "paramValue");
		String expectedResult="<span>Total Records: 1000</span>"
				+ PaginationGenUtil.LINE_SEPARATOR + "<span>page: 2/20</span>"
				+ PaginationGenUtil.LINE_SEPARATOR + "<span>records in page: 50</span>"
				+ PaginationGenUtil.LINE_SEPARATOR + "<input type=\"hidden\" name=\"page\"/>"
				+ PaginationGenUtil.LINE_SEPARATOR + "<input type=\"hidden\" name=\"totalrecords\" value=\"1000\" />"
				+ PaginationGenUtil.LINE_SEPARATOR + "<input type=\"hidden\" name=\"param\" value=\"paramValue\" />"
				+ PaginationGenUtil.LINE_SEPARATOR;
		assertEquals(expectedResult,util.generateCommonPagingPart(bean));
	}
	
	@Test
	public void testGenerateSearchingParams(){
		PageBean bean = new PageBean();
		bean.addSearchingParams("param", "paramValue");
		String expectResult = "<input type=\"hidden\" name=\"param\" value=\"paramValue\" />"+PaginationGenUtil.LINE_SEPARATOR;
		assertEquals(expectResult,util.generateSearchingParams(bean));
	}
}
