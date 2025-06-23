select o1.country, o1.officeCode as oneOffice, o2.officeCode as otherOffice
from offices o1, offices o2
where o1.country = o2.country and o1.officeCode < o2.officeCode;