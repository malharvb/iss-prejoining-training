USE mysql_jdbc;

INSERT INTO candidate (id, first_name, last_name, dob, phone, email) VALUES
(1, 'Amit', 'Sharma', '1985-01-01', '(91) 98765-43210', 'amit.sharma@gmail.com'),
(2, 'Priya', 'Singh', '1986-02-02', '(91) 98765-43211', 'priya.singh@gmail.com'),
(3, 'Rajesh', 'Kumar', '1987-03-03', '(91) 98765-43212', 'rajesh.kumar@gmail.com'),
(4, 'Sunita', 'Patel', '1988-04-04', '(91) 98765-43213', 'sunita.patel@gmail.com'),
(5, 'Vikram', 'Reddy', '1989-05-05', '(91) 98765-43214', 'vikram.reddy@gmail.com'),
(6, 'Anjali', 'Nair', '1990-06-06', '(91) 98765-43215', 'anjali.nair@gmail.com'),
(7, 'Rohan', 'Mehta', '1991-07-07', '(91) 98765-43216', 'rohan.mehta@gmail.com'),
(8, 'Sneha', 'Gupta', '1992-08-08', '(91) 98765-43217', 'sneha.gupta@gmail.com'),
(9, 'Arjun', 'Joshi', '1993-09-09', '(91) 98765-43218', 'arjun.joshi@gmail.com'),
(10, 'Kavita', 'Verma', '1994-10-10', '(91) 98765-43219', 'kavita.verma@gmail.com'),
(11, 'Sandeep', 'Pawar', '1985-11-11', '(91) 98765-43220', 'sandeep.pawar@gmail.com'),
(12, 'Neha', 'Kulkarni', '1986-12-12', '(91) 98765-43221', 'neha.kulkarni@gmail.com'),
(13, 'Vivek', 'Desai', '1987-01-13', '(91) 98765-43222', 'vivek.desai@gmail.com'),
(14, 'Pooja', 'Chopra', '1988-02-14', '(91) 98765-43223', 'pooja.chopra@gmail.com'),
(15, 'Manoj', 'Dubey', '1989-03-15', '(91) 98765-43224', 'manoj.dubey@gmail.com'),
(16, 'Rekha', 'Yadav', '1990-04-16', '(91) 98765-43225', 'rekha.yadav@gmail.com'),
(17, 'Ashok', 'Bansal', '1991-05-17', '(91) 98765-43226', 'ashok.bansal@gmail.com'),
(18, 'Meena', 'Saxena', '1992-06-18', '(91) 98765-43227', 'meena.saxena@gmail.com'),
(19, 'Rakesh', 'Aggarwal', '1993-07-19', '(91) 98765-43228', 'rakesh.aggarwal@gmail.com'),
(20, 'Divya', 'Mishra', '1994-08-20', '(91) 98765-43229', 'divya.mishra@gmail.com');

INSERT INTO skill (id, skill_name) VALUES
(1, 'Java'),
(2, 'Python'),
(3, 'SQL'),
(4, 'JavaScript');


INSERT INTO candidate_skill (candidate_id, skill_id) VALUES
(1, 1),
(1, 2),
(2, 1),
(2, 3),
(20, 3),
(20, 4);
