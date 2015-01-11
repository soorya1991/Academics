REGISTER /home/004/s/sx/sxm120032/Pig_Format.jar;
movies = load '/Fall2014_HW-3-Pig/movies_new.dat' using PigStorage('#') as (movieid:chararray, moviename:chararray, genre:chararray);
--dump movies;
moviedetails = FOREACH movies GENERATE movieid,moviename, Pig_Format(genre);
DUMP moviedetails;

