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
        StringBuilder htmlBuf = new StringBuilder();
        PaginationGenUtil genUtil = PaginationGenUtil.getInstance();

        htmlBuf.append(genUtil.generateCommonPagingPart(pageBean));

        if (pageBean.getCurrentPage() > 1) {
            htmlBuf.append(genUtil.generatePageAnchor(1, "first"));
            htmlBuf.append(genUtil.generatePageAnchor(pageBean.getPreviousPage(), "previous"));
        }

        if (pageBean.getCurrentPage() < pageBean.getTotalPage()) {
            htmlBuf.append(genUtil.generatePageAnchor(pageBean.getNextPage(), "next"));
            htmlBuf.append(genUtil.generatePageAnchor(pageBean.getTotalPage(), "last"));
        }

        htmlBuf.append("<select onchange=\"this.parentNode." + PageBean.PAGE_ATTR
                + ".value=this.value;this.parentNode.submit()\">");
        for (int pageIndex = 1; pageIndex <= pageBean.getTotalPage(); pageIndex++) {
            htmlBuf.append("<option value=\"").append(pageIndex).append("\"");
            if (pageIndex == pageBean.getCurrentPage()) {
                htmlBuf.append(" selected=\"selected\"");
            }
            htmlBuf.append(">page ").append(pageIndex).append("</option>");
        }

        htmlBuf.append("</select>");

        return htmlBuf.toString();
    }

}
