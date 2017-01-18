package afterChapterApps;

import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;

/**
 * Created by robert on 04.12.15.
 */
public class TestAddressBook extends Application {


    private File file = new File("Exercise17_09.dat");
    private int addressCount;
    private int currentAddress;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws FileNotFoundException {
        // Initialize address count and current address
        currentAddress = 0;
        addressCount = getAddressCount();

        // Create a main pane
        BorderPane pane = new BorderPane();

        // Create a pane for info
        GridPane gridPane = new GridPane();
        gridPane.setVgap(10);
        gridPane.setHgap(5);
        gridPane.setPadding(new Insets(10));

        gridPane.add(new Label("Name"), 0, 0);
        TextField tfName = new TextField();
        tfName.setPrefColumnCount(32);
        tfName.lengthProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                if (newValue.intValue() > oldValue.intValue()) {
                    if (tfName.getText().length() > 32) {
                        tfName.setText(tfName.getText().substring(0, 32));
                    }
                }
            }
        });
        gridPane.add(tfName, 1, 0);
        GridPane.setColumnSpan(tfName, 6);

        gridPane.add(new Label("Street"), 0, 1);
        TextField tfStreet = new TextField();
        tfStreet.setPrefColumnCount(32);
        tfStreet.lengthProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                if (newValue.intValue() > oldValue.intValue()) {
                    if (tfStreet.getText().length() > 32) {
                        tfStreet.setText(tfStreet.getText().substring(0, 32));
                    }
                }
            }
        });
        gridPane.add(tfStreet, 1, 1);
        GridPane.setColumnSpan(tfStreet, 6);

        gridPane.add(new Label("City"), 0, 2);
        TextField tfCity = new TextField();
        tfCity.setPrefColumnCount(20);
        tfCity.lengthProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                if (newValue.intValue() > oldValue.intValue()) {
                    if (tfCity.getText().length() > 20) {
                        tfCity.setText(tfCity.getText().substring(0, 20));
                    }
                }
            }
        });
        gridPane.add(tfCity, 1, 2);

        gridPane.add(new Label("State"), 3, 2);
        TextField tfState = new TextField();
        tfState.setPrefColumnCount(2);
        tfState.lengthProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                if (newValue.intValue() > oldValue.intValue()) {
                    if (tfState.getText().length() > 2) {
                        tfState.setText(tfState.getText().substring(0, 2));
                    }
                }
            }
        });
        gridPane.add(tfState, 4, 2);

        gridPane.add(new Label("Zip"), 5, 2);
        TextField tfZip = new TextField();
        tfZip.setPrefColumnCount(5);
        tfZip.lengthProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                if (newValue.intValue() > oldValue.intValue()) {
                    if (tfZip.getText().length() > 5) {
                        tfZip.setText(tfZip.getText().substring(0, 5));
                    }
                }
            }
        });
        gridPane.add(tfZip, 6, 2);

        pane.setCenter(gridPane);

        // Create a HBox for controls
        HBox hBox = new HBox(10);
        hBox.setAlignment(Pos.CENTER);
        hBox.setPadding(new Insets(0, 0, 5, 0));
        Button btAdd = new Button("Add");
        Button btFirst = new Button("First");
        Button btNext = new Button("Next");
        Button btPrevious = new Button("Previous");
        Button btLast = new Button("Last");
        Button btUpdate = new Button("Update");

        hBox.getChildren().addAll(btAdd, btFirst, btNext, btPrevious, btLast, btUpdate);

        pane.setBottom(hBox);

        // Create the action events for buttons
        btAdd.setOnAction(e -> {
            // Get address from textFields into an array
            String[] address = new String[5];
            address[0] = tfName.getText();
            address[1] = tfStreet.getText();
            address[2] = tfCity.getText();
            address[3] = tfState.getText();
            address[4] = tfZip.getText();
            addressCount++;
            currentAddress = addressCount;
            addAddress(address);
        });

        btFirst.setOnAction(e -> {
            String[] address = getFirstAddress();
            currentAddress = 1;
            tfName.setText(address[0]);
            tfStreet.setText(address[1]);
            tfCity.setText(address[2]);
            tfState.setText(address[3]);
            tfZip.setText(address[4]);

        });

        btNext.setOnAction(e -> {
            if (currentAddress == addressCount) {
                currentAddress = 1;
            } else {
                currentAddress++;
            }

            String[] address = getNextAddress();

            tfName.setText(address[0]);
            tfStreet.setText(address[1]);
            tfCity.setText(address[2]);
            tfState.setText(address[3]);
            tfZip.setText(address[4]);
        });

        btPrevious.setOnAction(e -> {
            if (currentAddress != 1) {
                currentAddress--;
            } else {
                currentAddress = addressCount;
            }

            String[] address = getPreviousAddress();

            tfName.setText(address[0]);
            tfStreet.setText(address[1]);
            tfCity.setText(address[2]);
            tfState.setText(address[3]);
            tfZip.setText(address[4]);
        });

        btLast.setOnAction(e -> {
            String[] address = getLastAddress();

            currentAddress = addressCount;

            tfName.setText(address[0]);
            tfStreet.setText(address[1]);
            tfCity.setText(address[2]);
            tfState.setText(address[3]);
            tfZip.setText(address[4]);
        });

        btUpdate.setOnAction(e -> {
            String[] address = new String[5];
            address[0] = tfName.getText();
            address[1] = tfStreet.getText();
            address[2] = tfCity.getText();
            address[3] = tfState.getText();
            address[4] = tfZip.getText();

            updateAddress(address);

        });

        // Create a scene and place it in the stage
        Scene scene = new Scene(pane);
        primaryStage.setTitle("TestAddressBook");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void addAddress(String[] address) {
        try (
                RandomAccessFile inout = new RandomAccessFile(file, "rw")
        ) {
            inout.seek(inout.length());
            inout.writeBytes(addressCount + ":" + address[0] + ":" + address[1] + ":"
                    + address[2] + ":" + address[3] + ":" + address[4] + System.lineSeparator());
        } catch (IOException ex) {
            System.err.println("Add address");
            ex.printStackTrace();
        }
    }

    private boolean updateAddress(String[] addressToUpdate) {
        File temp = new File("Exercise17_09.tmp");

        try (
                RandomAccessFile inout = new RandomAccessFile(file, "rw");
                RandomAccessFile tempInout = new RandomAccessFile(temp, "rw")
        ) {
            inout.seek(0);
            if (inout.length() == 0) {
                return false;
            }

            String address = inout.readLine();
            int addressNumber;

            while (address != null) {
                addressNumber = Integer.parseInt(address.split(":")[0]);

                if (addressNumber == currentAddress) {
                    tempInout.writeBytes(currentAddress + ":" + addressToUpdate[0] + ":" + addressToUpdate[1] + ":"
                            + addressToUpdate[2] + ":" + addressToUpdate[3] + ":" + addressToUpdate[4] + System.lineSeparator());
                    address = inout.readLine();
                    continue;
                }

                tempInout.writeBytes(address + System.lineSeparator());
                address = inout.readLine();
            }

        } catch (IOException ex) {
            System.err.println("Update address");
            ex.printStackTrace();
            return false;
        }

        file.delete();
        temp.renameTo(file);

        return true;
    }

    private String[] getNextAddress() {
        try (RandomAccessFile inout = new RandomAccessFile(file, "r")) {
            inout.seek(0);
            if (inout.length() == 0) {
                return new String[]{"", "", "", "", ""};
            }

            String address = inout.readLine();
            int addressNumber = Integer.parseInt(address.split(":")[0]);

            while (addressNumber != currentAddress) {
                address = inout.readLine();
                addressNumber = Integer.parseInt(address.split(":")[0]);
            }

            String[] splitAddress = address.split(":");

            return java.util.Arrays.copyOfRange(splitAddress, 1, splitAddress.length);
        } catch (IOException ex) {
            System.err.println("Next address");
            ex.printStackTrace();
            return new String[]{"", "", "", "", ""};
        }
    }

    private String[] getPreviousAddress() {
        try (RandomAccessFile inout = new RandomAccessFile(file, "r")) {
            inout.seek(0);
            if (inout.length() == 0) {
                return new String[]{"", "", "", "", ""};
            }

            String address = inout.readLine();
            int addressNumber = Integer.parseInt(address.split(":")[0]);

            while (addressNumber != currentAddress) {
                address = inout.readLine();
                addressNumber = Integer.parseInt(address.split(":")[0]);
            }

            String[] splitAddress = address.split(":");

            return java.util.Arrays.copyOfRange(splitAddress, 1, splitAddress.length);
        } catch (IOException ex) {
            System.err.println("Previous address");
            ex.printStackTrace();
            return new String[]{"", "", "", "", ""};
        }
    }

    private String[] getFirstAddress() {
        try (
                RandomAccessFile inout = new RandomAccessFile(file, "r")
        ) {
            if (inout.length() == 0) {
                return new String[]{"", "", "", "", ""};
            }

            inout.seek(0);
            String address = inout.readLine();

            String[] splitAddress = address.split(":");

            return java.util.Arrays.copyOfRange(splitAddress, 1, splitAddress.length);
        } catch (IOException ex) {
            System.err.println("First address");
            ex.printStackTrace();
            return new String[]{"", "", "", "", ""};
        }
    }

    private String[] getLastAddress() {
        try (
                RandomAccessFile inout = new RandomAccessFile(file, "r")
        ) {
            inout.seek(0);
            if (inout.length() == 0) {
                return new String[]{"", "", "", "", ""};
            }

            String address = inout.readLine();
            int addressNumber = Integer.parseInt(address.split(":")[0]);
            while (addressNumber != addressCount) {
                address = inout.readLine();
                addressNumber = Integer.parseInt(address.split(":")[0]);
            }

            String[] splitAddress = address.split(":");

            return java.util.Arrays.copyOfRange(splitAddress, 1, splitAddress.length);
        } catch (IOException ex) {
            System.err.println("Last address");
            ex.printStackTrace();
            return new String[]{"", "", "", "", ""};
        }
    }

    private int getAddressCount() {
        try (RandomAccessFile inout = new RandomAccessFile(file, "rw")) {
            inout.seek(0);
            int addressCount = 0;

            if (inout.length() == 0) {
                return addressCount;
            }

            String address = inout.readLine();

            while (address != null) {
                addressCount = Integer.parseInt(address.split(":")[0]);
                address = inout.readLine();
            }

            return addressCount;
        } catch (IOException ex) {
            System.err.println("Address count");
            ex.printStackTrace();
            return 0;
        }
    }
}
