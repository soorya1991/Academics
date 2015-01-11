users_data = LOAD '/Fall2014_HW-3-Pig/users_new.dat' using PigStorage('#') as (userid:chararray,gender:chararray,age:int,occupation:int,zipcode:int);
ratings_data = load '/Fall2014_HW-3-Pig/ratings_new.dat' using PigStorage('#') as(usrid:chararray,movid:chararray,rating:double,timestamp:chararray);
grpbyusers = cogroup users_data by (userid),ratings_data by (usrid);
join = foreach grpbyusers generate flatten(users_data), flatten (ratings_data);
limitusers = limit join 11;
dump limitusers;
		
