import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

/**
 * Created by robert on 5.2.2017.
 */

@ManagedBean
@RequestScoped
public class MultiplicationTableBean {
    public MultiplicationTableBean() {
    }

    public String getMultiplicationTable() {
        StringBuilder table = new StringBuilder("<table>");

        table.append("<thead><tr><th colspan=\"11\">Multiplication Table</th></tr></thead>");
        table.append("<tbody><tr><td></td>");
        for (int i = 1; i <= 10; i++) {
            table.append(String.format("<td>%d</td>", i));
        }
        table.append("</tr>");

        for (int i = 1; i <= 10; i++) {
            table.append(String.format("<tr><td>%d</td>", i));
            for (int j = 1; j <= 10; j++) {
                table.append(String.format("<td>%d</td>", i * j));
            }
            table.append("</tr>");
        }
        table.append("</tbody></table>");
        return table.toString();
    }
}
