users_data = LOAD '/Fall2014_HW-3-Pig/users_new.dat' using PigStorage('#') as (userid:chararray,gender:chararray,age:int,occupation:int,zipcode:int);
ratings_data = load '/Fall2014_HW-3-Pig/ratings_new.dat' using PigStorage('#') as(usrid:chararray,movid:chararray,rating:double,timestamp:chararray);
grpbyuserid = COGROUP users_data by (userid), ratings_data by (usrid);
limituserid = limit grpbyuserid 11;
DUMP limituserid;
