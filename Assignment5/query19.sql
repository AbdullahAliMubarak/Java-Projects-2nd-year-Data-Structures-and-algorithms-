select customerNumber
from payments
where amount = (select max(amount) from payments);