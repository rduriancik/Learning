/**
 * Created by robert on 18.1.2017.
 */

import javax.faces.bean.RequestScoped;
import javax.inject.Named;

@Named
@RequestScoped
public class TimeBean {
    public TimeBean() {
    }

    public String getTime() {
        return new java.util.Date().toString();
    }
}
