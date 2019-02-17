package jsf2demo;

/**
 * Created by robert on 18.1.2017.
 */

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.inject.Named;

@ManagedBean
@Named(value = "timeBean")
@RequestScoped
public class TimeBean {
    public TimeBean() {
    }

    public String getTime() {
        return new java.util.Date().toString();
    }
}
