# ProiectFinalJava
This project is a simple Java-based console application for managing products, including adding, displaying, updating, and removing products stored in a MySQL database.

## Table of Contents

- [Installation](#installation)
- [Usage](#usage)
- [Configuration](#configuration)
- [Running Tests](#running-tests)
- [Contributing](#contributing)
- [License](#license)
- [Contact](#contact)
- 
## Installation

- Java Development Kit (JDK) 8 or higher
- IntelliJ IDEA or any Java IDE
- MySQL 8.0
- Maven (optional)

### Steps

1. **Clone the repository**
   ```bash
   git clone https://github.com/bogdanhosu/ProiectFinalJava.git

   
**2. Open the project in IntelliJ IDEA**

Open IntelliJ IDEA.
Select File > Open... and choose the cloned repository directory. (the location on your pc)

**3.Set up MySQL**

Install MySQL 8.0 if not already installed.
Create a new database:
CREATE DATABASE InventoryDB;

Create a Products table in the InventoryDB database:
CREATE TABLE Products (
    id INT PRIMARY KEY,
    name VARCHAR(100),
    price DOUBLE,
    quantity INT,
    origin VARCHAR(100)
);


**4. Configure the database connection**

Update the application.properties (or application.yml) file in the src/main/resources directory with your database credentials:
spring.datasource.url=jdbc:mysql://localhost:3306/InventoryDB
spring.datasource.username=root
spring.datasource.password=ProiectFinal

**5.Build and run the project**
Run the project from IntelliJ IDEA by selecting Run > Run 'Main'.


## Usage
This application provides a menu to manage products. The following options are available:

- 1.Display all products
- 2.Update product price
- 3.Update product quantity
- 4.Remove a product
- 5.Delete all products
- 6.Exit

Just run the 'Main' class and pick whatever you like to do with the database.

## Example
Product Management Menu:
1. Display all products
2. Update product price
3. Update product quantity
4. Remove a product
5. Delete all products
6. Exit
Enter your choice: 1

Displaying all products:
Product{id=1, name='DemoApple', price=1.5, quantity=200, origin='USA'}
Product{id=2, name='DemoBanana', price=0.75, quantity=200, origin='Brazilia'}

## Configuration
Update the database credentials in the ProductService class if necessary. You may also add additional configuration for logging, error handling, etc.

## Running Tests
Go to test/java/ProductServiceTest and just run the test that you want to run, or right-click and run the whole class with all tests.

## Contributing

Fork the repository.
Create your feature branch (git checkout -b feature/AmazingFeature).
Commit your changes (git commit -m 'Add some AmazingFeature').
Push to the branch (git push origin feature/AmazingFeature).
Open a pull request.

## License
This project is licensed under the MIT License - see the LICENSE file for details.


## Contact
Email: b.hosu@ymail.com
GitHub: bogdanhosu
