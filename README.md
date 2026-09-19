# Food Management System

A clean, modern **GUI-based Food Inventory Management** application built with **Java Swing**.

Demonstrates the four pillars of Object-Oriented Programming:

| Concept         | How it is applied                                      |
|-----------------|--------------------------------------------------------|
| **Encapsulation** | Private fields + public getters/setters               |
| **Inheritance**   | `Food extends Item`                                    |
| **Polymorphism**  | `ArrayList<Item>` holds `Food` objects; `totalValue()` is called polymorphically |
| **Abstraction**   | Abstract method `totalValue()` declared in `Item`     |

---

## Screenshots (Flow)

```
Welcome Screen  →  Login  →  Main Inventory Dashboard
```

- **Welcome** – Dark professional landing page  
- **Login** – Password protected (`razin`)  
- **Dashboard** – Add / Update / Delete items + live table with Total Value  

---

## Features

- Modern dark UI with teal accents  
- Add, Update, Delete food items  
- Live table showing **Total Value** (price × quantity)  
- Click any row to edit  
- Data auto-saves to `foods.txt` and loads on startup  
- Input validation (empty fields, negative values, number format)  

---

## Project Structure

```
food-management-system/
├── src/main/java/FoodMS/
│   ├── Item.java            # Abstract parent class
│   ├── Food.java            # Concrete food item
│   ├── WelcomeFrame.java    # Landing page
│   ├── LoginFrame.java      # Authentication
│   ├── FoodFrame.java       # Main dashboard
│   └── FoodMS.java          # Entry point
├── foods.txt                # Persistent data
├── pom.xml                  # Maven configuration
├── README.md
├── LICENSE
└── .gitignore
```

---

## Requirements

- **Java 17+** (or Java 11+)
- Maven (optional, for easier build)

---

## How to Run

### Using Maven

```bash
mvn clean compile
mvn exec:java
```

### Manual compilation

```bash
javac -d out src/main/java/FoodMS/*.java
java -cp out FoodMS.FoodMS
```

### NetBeans / IntelliJ

Open the project folder and run the class `FoodMS.FoodMS`.

---

## Login Credentials

| Field    | Value  |
|----------|--------|
| Password | `razin` |

---

## Sample Data Format (`foods.txt`)

```
1,burger,100.0,12
2,pizza,500.0,4
3,Shawarma,80.0,15
```

---

## Author

**Mohammad Razin Masud**    
Course: Object Oriented Programming Lab (`CSE 2104`)  
University of Liberal Arts Bangladesh  

Submitted to: **Jannatul Ferdous Ruma**, Lecturer, Department of CSE

---

## License

Released under the [MIT License](LICENSE).
