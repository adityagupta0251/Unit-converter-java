<!-- 🚀✨ Unit Converter Desktop Application with Java & JavaFX ✨🚀 -->

---

🎯 **Project Name:** **Unit Converter**  
🛠️ **Tech Stack:** Java 11+, JavaFX 17+, Maven, JUnit 5  
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
| 10. 🎨 [CSS Styling](#css-styling)           |                                              |
| 11. ✅ [Testing](#testing)                    |                                              |
| 12. ▶️ [Run App](#run-app)                    |                                              |
| 13. 🔮 [Future Spring Integration](#spring)   |                                              |
| 14. 📚 [Resources](#resources)                |                                              |

---

## 📝 1. Project Overview <a name="project-overview"></a>

A sleek desktop **Unit Converter** app that converts between Length, Weight & Temperature units in real-time.  
Features:  
- 🏷️ Tab-based navigation  
- 🔄 Dynamic unit selection  
- ⚡ Real-time conversion  
- ✔️ Input validation & formatted output  

---

## 🏗️ 2. Tech Stack <a name="tech-stack"></a>

| Component             | Technology/Version        | Emoji   |
|-----------------------|---------------------------|---------|
| **Language**          | Java 11+                  | ☕      |
| **UI Framework**      | JavaFX 17+                | 🖥️      |
| **Build Tool**        | Maven                     | 📦      |
| **Testing**           | JUnit 5                   | ✅      |
| **Future Backend**    | Spring Boot / Spring Cloud| 🌐      |

---

## 🛠️ 3. Project Setup <a name="project-setup"></a>

Create `pom.xml` with JavaFX & JUnit deps:

```xml
<!-- pom.xml -->
<project xmlns="http://maven.apache.org/POM/4.0.0" 
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" 
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 
                             http://maven.apache.org/xsd/maven-4.0.0.xsd">
  <modelVersion>4.0.0</modelVersion>

  <groupId>com.example</groupId>
  <artifactId>unit-converter</artifactId>
  <version>1.0.0</version>
  <packaging>jar</packaging>

  <properties>
    <maven.compiler.source>11</maven.compiler.source>
    <maven.compiler.target>11</maven.compiler.target>
    <javafx.version>17.0.2</javafx.version>
    <junit.version>5.8.1</junit.version>
  </properties>

  <dependencies>
    <!-- JavaFX -->
    <dependency>
      <groupId>org.openjfx</groupId>
      <artifactId>javafx-controls</artifactId>
      <version>${javafx.version}</version>
    </dependency>
    <dependency>
      <groupId>org.openjfx</groupId>
      <artifactId>javafx-fxml</artifactId>
      <version>${javafx.version}</version>
    </dependency>

    <!-- Testing -->
    <dependency>
      <groupId>org.junit.jupiter</groupId>
      <artifactId>junit-jupiter-api</artifactId>
      <version>${junit.version}</version>
      <scope>test</scope>
    </dependency>
    <dependency>
      <groupId>org.junit.jupiter</groupId>
      <artifactId>junit-jupiter-engine</artifactId>
      <version>${junit.version}</version>
      <scope>test</scope>
    </dependency>
  </dependencies>

  <build>
    <plugins>
      <!-- Java Compiler -->
      <plugin>
        <groupId>org.apache.maven.plugins</groupId>
        <artifactId>maven-compiler-plugin</artifactId>
        <version>3.8.1</version>
        <configuration>
          <source>11</source>
          <target>11</target>
        </configuration>
      </plugin>
      <!-- JavaFX Maven Plugin -->
      <plugin>
        <groupId>org.openjfx</groupId>
        <artifactId>javafx-maven-plugin</artifactId>
        <version>0.0.8</version>
        <configuration>
          <mainClass>com.example.unitconverter.App</mainClass>
        </configuration>
      </plugin>
    </plugins>
  </build>
</project>
````

---

## 📂 4. Application Structure <a name="app-structure"></a>

```bash
src/
├─ main/
│  ├─ java/
│  │  └─ com/example/unitconverter/
│  │     ├─ App.java              🏁 Main entry point
│  │     ├─ controller/
│  │     │  └─ ConverterController.java  
│  │     ├─ service/
│  │     │  ├─ ConversionService.java
│  │     │  ├─ LengthConverter.java
│  │     │  ├─ WeightConverter.java
│  │     │  └─ TemperatureConverter.java
│  │     └─ model/
│  │        └─ ConversionData.java
│  └─ resources/
│     ├─ converter.fxml           🖼️ UI layout
│     ├─ style.css                🎨 Styling
│     └─ icon.png                 🔖 App icon
└─ test/
   └─ java/
      └─ com/example/unitconverter/service/
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
        this.value   = value;
        this.fromUnit= fromUnit;
        this.toUnit  = toUnit;
        this.category= category;
    }

    // Getters & Setters 🔄
    public double getValue()         { return value; }
    public void   setValue(double v) { this.value = v; }
    public String getFromUnit()      { return fromUnit; }
    public void   setFromUnit(String u) { this.fromUnit = u; }
    public String getToUnit()        { return toUnit; }
    public void   setToUnit(String u){ this.toUnit = u; }
    public String getCategory()      { return category; }
    public void   setCategory(String c) { this.category = c; }
}
```

---

## 🔄 6. Service Layer <a name="service-layer"></a>

### ConversionService Interface

```java
// ConversionService.java
package com.example.unitconverter.service;

import com.example.unitconverter.model.ConversionData;

public interface ConversionService {
    double convert(ConversionData data);
}
```

### LengthConverter

```java
// LengthConverter.java
package com.example.unitconverter.service;
import com.example.unitconverter.model.ConversionData;

public class LengthConverter implements ConversionService {
    @Override
    public double convert(ConversionData data) {
        double v = data.getValue();
        // ▪️ To meters
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
        // ▪️ From meters to target
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

### WeightConverter & TemperatureConverter

> *(Similar structure: convert → base unit → target unit)*

---

## 🎛️ 7. Controller <a name="controller"></a>

```java
// ConverterController.java
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
    // 🔄 Switch units when tab changes
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
        default            -> throw new IllegalArgumentException("Unknown");
      };
      ConversionData data = new ConversionData(val, fromUnitCombo.getValue(), toUnitCombo.getValue(), cat);
      double res = conversionService.convert(data);
      resultLabel.setText(String.format("%,.4f %s ➡️ %,.4f %s", val, data.getFromUnit(), res, data.getToUnit()));
    } catch(NumberFormatException e) {
      resultLabel.setText("❌ Invalid input; please enter a number!");
    }
  }
}
```

---

## 🌟 8. Main App Class <a name="main-app-class"></a>

```java
// App.java
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
    stage.setMinWidth(500); stage.setMinHeight(450);
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
<!-- converter.fxml -->
<VBox xmlns="http://javafx.com/javafx/17"
      xmlns:fx="http://javafx.com/fxml/1"
      fx:controller="com.example.unitconverter.controller.ConverterController"
      alignment="TOP_CENTER" spacing="20">
  <padding>
    <Insets top="20" right="20" bottom="20" left="20"/>
  </padding>

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
/* style.css */
.root {
  -fx-font-family: 'Segoe UI', sans-serif;
  -fx-background-color: #f5f7fa;
}
.title {
  -fx-text-fill: #2c3e50; -fx-font-weight: bold;
}
/* TabPane */
.tab-pane {
  -fx-background-color: white;
  -fx-background-radius: 5;
  -fx-effect: dropshadow(three-pass-box, rgba(0,0,0,0.1), 5,0,0);
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
/* Inputs */
.input-label { -fx-font-size:14px; -fx-text-fill:#34495e; }
.input-field {
  -fx-font-size:16px; -fx-pref-width:200px; -fx-padding:8;
  -fx-background-radius:4; -fx-border-radius:4; -fx-border-color:#bdc3c7;
}
.input-field:focused { -fx-border-color:#3498db; }
/* Combos */
.combo-label { -fx-font-size:13px; -fx-text-fill:#7f8c8d; }
.unit-combo { -fx-pref-width:150px; }
/* Button */
.convert-button {
  -fx-background-color:#3498db; -fx-text-fill:white;
  -fx-font-size:14px; -fx-font-weight:bold; -fx-padding:8 25;
  -fx-background-radius:4; -fx-cursor:hand;
}
.convert-button:hover { -fx-background-color:#2980b9; }
/* Result */
.result-label {
  -fx-font-size:16px; -fx-font-weight:bold; -fx-text-fill:#27ae60;
  -fx-padding:15; -fx-background-color:white;
  -fx-background-radius:5; -fx-effect:dropshadow(three-pass-box, rgba(0,0,0,0.05),3,0,0);
}
```

---

## ✅ 11. Testing <a name="testing"></a>

```java
// LengthConverterTest.java
package com.example.unitconverter.service;

import com.example.unitconverter.model.ConversionData;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class LengthConverterTest {
  private final LengthConverter conv = new LengthConverter();

  @Test void metersToKm() {
    assertEquals(1.0, conv.convert(new ConversionData(1000,"Meters","Kilometers","Length")), 1e-3);
  }
  @Test void milesToKm() {
    assertEquals(16.09344, conv.convert(new ConversionData(10,"Miles","Kilometers","Length")), 1e-3);
  }
  @Test void feetToM() {
    assertEquals(30.48, conv.convert(new ConversionData(100,"Feet","Meters","Length")), 1e-3);
  }
}
```

---

## ▶️ 12. Run Application <a name="run-app"></a>

```bash
mvn clean javafx:run
```

---

## 🔮 13. Future Integration with Spring <a name="spring"></a>

| 🔧 Feature                | 📈 Benefit                          |
| ------------------------- | ----------------------------------- |
| Spring Boot Microservices | REST APIs for conversions           |
| Eureka Service Discovery  | Auto-register & discovery           |
| Docker & Kubernetes       | Scalable, containerized deployments |
| Spring Security           | Auth & role-based access            |
| Spring Data JPA           | Track conversion history in DB      |
| External Currency APIs    | Live currency conversions           |
| Dark/Light Mode Toggle    | Enhanced UX                         |

```java
// Spring ConversionController.java
@RestController
@RequestMapping("/api/convert")
public class ConversionController {
  @Autowired private ConversionService service;

  @PostMapping
  public ConversionResult convert(@RequestBody ConversionRequest req) {
    double result = service.convert(req.getValue(), req.getFromUnit(), req.getToUnit(), req.getCategory());
    return new ConversionResult(req.getValue(), req.getFromUnit(), result, req.getToUnit(), req.getCategory());
  }
}
```

---

## 📚 14. Resources <a name="resources"></a>

| 🔗 Link                                                               | 📖 Description                |
| --------------------------------------------------------------------- | ----------------------------- |
| [OpenJFX](https://openjfx.io/)                                        | Official JavaFX documentation |
| [JavaFX API](https://docs.oracle.com/javase/8/javafx/api)             | JavaFX API reference          |
| [Spring Boot](https://spring.io/projects/spring-boot)                 | Spring Boot guides & docs     |
| [Scene Builder](https://gluonhq.com/products/scene-builder/)          | Drag‑n‑drop FXML designer     |
| [Material Design for JavaFX](https://github.com/jfoenixadmin/JFoenix) | UI component library          |

---

✨ **Happy Coding!** ✨
🚀 Build, Convert & Conquer! 🧮💡

```
```
