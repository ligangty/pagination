package ligangty.common.pagination.web.tag;

import static org.junit.Assert.assertTrue;
import ligangty.common.pagination.PageBean;

import org.junit.Test;

public class DownListStylePageGenStrategyTest {
    private DownListStylePageGenStrategy dlStra = new DownListStylePageGenStrategy();

    @Test
    public void testGeneratePaging() {
        PageBean bean = new PageBean();
        bean.setTotalRecords(1000L);
        bean.setCurrentPage(2);
        bean.setTotalPage(20);
        bean.setPageSize(50);
        bean.addSearchingParams("param", "paramValue");
        String result = dlStra.generatePaging(bean, 5, "", "");
        String expectContainsFirstPrevious = "<a href=\"#\" onclick=\"this.parentNode.page.value=1;this.parentNode.submit()\"><span>first</span></a>"
                + PaginationGenUtil.LINE_SEPARATOR
                + "<a href=\"#\" onclick=\"this.parentNode.page.value=1;this.parentNode.submit()\"><span>previous</span></a>";
        String expectContainsNextLast = "<a href=\"#\" onclick=\"this.parentNode.page.value=3;this.parentNode.submit()\"><span>next</span></a>"
                + PaginationGenUtil.LINE_SEPARATOR
                + "<a href=\"#\" onclick=\"this.parentNode.page.value=20;this.parentNode.submit()\"><span>last</span></a>";
        String expectContainsList = "<select onchange=\"this.parentNode.page.value=this.value;this.parentNode.submit()\">"
                + "<option value=\"1\">page 1</option>" + "<option value=\"2\" selected=\"selected\">page 2</option>"
                + "<option value=\"3\">page 3</option>" + "<option value=\"4\">page 4</option>"
                + "<option value=\"5\">page 5</option>" + "<option value=\"6\">page 6</option>"
                + "<option value=\"7\">page 7</option>" + "<option value=\"8\">page 8</option>"
                + "<option value=\"9\">page 9</option>" + "<option value=\"10\">page 10</option>"
                + "<option value=\"11\">page 11</option>" + "<option value=\"12\">page 12</option>"
                + "<option value=\"13\">page 13</option>" + "<option value=\"14\">page 14</option>"
                + "<option value=\"15\">page 15</option>" + "<option value=\"16\">page 16</option>"
                + "<option value=\"17\">page 17</option>" + "<option value=\"18\">page 18</option>"
                + "<option value=\"19\">page 19</option>" + "<option value=\"20\">page 20</option>" + "</select>";
        assertTrue(result.contains(expectContainsFirstPrevious));
        assertTrue(result.contains(expectContainsNextLast));
        assertTrue(result.contains(expectContainsList));
    }

}
