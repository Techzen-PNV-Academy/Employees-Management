CREATE TABLE employee
(
    id            INT AUTO_INCREMENT NOT NULL,
    name          VARCHAR(255)       NULL,
    dob           date               NULL,
    gender        SMALLINT           NULL,
    salary        DOUBLE             NOT NULL,
    phone         VARCHAR(255)       NULL,
    department_id INT                NULL,
    CONSTRAINT pk_employee PRIMARY KEY (id),
    CONSTRAINT fk_department FOREIGN KEY (department_id) REFERENCES department(id)
);