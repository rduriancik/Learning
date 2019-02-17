package afterChapterApps.cardGame;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by robert on 26.5.2016.
 */
public class CardPane extends HBox {
    private List<ImageView> cards;
    private Integer[] values;

    /**
     * Constructs a new CardPane object with 4 random cards
     */
    public CardPane() {
        cards = new ArrayList<>(52);
        values = new Integer[4];

        this.setSpacing(5);
        this.setPadding(new Insets(5));
        this.setAlignment(Pos.CENTER);

        try {
            Files.walk(Paths.get("src/cards")).forEach(filePath -> {
                if (Files.isRegularFile(filePath)) {
                    ImageView imageView = new ImageView(filePath.toUri().toString());
                    imageView.setFitWidth(160);
                    imageView.setFitHeight(200);
                    String name = filePath.getFileName().toString();
                    imageView.setId(name.substring(0, name.indexOf('.')));
                    cards.add(imageView);
                }
            });
        } catch (IOException ex) {
            System.err.println("Error when reading cards. - " + ex.getMessage());
        }

        showCards();
    }

    /**
     * Shows 4 random cards from the deck
     */
    public void showCards() {
        if (cards == null || cards.isEmpty()) {
            return;
        }

        this.getChildren().clear();
        ImageView img;

        for (int i = 0; i < 4; i++) {
            do {
                img = cards.get((int) (Math.random() * 100) % 52);
            } while (this.getChildren().contains(img));
            values[i] = (Integer.parseInt(img.getId()) % 13 != 0) ? (Integer.parseInt(img.getId()) % 13) : 13;
            this.getChildren().add(img);
        }
    }

    /**
     * Returns an array of values of showed cards
     */
    public Integer[] getValues() {
        return values;
    }
}
