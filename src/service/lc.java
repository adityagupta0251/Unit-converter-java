
import com.example.unitconverter.model.ConversionData;

public class LengthConverter implements ConversionService {
    @Override
    public double convert(ConversionData data) {
        double v = data.getValue();
        double m = switch(data.getFromUnit()) {
            case "Kilometers" -> v * 1000;
            case "Centimeters" -> v / 100;
            case "Millimeters" -> v / 1000;
            case "Miles"      -> v * 1609.344;
            case "Yards"      -> v * 0.9144;
            case "Feet"       -> v * 0.3048;
            case "Inches"     -> v * 0.0254;
            default           -> v;
        };
        return switch(data.getToUnit()) {
            case "Kilometers" -> m / 1000;
            case "Centimeters"-> m * 100;
            case "Millimeters"-> m * 1000;
            case "Miles"      -> m / 1609.344;
            case "Yards"      -> m / 0.9144;
            case "Feet"       -> m / 0.3048;
            case "Inches"     -> m / 0.0254;
            default           -> m;
        };
    }
}