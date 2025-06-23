select 'YES' as anyProblems
from dual
where exists (select 1 from orderdetails where priceEach = 0)
or exists (select 1 from products where buyPrice = 0);