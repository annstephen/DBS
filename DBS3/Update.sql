#delete the batches of products that expired before October and are medicines
delete from Hospital.batch where expiry_date < '2013-10-01' and batch_id in (select distinct(b.batch_id) from Hospital.production b, Hospital.medicine m 
where b.product_id = m.product_id);
#delete treatments made by memebers of the nurses on night shifts
delete from Hospital.treated_by where employee_number in (select e.employee_number from Hospital.employees e, Hospital.takes t where t.shift_name = 'night' 
and e.employee_number = t.employee_number and e.designation = 'Nurse');

#update the head of department of the theraputic department such that it is the employee with the smallest employee number of the cardiology department
update Hospital.department set head_of_department = (select min(employee_number) from Hospital.physician natural join Hospital.employees where specialization='Cardiology') where department_name = 'Theraputic Services';
#update all members of the nursing staff such that those in the morning shift are now in the afternoon shift
update Hospital.takes set shift_name = 'afternoon' where shift_name='morning' and employee_number in (select employee_number from Hospital.employees where designation = 'Nurse');

#insert into the takes table an additional evening shift to all radiologists working morning
insert into Hospital.takes(employee_number, shift_name) select e.employee_number,'evening' from Hospital.takes t, Hospital.employees e where 
e.employee_number = t.employee_number and t.shift_name = 'morning' and e.designation = 'Radiology' ;
#insert into consultation for the first patient(sorted by registration number) in the patients list with the first cardiologist on their physician realtion(sorted by employee_number)
insert into Hospital.consulted(employee_number, certification_no, patient_registration_no, date_of_visit) select d.employee_number, d.certification_no, v.patient_registration_no,
 v.date_of_visit from Hospital.physician d, Hospital.visitation v where d.employee_number = (select min(employee_number)from Hospital.physician
where specialization = 'Cardiology') and v.date_of_visit = (select min(date_of_visit) from Hospital.visitation where patient_registration_no = v.patient_registration_no )
and v.patient_registration_no = (select min(patient_registration_no) from hospital.patient);