
import com.example.unitconverter.model.ConversionData;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class LengthConverterTest {
    private final LengthConverter conv = new LengthConverter();

    @Test void metersToKm() {
        assertEquals(1.0, conv.convert(new ConversionData(1000,"Meters","Kilometers","Length")),1e-3);
    }
    @Test void milesToKm() {
        assertEquals(16.09344, conv.convert(new ConversionData(10,"Miles","Kilometers","Length")),1e-3);
    }
    @Test void feetToM() {
        assertEquals(30.48, conv.convert(new ConversionData(100,"Feet","Meters","Length")),1e-3);
    }
}