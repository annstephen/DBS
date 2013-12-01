# This program is used to generate the file insertSyntheticData.sql, that is used to insert values into the Hospital schema
import string, random
from datetime import date
# The designation of employees, specializations of Doctors and Shifts are given in a list. Random values are selected from this list. 
# Also a list is used to determine if a product is a medicine or not
designation = ['Doctor','Nurse','Administration','Radiology']
specialization = ['Anesthesiology','Cardiology','Dermatology','Pediatrics']
shift = ['morning','evening','night']
Medicine = ['y','n']
insertEmployee = ''
insertDoctor = ''
# Opening the file and writing shift values into it. We assume there are only 4 values for shift, hence it is hardcoded
f = open('insertSyntheticData.sql','w')
f.write('insert into Hospital.Shift(shift_name,begin_time,end_time) values (\'morning\',\'06:00:00\',\'12:00:00\');\n')
f.write('insert into Hospital.Shift(shift_name,begin_time,end_time) values (\'afternoon\',\'12:00:00\',\'18:00:00\');\n')
f.write('insert into Hospital.Shift(shift_name,begin_time,end_time) values (\'evening\',\'18:00:00\',\'00:00:00\');\n')
f.write('insert into Hospital.Shift(shift_name,begin_time,end_time) values (\'night\',\'00:00:00\',\'06:00:00\');\n')
# The flags are used to determine if the department's have been inserted, The first entry for Doctor, Administrator, Radiology and Nurse
# Are taken as the heads of the 4 departments
flagDoc = 0
flagAdmin = 0
flagDiag = 0
flagSup = 0
# These lists are inserted into, so valid entries for doctor's, nurses and Medicines can be kept track of for referential relationship
Doctors =[]
Nurse = []
Med = []
start_date = date.today().replace(day=1, month=1).toordinal()
end_date = date.today().toordinal()
#Here the product, employee and patient relations take about 500 values, the visitation and the batch table can have up to 2500 values
for count in range(500):
	#insert into product
	f.write('insert into Hospital.Pharmaceutical_product(product_id,cost,manufacturer) values('+str(count)+','+str(random.uniform(10,1000))+',\''+''.join(random.choice(string.ascii_lowercase) for x in range(5))+'\');\n')
	BatchBegin = str(date.fromordinal(random.randint(start_date, end_date)))
	BatchEnd = str(date.fromordinal(random.randint(start_date, end_date)))
	if BatchBegin < BatchEnd :
		for count3 in range(5):
			#insert into batch relation and the corresponding relation for join between product and batch
			f.write('insert into Hospital.Batch(batch_id,manufacture_date,expiry_date) values ('+str(count3+count)+',\''+BatchBegin+'\',\''+BatchEnd+'\');\n')
			f.write('insert into Hospital.Production(product_id,batch_id) values ('+str(count)+','+str(count3+count)+');\n')
	if random.choice(Medicine) == 'y' :
		#If product is a medicine insert into table
		f.write('insert into Hospital.Medicine(product_id,medicine_name,dosage) values ('+str(count)+','+'\''+''.join(random.choice(string.ascii_lowercase) for x in range(5))+'\',\''+''.join(random.choice(string.ascii_lowercase) for x in range(5))+'\');\n')
		Med.append(str(count))
	desg = random.choice(designation)
	#Insert into employee table
	insertEmployee = 'insert into Hospital.Employees (employee_number, name, location, ssn, home_address,phone_number,designation) values ( ' + str(count)+',\''+''.join(random.choice(string.ascii_lowercase) for x in range(5))+'\','+'\''+''.join(random.choice(string.ascii_lowercase) for x in range(15))+'\','+str(random.randint(10000000,999999999))+',\''+''.join(random.choice(string.ascii_lowercase) for x in range(5))+'\','+str(random.randint(10000000,99999999)) +',\''+desg+'\');\n'	
	f.write(insertEmployee)
	insertPatient = 'insert into Hospital.Patient (patient_registration_no, name, ssn, address,contact_no,emergency_contact_no) values ( ' + str(count)+',\''+''.join(random.choice(string.ascii_lowercase) for x in range(5))+'\','+str(random.randint(10000000,999999999))+',\''+''.join(random.choice(string.ascii_lowercase) for x in range(5))+'\','+str(random.randint(10000000,99999999)) +','+str(random.randint(10000000,99999999))+');\n'	
	for visitcnt in range(5):
		# A random date is used for the visitation table, it is kept as it needs to be referenced to in other relations
		randDate = str(date.fromordinal(random.randint(start_date, end_date)))
		f.write('insert into Hospital.Visitation(patient_registration_no,date_of_visit,diagnosis) values ('+str(count)+',\''+randDate+'\','+'\''+''.join(random.choice(string.ascii_lowercase) for x in range(5))+'\');\n')
		# Insert into consulted, prescribed and treated by, picking random values from the doctor list, medicine list and the nurse list
		if Doctors:
			docNum = random.choice(Doctors)
			f.write('insert into Hospital.Consulted(employee_number,certification_no,patient_registration_no,date_of_visit) values('+docNum+','+docNum+'112233,'+str(count)+',\''+randDate+'\');\n')
		if Nurse:
			treatNum = random.choice(Nurse)
			f.write('insert into Hospital.Treated_by(patient_registration_no,date_of_visit,employee_number,treatment) values('+str(count)+',\''+randDate+'\','+treatNum+');\n')
		if Med:
			MedNum = random.choice(Med)
			f.write('insert into Hospital.Prescribed(product_id,patient_registration_no,date_of_visit) values ('+MedNum+','+str(count)+',\''+randDate+'\');\n')
	f.write(insertPatient)
	insertPatient=''
	insertEmployee=''
	# If the designation is doctor enter into the physician table, also if it is the first doctor make them head of department, and add them in the join between employee and department
	if desg == designation[0] :
		if flagDoc == 0:
			f.write('insert into Hospital.Department(department_id,department_name,head_of_department) values (1,\'Theraputic Services\','+str(count)+');\n')
		insertDoctor = 'insert into Hospital.Physician (employee_number, certification_no,specialization) values ( ' + str(count)+','+str(count)+'112233'+',\''+random.choice(specialization) +'\');\n'
		f.write(insertDoctor)
		insertDoctor = ''
		f.write('insert into Hospital.Belongs_to(employee_number,department_id) values ('+str(count)+',1);\n')
		flagDoc = 1
		Doctors.append(str(count))
	# If in administration, make an entry in the belongs_to table. If they are the first entry in the department, make them the head of department
	if desg == designation[2]:
		if flagAdmin ==0:
			f.write('insert into Hospital.Department(department_id,department_name,head_of_department) values (2,\'Administrative Services\','+str(count)+');\n')
			flagAdmin = 1
		f.write('insert into Hospital.Belongs_to(employee_number,department_id) values ('+str(count)+',2);\n')
	# If in radiology, make an entry in the belongs_to table in the diagnosis department. If they are the first entry in the department, make them the head of department
	if desg == designation[3] and flagDiag == 0:
		f.write('insert into Hospital.Department(department_id,department_name,head_of_department) values (4,\'Diagnosis Services\','+str(count)+');\n')
		flagDiag = 1
		f.write('insert into Hospital.Belongs_to(employee_number,department_id) values ('+str(count)+',4);\n')
	# If in nursing, make an entry in the belongs_to table for supporting services. If they are the first entry in the department, make them the head of department
	# Also add random shift to each entry
	if desg == designation[1] or desg == designation[3]:
		insertShift = 'insert into Hospital.Takes (employee_number,shift_name ) values ( ' + str(count)+',\''+random.choice(shift) +'\');\n'
		f.write(insertShift)
		insertShift = ''
		f.write('insert into Hospital.Belongs_to(employee_number,department_id) values ('+str(count)+',3);\n')
		if flagSup == 0 and desg == designation[3]:
			f.write('insert into Hospital.Department(department_id,department_name,head_of_department) values (3,\'Supporting Services\','+str(count)+');\n')	
			flagSup = 1
		if desg == designation[3]:
			Nurse.append(str(count))
f.close();
# This code successfully generates insert statements for all the relations in the Hospital schema. One problem that does arise is that since the date is generated randomly
# it can cause duplicate entries. In such case the entries are ignored.