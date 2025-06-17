// ConversionData.java


public class ConversionData {
    private double value;
    private String fromUnit, toUnit, category;

    public ConversionData() {}
    public ConversionData(double value, String fromUnit, String toUnit, String category) {
        this.value = value;
        this.fromUnit = fromUnit;
        this.toUnit = toUnit;
        this.category = category;
    }

    // Getters & Setters
    public double getValue() { return value; }
    public void setValue(double v) { this.value = v; }
    public String getFromUnit() { return fromUnit; }
    public void setFromUnit(String u) { this.fromUnit = u; }
    public String getToUnit() { return toUnit; }
    public void setToUnit(String u) { this.toUnit = u; }
    public String getCategory() { return category; }
    public void setCategory(String c) { this.category = c; }
}