package ligangty.common.pagination.web.tag;

import ligangty.common.pagination.PageBean;

/**
 * Abstract Strategy class to extract the common outer rendering functions
 * 
 * @author gli
 */
public abstract class AbstractPageGenStrategy implements PageGenStrategy {

    @Override
    public String generatePaging(PageBean pageBean, int showPages, String actionUrl, String cssClass) {
        StringBuilder htmlBuf = new StringBuilder();

        if (cssClass != null && !cssClass.trim().equals("")) {
            htmlBuf.append("<div class=\"").append(cssClass).append("\">");
        } else {
            htmlBuf.append("<div>");
        }

        htmlBuf.append("<form method=\"post\" action=\"").append(actionUrl).append("\">");

        htmlBuf.append(generateInnerPaging(pageBean, showPages));

        htmlBuf.append("</form>");

        htmlBuf.append("</div>");

        return htmlBuf.toString();
    }

    protected abstract String generateInnerPaging(PageBean pageBean, int showPages);

}
