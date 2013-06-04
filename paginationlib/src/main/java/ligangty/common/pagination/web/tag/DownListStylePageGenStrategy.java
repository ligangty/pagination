package ligangty.common.pagination.web.tag;

import ligangty.common.pagination.PageBean;

/**
 * Drop down list styled paging generation strategy class
 * 
 * @author gli@redhat.com
 * 
 */
public class DownListStylePageGenStrategy extends AbstractPageGenStrategy {

    /**
     * Drop down list styled paging generation strategy method
     * 
     * @param pageBean
     * @param showPages
     * @return
     */
    @Override
    protected String generateInnerPaging(PageBean pageBean, int showPages) {

        StringBuffer htmlBuf = new StringBuffer();

        htmlBuf.append(PaginationGenUtil.getInstance().generateCommonPagingPart(pageBean));

        if (pageBean.getCurrentPage() > 1) {
            htmlBuf.append(PaginationGenUtil.getInstance().generatePageAnchor(1, "first"));
            htmlBuf.append(PaginationGenUtil.getInstance().generatePageAnchor(pageBean.getPreviousPage(), "previous"));
        }

        if (pageBean.getCurrentPage() < pageBean.getTotalPage()) {
            htmlBuf.append(PaginationGenUtil.getInstance().generatePageAnchor(pageBean.getNextPage(), "next"));
            htmlBuf.append(PaginationGenUtil.getInstance().generatePageAnchor(pageBean.getTotalPage(), "last"));
        }

        htmlBuf.append("<select onchange=\"this.parentNode." + PageBean.PAGE_ATTR
                + ".value=this.value;this.parentNode.submit()\">");
        for (int pageIndex = 1; pageIndex <= pageBean.getTotalPage(); pageIndex++) {
            htmlBuf.append("<option value=\"" + pageIndex + "\"");
            if (pageIndex == pageBean.getCurrentPage()) {
                htmlBuf.append(" selected=\"selected\"");
            }
            htmlBuf.append(">page " + pageIndex + "</option>");
        }

        htmlBuf.append("</select>");

        return htmlBuf.toString();
    }

}
