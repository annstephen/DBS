# Select the names, phone numbers and dates of visits for patients who made a visit during October (general query)
select p.name, p.contact_no, v.date_of_visit from hospital.patient p, hospital.visitation v where p.patient_registration_no = v.patient_registration_no 
and v.date_of_visit >= '2013-09-01' and v.date_of_visit <'2013-11-01';
#Select the name and certification numbers if doctors who made consultations(join and general query)
select name, certification_no, date_of_visit from hospital.employees natural join hospital.physician natural join hospital.consulted  where date_of_visit >= '2013-10-01' and date_of_visit < '2013-11-01' and specialization = 'Pediatrics';
#Select medicines that have an expiry date later than the beginning of October(join and general query)
select medicine_name, batch_id, expiry_date from Hospital.medicine natural join Hospital.Pharmaceutical_product natural join Hospital.production natural join Hospital.batch where expiry_date <'2013-10-01';
#Select names of nurses and radiologists who work the evening or morning shift (Union)
(select name from hospital.employees natural join hospital.takes where shift_name='evening' and (designation='Nurse' or designation='Radiologist')) union
(select name from hospital.employees natural join hospital.takes where shift_name='morning' and (designation='Radiology' or designation='Nurse'));
#Show the latest manufacture date of each product(Join, group by and aggregate function)
select product_id, max(manufacture_date) from Hospital.production natural join Hospital.batch group by(product_id);
#Select the latest date of visit for each patient(Aggregate and group by)
select name, max(date_of_visit) from Hospital.patient natural join Hospital.visitation group by(patient_registration_no);
#Select distinct values of each doctor having treated a patient(general query)
select distinct(e.name), p.name from Hospital.employees e, Hospital.patient p, Hospital.consulted c where c.employee_number = e.employee_number and 
 c.patient_registration_no = p.patient_registration_no;
# Select the latest patients who have consulted a cardiologist(Order by, distinct)
select distinct(p.name) from Hospital.patient p, Hospital.consulted c, Hospital.physician d where p.patient_registration_no = c.patient_registration_no
 and c.employee_number = d.employee_number and d.specialization = 'Cardiology'  order by (c.date_of_visit) desc;
#Select the of the head of department of the theraputic services (general query)
select e.name from Hospital.department d, Hospital.employees e where e.employee_number = d.head_of_department and d.department_name='Theraputic Services'; 
#Select the number people in the radiology department who work evening shifts(aggregate function)
select count(*) from Hospital.takes t, Hospital.employees e where e.employee_number = t.employee_number and e.designation = 'Radiology' and t.shift_name = 'evening';