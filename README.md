<!-- 📝 Comprehensive “Unit Converter” Guide & Resources -->

---

# 🚀✨ Unit Converter Desktop Application with Java & JavaFX ✨🚀

🎯 **Project Name:** **Unit Converter**  
🛠️ **Tech Stack:** Java 11+, JavaFX 17+, Maven, JUnit 5  
📦 **Packaging:** JAR via `javafx-maven-plugin`  

---

## 📋 Table of Contents

| 🚩 Section                                    | 🔗 Link                                      |
|-----------------------------------------------|----------------------------------------------|
| 1. 🔍 [Project Overview](#project-overview)   |                                              |
| 2. 🏗️ [Tech Stack](#tech-stack)               |                                              |
| 3. 🛠️ [Project Setup](#project-setup)         |                                              |
| 4. 📂 [App Structure](#app-structure)         |                                              |
| 5. 📐 [Domain Model](#domain-model)           |                                              |
| 6. 🔄 [Service Layer](#service-layer)         |                                              |
| 7. 🎛️ [Controller](#controller)              |                                              |
| 8. 🌟 [Main App Class](#main-app-class)       |                                              |
| 9. 🖼️ [FXML Layout](#fxml-layout)            |                                              |
| 10. 🎨 [CSS Styling](#css-styling)            |                                              |
| 11. ✅ [Testing](#testing)                    |                                              |
| 12. ▶️ [Run App](#run-app)                    |                                              |
| 13. 🔮 [Future Spring Integration](#spring)   |                                              |
| 14. 📚 [Additional Resources](#resources)     |                                              |

---

## 📝 1. Project Overview <a name="project-overview"></a>

**Description & Goals**  
- Build a desktop app converting **Length**, **Weight** & **Temperature**.  
- Emphasize clean UI/UX, robust conversion logic, and future extensibility.

**Features**  
- 🏷️ Tab‑based navigation  
- 🔄 Dynamic unit selection  
- ⚡ Real‑time conversion  
- ✔️ Input validation & formatted results  

---

## 🏗️ 2. Tech Stack <a name="tech-stack"></a>

| Component         | Purpose                     | Docs & Tutorials                                                |
|-------------------|-----------------------------|-----------------------------------------------------------------|
| **Java 11+**      | Core language/runtime       | https://openjdk.java.net/projects/jdk/11/                      |
| **JavaFX 17+**    | UI toolkit                  | https://openjfx.io/                                            |
| **Maven**         | Build & dependency manager  | https://maven.apache.org/guides/getting-started/               |
| **JUnit 5**       | Unit testing framework      | https://junit.org/junit5/docs/current/user-guide/              |
| **Spring Boot**   | Future REST/service layer   | https://spring.io/projects/spring-boot                         |

---

## 🛠️ 3. Project Setup <a name="project-setup"></a>

1. **Initialize Maven Project**  
   ```bash
   mvn archetype:generate \
     -DgroupId=com.example \
     -DartifactId=unit-converter \
     -DarchetypeArtifactId=maven-archetype-quickstart


2. **Configure `pom.xml`**

   * JavaFX Controls & FXML dependencies
   * JUnit 5 for testing
   * `javafx-maven-plugin` for `mvn javafx:run`

3. **IDE Configuration**

   * **IntelliJ IDEA**: enable JavaFX plugin, configure Maven run.
   * **Eclipse**: install e(fx)clipse, use JavaFX project wizard.

4. **Guide Notes**

   * Align `pom.xml` Java version with IDE JDK.
   * Run `mvn clean install` before first launch.

---

## 📂 4. Application Structure <a name="app-structure"></a>

```text
src/
├─ main/
│  ├─ java/com/example/unitconverter/
│  │   ├─ App.java
│  │   ├─ controller/ConverterController.java
│  │   ├─ service/
│  │   │   ├─ ConversionService.java
│  │   │   ├─ LengthConverter.java
│  │   │   ├─ WeightConverter.java
│  │   │   └─ TemperatureConverter.java
│  │   └─ model/ConversionData.java
│  └─ resources/
│      ├─ converter.fxml
│      ├─ style.css
│      └─ icon.png
└─ test/java/com/example/unitconverter/service/
    └─ LengthConverterTest.java
```

---

## 📐 5. Domain Model <a name="domain-model"></a>

```java
// ConversionData.java
package com.example.unitconverter.model;

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
```

---

## 🔄 6. Service Layer <a name="service-layer"></a>

### ConversionService Interface

```java
package com.example.unitconverter.service;
import com.example.unitconverter.model.ConversionData;

public interface ConversionService {
    double convert(ConversionData data);
}
```

### LengthConverter Example

```java
package com.example.unitconverter.service;
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
```

> *WeightConverter & TemperatureConverter follow the same pattern.*

---

## 🎛️ 7. Controller <a name="controller"></a>

```java
package com.example.unitconverter.controller;
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
```

---

## 🌟 8. Main App Class <a name="main-app-class"></a>

```java
package com.example.unitconverter;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class App extends Application {
  @Override
  public void start(Stage stage) throws Exception {
    var root = FXMLLoader.load(getClass().getResource("/converter.fxml"));
    Scene scene = new Scene(root, 600, 500);
    scene.getStylesheets().add(getClass().getResource("/style.css").toExternalForm());
    stage.setTitle("🧮 Unit Converter");
    stage.getIcons().add(new Image(getClass().getResourceAsStream("/icon.png")));
    stage.setMinWidth(500);
    stage.setMinHeight(450);
    stage.setScene(scene);
    stage.show();
  }
  public static void main(String[] args) { launch(); }
}
```

---

## 🖼️ 9. FXML Layout <a name="fxml-layout"></a>

```xml
<?xml version="1.0" encoding="UTF-8"?>
<VBox xmlns="http://javafx.com/javafx/17"
      xmlns:fx="http://javafx.com/fxml/1"
      fx:controller="com.example.unitconverter.controller.ConverterController"
      alignment="TOP_CENTER" spacing="20">
  <padding><Insets top="20" right="20" bottom="20" left="20"/></padding>
  <Label text="🧮 Unit Converter" styleClass="title">
    <font><Font size="24" name="System Bold"/></font>
  </Label>
  <TabPane fx:id="tabPane" styleClass="tab-pane">
    <Tab text="Length"/><Tab text="Weight"/><Tab text="Temperature"/>
  </TabPane>
  <VBox alignment="CENTER" spacing="15">
    <Label text="Enter value to convert:" styleClass="input-label"/>
    <TextField fx:id="inputField" promptText="e.g., 42.0" styleClass="input-field"/>
    <HBox alignment="CENTER" spacing="20">
      <VBox alignment="CENTER" spacing="5">
        <Label text="From" styleClass="combo-label"/>
        <ComboBox fx:id="fromUnitCombo" styleClass="unit-combo"/>
      </VBox>
      <VBox alignment="CENTER" spacing="5">
        <Label text="To" styleClass="combo-label"/>
        <ComboBox fx:id="toUnitCombo" styleClass="unit-combo"/>
      </VBox>
    </HBox>
    <Button text="Convert 🔄" onAction="#handleConvert" styleClass="convert-button"/>
  </VBox>
  <Label fx:id="resultLabel" styleClass="result-label" wrapText="true"/>
</VBox>
```

---

## 🎨 10. CSS Styling <a name="css-styling"></a>

```css
.root {
  -fx-font-family: 'Segoe UI', sans-serif;
  -fx-background-color: #f5f7fa;
}
.title {
  -fx-text-fill: #2c3e50;
  -fx-font-weight: bold;
}
.tab-pane {
  -fx-background-color: white;
  -fx-background-radius: 5;
  -fx-effect: dropshadow(three-pass-box, rgba(0,0,0,0.1),5,0,0);
}
.tab {
  -fx-background-color: #ecf0f1;
  -fx-background-radius: 5 5 0 0;
}
.tab:selected {
  -fx-background-color: #3498db;
}
.tab:selected .tab-label {
  -fx-text-fill: white;
}
.input-label {
  -fx-font-size: 14px;
  -fx-text-fill: #34495e;
}
.input-field {
  -fx-font-size: 16px;
  -fx-pref-width: 200px;
  -fx-padding: 8;
  -fx-background-radius: 4;
  -fx-border-radius: 4;
  -fx-border-color: #bdc3c7;
}
.input-field:focused {
  -fx-border-color: #3498db;
}
.combo-label {
  -fx-font-size: 13px;
  -fx-text-fill: #7f8c8d;
}
.unit-combo {
  -fx-pref-width: 150px;
}
.convert-button {
  -fx-background-color: #3498db;
  -fx-text-fill: white;
  -fx-font-size: 14px;
  -fx-font-weight: bold;
  -fx-padding: 8 25;
  -fx-background-radius: 4;
  -fx-cursor: hand;
}
.convert-button:hover {
  -fx-background-color: #2980b9;
}
.result-label {
  -fx-font-size: 16px;
  -fx-font-weight: bold;
  -fx-text-fill: #27ae60;
  -fx-padding: 15;
  -fx-background-color: white;
  -fx-background-radius: 5;
  -fx-effect: dropshadow(three-pass-box, rgba(0,0,0,0.05),3,0,0);
}
```

---

## ✅ 11. Testing <a name="testing"></a>

```java
package com.example.unitconverter.service;
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
```

---

## ▶️ 12. Run App <a name="run-app"></a>

```bash
# Build & test
mvn clean install

# Launch JavaFX app
mvn javafx:run
```

---

## 🔮 13. Future Spring Integration <a name="spring"></a>

| 🔧 Feature                | 📈 Benefit                       |
| ------------------------- | -------------------------------- |
| Spring Boot Microservices | REST APIs for conversions        |
| Eureka Service Discovery  | Auto‑register & discovery        |
| Docker & Kubernetes       | Scalable deployments             |
| Spring Security           | Auth & role‑based access         |
| Spring Data JPA           | Persist conversion history in DB |
| External Currency APIs    | Live currency conversions        |
| Dark/Light Mode Toggle    | Enhanced UX                      |

```java
@RestController
@RequestMapping("/api/convert")
public class ConversionController {
  @Autowired private ConversionService service;

  @PostMapping
  public ConversionResult convert(@RequestBody ConversionRequest req) {
    double result = service.convert(
      req.getValue(), req.getFromUnit(), req.getToUnit(), req.getCategory()
    );
    return new ConversionResult(
      req.getValue(), req.getFromUnit(), result, req.getToUnit(), req.getCategory()
    );
  }
}
```

---

## 📚 14. Additional Resources <a name="resources"></a>

| Category             | Link                                                                                                                                                 |
| -------------------- | ---------------------------------------------------------------------------------------------------------------------------------------------------- |
| **OpenJFX**          | [https://openjfx.io/](https://openjfx.io/)                                                                                                           |
| **JavaFX API**       | [https://docs.oracle.com/javase/8/javafx/api/](https://docs.oracle.com/javase/8/javafx/api/)                                                         |
| **Spring Boot**      | [https://spring.io/projects/spring-boot](https://spring.io/projects/spring-boot)                                                                     |
| **Maven Guide**      | [https://maven.apache.org/guides/](https://maven.apache.org/guides/)                                                                                 |
| **JUnit 5**          | [https://junit.org/junit5/docs/current/user-guide/](https://junit.org/junit5/docs/current/user-guide/)                                               |
| **Scene Builder**    | [https://gluonhq.com/products/scene-builder/](https://gluonhq.com/products/scene-builder/)                                                           |
| **StackOverflow**    | [https://stackoverflow.com/questions/tagged/javafx](https://stackoverflow.com/questions/tagged/javafx)                                               |
| **Design Patterns**  | [https://refactoring.guru/design-patterns/java](https://refactoring.guru/design-patterns/java)                                                       |
| **YouTube Playlist** | [https://www.youtube.com/playlist?list=PL4cUxeGkcC9gOUlY-uCHurFIpqogsdOnw](https://www.youtube.com/playlist?list=PL4cUxeGkcC9gOUlY-uCHurFIpqogsdOnw) |

---

✨ **Happy Coding!** 🚀🧮

```
```
