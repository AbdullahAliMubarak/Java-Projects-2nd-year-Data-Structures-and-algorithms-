select round((count(case when country = 'USA' then 1 end) * 100.0 / count(*)), 4) as percentUSA
from offices;