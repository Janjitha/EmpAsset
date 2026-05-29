-- -----------------------------------------------------
-- Schema empasset_db
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `empasset_db` DEFAULT CHARACTER SET utf8;
USE `empasset_db`;

-- -----------------------------------------------------
-- 1. Table `users`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `users` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `username` VARCHAR(100) NOT NULL,
  `password` VARCHAR(255) NOT NULL,
  `email` VARCHAR(100) NOT NULL,
  `role` ENUM('admin', 'employee') NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `email_UNIQUE` (`email` ASC)
) ENGINE = InnoDB;

-- -----------------------------------------------------
-- 2. Table `admin`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `admin` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `admin_name` VARCHAR(100) NULL,
  `users_id` INT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_admin_users_idx` (`users_id` ASC),
  CONSTRAINT `fk_admin_users`
    FOREIGN KEY (`users_id`)
    REFERENCES `users` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION
) ENGINE = InnoDB;

-- -----------------------------------------------------
-- 3. Table `employee`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `employee` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `employee_name` VARCHAR(100) NULL,
  `gender` VARCHAR(20) NULL,
  `phone` VARCHAR(20) NULL,
  `address` VARCHAR(255) NULL,
  `department` VARCHAR(100) NULL,
  `users_id` INT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_employee_users_idx` (`users_id` ASC),
  CONSTRAINT `fk_employee_users`
    FOREIGN KEY (`users_id`)
    REFERENCES `users` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION
) ENGINE = InnoDB;

-- -----------------------------------------------------
-- 4. Table `asset_category`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `asset_category` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `category_name` VARCHAR(100) NOT NULL,
  `description` VARCHAR(255) NULL,
  PRIMARY KEY (`id`)
) ENGINE = InnoDB;

-- -----------------------------------------------------
-- 5. Table `asset`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `asset` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `asset_no` VARCHAR(50) NOT NULL,
  `asset_name` VARCHAR(100) NULL,
  `category_id` INT NOT NULL,
  `asset_model` VARCHAR(100) NULL,
  `manufacturing_date` DATE NULL,
  `expiry_date` DATE NULL,
  `asset_value` DECIMAL(10,2) NULL,
  `image_url` VARCHAR(255) NULL,
  `asset_status` ENUM('AVAILABLE','ALLOCATED','SERVICE') NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `asset_no_UNIQUE` (`asset_no` ASC),
  INDEX `fk_asset_category_idx` (`category_id` ASC),
  CONSTRAINT `fk_asset_category`
    FOREIGN KEY (`category_id`)
    REFERENCES `asset_category` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION
) ENGINE = InnoDB;

-- -----------------------------------------------------
-- 6. Table `asset_request`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `asset_request` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `employee_id` INT NOT NULL,
  `asset_id` INT NOT NULL,
  `request_date` DATE NULL,
  `request_status` ENUM('PENDING','APPROVED','REJECTED') NULL,
  `remarks` VARCHAR(255) NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_request_employee_idx` (`employee_id` ASC),
  INDEX `fk_request_asset_idx` (`asset_id` ASC),
  CONSTRAINT `fk_request_employee`
    FOREIGN KEY (`employee_id`)
    REFERENCES `employee` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_request_asset`
    FOREIGN KEY (`asset_id`)
    REFERENCES `asset` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION
) ENGINE = InnoDB;

-- -----------------------------------------------------
-- 7. Table `asset_allocation`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `asset_allocation` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `employee_id` INT NOT NULL,
  `asset_id` INT NOT NULL,
  `allocated_date` DATE NULL,
  `return_date` DATE NULL,
  `allocation_status` ENUM('ALLOCATED','RETURNED') NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_allocation_employee_idx` (`employee_id` ASC),
  INDEX `fk_allocation_asset_idx` (`asset_id` ASC),
  CONSTRAINT `fk_allocation_employee`
    FOREIGN KEY (`employee_id`)
    REFERENCES `employee` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_allocation_asset`
    FOREIGN KEY (`asset_id`)
    REFERENCES `asset` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION
) ENGINE = InnoDB;

-- -----------------------------------------------------
-- 8. Table `service_request`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `service_request` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `asset_id` INT NOT NULL,
  `employee_id` INT NOT NULL,
  `issue_type` ENUM('MALFUNCTION','REPAIR') NOT NULL,
  `description` VARCHAR(255) NULL,
  `service_status` ENUM('OPEN','IN_PROGRESS','COMPLETED') NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_service_asset_idx` (`asset_id` ASC),
  INDEX `fk_service_employee_idx` (`employee_id` ASC),
  CONSTRAINT `fk_service_asset`
    FOREIGN KEY (`asset_id`)
    REFERENCES `asset` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_service_employee`
    FOREIGN KEY (`employee_id`)
    REFERENCES `employee` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION
) ENGINE = InnoDB;

-- -----------------------------------------------------
-- 9. Table `asset_audit`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `asset_audit` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `employee_id` INT NOT NULL,
  `asset_id` INT NOT NULL,
  `audit_date` DATE NULL,
  `audit_status` ENUM('PENDING','VERIFIED','REJECTED') NULL,
  `remarks` VARCHAR(255) NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_audit_employee_idx` (`employee_id` ASC),
  INDEX `fk_audit_asset_idx` (`asset_id` ASC),
  CONSTRAINT `fk_audit_employee`
    FOREIGN KEY (`employee_id`)
    REFERENCES `employee` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_audit_asset`
    FOREIGN KEY (`asset_id`)
    REFERENCES `asset` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION
) ENGINE = InnoDB;

-- -----------------------------------------------------
-- SAMPLE DATA
-- -----------------------------------------------------

-- Asset Categories
INSERT INTO `asset_category` (`category_name`, `description`) VALUES
('Laptop', 'Company laptops'),
('Furniture', 'Office furniture'),
('Mobile', 'Mobile devices');

-- Users
INSERT INTO `users` (`username`, `password`, `email`, `role`) VALUES
('admin_user', 'admin123', 'admin@empasset.com', 'admin'),
('john_emp', 'emp123', 'john@empasset.com', 'employee'),
('alice_emp', 'emp123', 'alice@empasset.com', 'employee');

-- Admin
INSERT INTO `admin` (`admin_name`, `users_id`) VALUES
('System Admin', 1);

-- Employees
INSERT INTO `employee`
(`employee_name`, `gender`, `phone`, `address`, `department`, `users_id`)
VALUES
('John Doe', 'Male', '9876543210', 'Chennai', 'IT', 2),
('Alice Smith', 'Female', '9123456780', 'Bangalore', 'HR', 3);

-- Assets
INSERT INTO `asset`
(`asset_no`, `asset_name`, `category_id`, `asset_model`,
 `manufacturing_date`, `expiry_date`, `asset_value`,
 `image_url`, `asset_status`)
VALUES
('LAP101', 'Dell Latitude', 1, 'Dell 5420',
 '2024-01-10', '2028-01-10', 75000,
 'dell.jpg', 'ALLOCATED'),

('MOB201', 'iPhone 14', 3, 'Apple',
 '2024-02-15', '2027-02-15', 65000,
 'iphone.jpg', 'AVAILABLE');

-- Asset Request
INSERT INTO `asset_request`
(`employee_id`, `asset_id`, `request_date`,
 `request_status`, `remarks`)
VALUES
(1, 1, CURDATE(),
 'APPROVED', 'Asset approved by admin');

-- Asset Allocation
INSERT INTO `asset_allocation`
(`employee_id`, `asset_id`, `allocated_date`,
 `return_date`, `allocation_status`)
VALUES
(1, 1, '2025-08-01', NULL, 'ALLOCATED');

-- Service Request
INSERT INTO `service_request`
(`asset_id`, `employee_id`, `issue_type`,
 `description`, `service_status`)
VALUES
(1, 1, 'REPAIR',
 'Laptop screen flickering',
 'OPEN');

-- Asset Audit
INSERT INTO `asset_audit`
(`employee_id`, `asset_id`, `audit_date`,
 `audit_status`, `remarks`)
VALUES
(1, 1, CURDATE(),
 'PENDING', 'Awaiting employee verification');

-- -----------------------------------------------------
-- SAMPLE QUERY
-- -----------------------------------------------------

-- View allocated assets for employees
SELECT e.employee_name,
       a.asset_name,
       aa.allocated_date,
       aa.allocation_status
FROM asset_allocation aa
JOIN employee e ON aa.employee_id = e.id
JOIN asset a ON aa.asset_id = a.id;