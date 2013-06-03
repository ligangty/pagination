package ligangty.common.pagination.web.tag;

import ligangty.common.pagination.PageBean;

/**
 * Items styled paging generation strategy class
 * 
 * @author gli@redhat.com
 * 
 */
public class ItemsStylePageGenStrategy implements PageGenStrategy {

	/**
	 * Items style paging generation strategy method
	 * 
	 * @param pageBean
	 * @param showPages
	 * @return
	 */
	@Override
	public String generatePaging(PageBean pageBean, int showPages) {

		StringBuffer htmlBuf = new StringBuffer();

		htmlBuf.append(PaginationGenUtil.getInstance()
				.generateCommonPagingPart(pageBean));

		if (pageBean.getCurrentPage() > 1) {
			htmlBuf.append(PaginationGenUtil.getInstance().generatePageAnchor(
					1, "first"));
			htmlBuf.append(PaginationGenUtil.getInstance().generatePageAnchor(
					pageBean.getPreviousPage(), "previous"));
		}

		int currentPageOffset = showPages / 2;
		int startPosition = 1;
		if (pageBean.getCurrentPage() - currentPageOffset > 0) {
			startPosition = pageBean.getCurrentPage() - currentPageOffset;
		}
		int endPosition = pageBean.getCurrentPage() + currentPageOffset;
		if (showPages % 2 == 0) {
			endPosition--;
		}
		if (endPosition > pageBean.getTotalPage()) {
			endPosition = pageBean.getTotalPage();
		}

		if (endPosition < showPages) {
			endPosition = showPages;
		}

		for (int pageInd = startPosition; pageInd >= startPosition
				&& pageInd <= endPosition; pageInd++) {
			if (pageInd == pageBean.getCurrentPage()) {
				htmlBuf.append("<strong>" + pageInd + "</strong>").append(
						PaginationGenUtil.LINE_SEPARATOR);
			} else {
				htmlBuf.append(PaginationGenUtil.getInstance()
						.generatePageAnchor(pageInd, "" + pageInd));
			}
		}

		if (pageBean.getCurrentPage() < pageBean.getTotalPage()) {
			htmlBuf.append(PaginationGenUtil.getInstance().generatePageAnchor(
					pageBean.getNextPage(), "next"));
			htmlBuf.append(PaginationGenUtil.getInstance().generatePageAnchor(
					pageBean.getTotalPage(), "last"));
		}

		return htmlBuf.toString();

	}

}
