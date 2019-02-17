package jsf2demo;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.inject.Named;

/**
 * Created by robert on 27.1.2017.
 */

@ManagedBean
@RequestScoped
@Named(value = "calculator")
public class CalculatorJSFBean {
    private Double num1;
    private Double num2;
    private Double result;

    public CalculatorJSFBean() {
    }

    public Double getNum1() {
        return num1;
    }

    public void setNum1(Double num1) {
        this.num1 = num1;
    }

    public Double getNum2() {
        return num2;
    }

    public void setNum2(Double num2) {
        this.num2 = num2;
    }

    public Double getResult() {
        return result;
    }

    public void setResult(Double result) {
        this.result = result;
    }

    public void add() {
        result = num1 + num2;
    }

    public void subtract() {
        result = num1 - num2;
    }

    public void multiply() {
        result = num1 * num2;
    }

    public void divide() {
        result = num1 / num2;
    }
}
