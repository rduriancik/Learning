package jsf2demo;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

/**
 * Created by robert on 27.1.2017.
 */

@ManagedBean
@ViewScoped
public class GuessNumberJSFBean {
    private int number;
    private String guessString;

    public GuessNumberJSFBean() {
        number = (int) (Math.random() * 100);
    }

    public String getGuessString() {
        return guessString;
    }

    public void setGuessString(String guessString) {
        this.guessString = guessString;
    }

    public String getResponse() {
        if (guessString == null) {
            return ""; // No user input yet
        }

        int guess = Integer.parseInt(guessString);
        if (guess < number) {
            return "Too low";
        } else if (guess > number) {
            return "Too high";
        } else {
            return "You got it";
        }
    }
}
