/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 * Author:  ducna
 * Created: Feb 17, 2017
 */

CREATE TABLE crm.login(
    username VARCHAR(30),
    password VARCHAR(50),
    PRIMARY KEY (username)
)

INSERT INTO crm.login VALUES("ducna","1691991")

CREATE TABLE crm.company_loan(
	loan_id INTEGER, 
    company_name VARCHAR(30),
    loan_value VARCHAR (20),
    due_date DATE,
    PRIMARY KEY (loan_id)
)

INSERT INTO crm.company_loan(company_name,loan_value,due_date) VALUES("bidv","1110",NOW())