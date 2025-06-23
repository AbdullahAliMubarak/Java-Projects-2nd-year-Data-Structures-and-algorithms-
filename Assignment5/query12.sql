select o.orderNumber, o.comments, c.customerName
from orders o
join customers c on o.customerNumber = c.customerNumber
where o.status = 'Disputed';