CREATE SCHEMA IF NOT EXISTS empasset_db;
USE empasset_db;

-- USERS
CREATE TABLE users (
    id INT PRIMARY KEY AUTO_INCREMENT,
    username VARCHAR(100) NOT NULL,
    email VARCHAR(100) UNIQUE NOT NULL,
    password VARCHAR(255) NOT NULL,
    role ENUM('ADMIN','EMPLOYEE') NOT NULL
);

-- ADMIN
CREATE TABLE admin (
    id INT PRIMARY KEY AUTO_INCREMENT,
    admin_name VARCHAR(100),
    users_id INT,
    FOREIGN KEY (users_id) REFERENCES users(id)
);

-- EMPLOYEE
CREATE TABLE employee (
    id INT PRIMARY KEY AUTO_INCREMENT,
    employee_name VARCHAR(100),
    gender VARCHAR(20),
    phone VARCHAR(20),
    address VARCHAR(255),
    department VARCHAR(100),
    users_id INT,
    FOREIGN KEY (users_id) REFERENCES users(id)
);

-- ASSET CATEGORY
CREATE TABLE asset_category (
    id INT PRIMARY KEY AUTO_INCREMENT,
    category_name VARCHAR(100),
    description VARCHAR(255)
);

-- ASSET
CREATE TABLE asset (
    id INT PRIMARY KEY AUTO_INCREMENT,
    asset_no VARCHAR(50) UNIQUE NOT NULL,
    asset_name VARCHAR(100),
    category_id INT,
    asset_model VARCHAR(100),
    manufacturing_date DATE,
    expiry_date DATE,
    asset_value DECIMAL(10,2),
    image_url VARCHAR(255),
    asset_status ENUM('AVAILABLE','ALLOCATED','SERVICE'),
    FOREIGN KEY (category_id)
    REFERENCES asset_category(id)
);

-- ASSET ALLOCATION
CREATE TABLE asset_allocation (
    id INT PRIMARY KEY AUTO_INCREMENT,
    employee_id INT,
    asset_id INT,
    allocated_date DATE,
    return_date DATE,
    allocation_status ENUM('ALLOCATED','RETURNED'),
    FOREIGN KEY (employee_id)
    REFERENCES employee(id),
    FOREIGN KEY (asset_id)
    REFERENCES asset(id)
);

-- SERVICE REQUEST
CREATE TABLE service_request (
    id INT PRIMARY KEY AUTO_INCREMENT,
    asset_id INT,
    employee_id INT,
    issue_type ENUM('MALFUNCTION','REPAIR'),
    description VARCHAR(255),
    service_status ENUM('OPEN','IN_PROGRESS','COMPLETED'),
    FOREIGN KEY (asset_id)
    REFERENCES asset(id),
    FOREIGN KEY (employee_id)
    REFERENCES employee(id)
);