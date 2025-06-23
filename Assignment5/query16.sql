select salesRepEmployeeNumber, count(*) AS numCustomers
from customers
where salesRepEmployeeNumber in (select employeeNumber from employees where reportsTo = 1143)
group by salesRepEmployeeNumber
having count(*) >= 10;