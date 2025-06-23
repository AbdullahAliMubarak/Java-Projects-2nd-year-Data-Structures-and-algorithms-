select officeCode, Count(*) as numEmps
from employees
group by officeCode;