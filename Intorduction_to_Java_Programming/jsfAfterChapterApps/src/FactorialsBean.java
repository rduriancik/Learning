import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import java.math.BigInteger;

/**
 * Created by robert on 5.2.2017.
 */

@ManagedBean
@RequestScoped
public class FactorialsBean {
    public FactorialsBean() {
    }

    public String getFactorialTable() {
        StringBuilder result = new StringBuilder("<table>" +
                "<thead>" +
                "<tr>" +
                "<th>Number</th>" +
                "<th>Factorial</th>" +
                "</tr>" +
                "</thead>" +
                "<tbody>");

        for (BigInteger i = BigInteger.ZERO; i.compareTo(BigInteger.valueOf(20)) < 1; i = i.add(BigInteger.ONE)) {
            result.append(String.format("<tr><td>%d</td><td>%d</td></tr>", i, factorial(i)));
        }

        result.append("</tbody></table>");
        return result.toString();
    }

    private BigInteger factorial(BigInteger number) {
        BigInteger result = BigInteger.ONE;
        for (; number.compareTo(BigInteger.ONE) == 1; number = number.subtract(BigInteger.ONE)) {
            result = result.multiply(number);
        }
        return result;
    }
}
