select od.orderNumber, o.status, od.priceEach, od.quantityOrdered, p.productName
from orderdetails od
join orders o on od.orderNumber = o.orderNumber
join products p on od.productCode = p.productCode
where productVendor= 'Exoto Designs';