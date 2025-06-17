
import com.example.unitconverter.model.ConversionData;
import com.example.unitconverter.service.*;
import javafx.fxml.FXML;
import javafx.scene.control.*;

public class ConverterController {
    @FXML private TabPane tabPane;
    @FXML private TextField inputField;
    @FXML private ComboBox<String> fromUnitCombo, toUnitCombo;
    @FXML private Label resultLabel;

    private ConversionService conversionService;

    @FXML
    public void initialize() {
        tabPane.getSelectionModel().selectedItemProperty()
                .addListener((obs, o, n) -> updateCombos(n.getText()));
        updateCombos("Length");
    }

    private void updateCombos(String cat) {
        fromUnitCombo.getItems().clear();
        toUnitCombo.getItems().clear();
        switch(cat) {
            case "Length"      -> fromUnitCombo.getItems().addAll("Meters","Kilometers","Centimeters","Millimeters","Miles","Yards","Feet","Inches");
            case "Weight"      -> fromUnitCombo.getItems().addAll("Kilograms","Grams","Milligrams","Metric Tons","Pounds","Ounces","Stone");
            case "Temperature" -> fromUnitCombo.getItems().addAll("Celsius","Fahrenheit","Kelvin");
        }
        toUnitCombo.getItems().setAll(fromUnitCombo.getItems());
        fromUnitCombo.getSelectionModel().selectFirst();
        toUnitCombo.getSelectionModel().selectFirst();
    }

    @FXML
    private void handleConvert() {
        try {
            double val = Double.parseDouble(inputField.getText());
            String cat = tabPane.getSelectionModel().getSelectedItem().getText();
            conversionService = switch(cat) {
                case "Length"      -> new LengthConverter();
                case "Weight"      -> new WeightConverter();
                case "Temperature" -> new TemperatureConverter();
                default            -> throw new IllegalArgumentException("Unknown category");
            };
            ConversionData data = new ConversionData(val, fromUnitCombo.getValue(), toUnitCombo.getValue(), cat);
            double res = conversionService.convert(data);
            resultLabel.setText(String.format("%,.4f %s ➡️ %,.4f %s", val, data.getFromUnit(), res, data.getToUnit()));
        } catch (NumberFormatException e) {
            resultLabel.setText("❌ Invalid input; please enter a number!");
        }
    }
}