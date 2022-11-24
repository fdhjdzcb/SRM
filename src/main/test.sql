select u.username, ur.role_name
from users u
         inner join roles ur on u.id = ur.role_id
where u.username='11111111'