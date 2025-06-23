select productCode from products
where productCode not in (select productCode from orderdetails);