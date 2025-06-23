select officeCode, count(*) as numReps
from employees
where jobTitle = 'Sales Rep'
group by officeCode;