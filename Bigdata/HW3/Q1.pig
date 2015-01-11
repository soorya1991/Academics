movies_data = LOAD '/Fall2014_HW-3-Pig/movies_new.dat' using PigStorage('#') as (movieid:chararray,title:chararray,genres:chararray); 
action_war_movies = filter movies_data by (genres matches '.*Action.*' and genres matches '.*War.*');
ratings_data = load '/Fall2014_HW-3-Pig/ratings_new.dat' using PigStorage('#') as(userid:chararray,movid:chararray,rating:double,timestamp:chararray);
joined_movie_rating = JOIN action_war_movies by movieid, ratings_data by movid;
grpbymovieid = group joined_movie_rating by $0;
flat = FOREACH grpbymovieid GENERATE group, AVG(joined_movie_rating.rating) as avgRat,joined_movie_rating.userid as UserID;
high = order flat by avgRat desc;
highrat = limit high 1;
movieHighRat =  foreach highrat generate flatten(highrat.UserID);
users_data = LOAD '/Fall2014_HW-3-Pig/users_new.dat' using PigStorage('#') as (userid:chararray,gender:chararray,age:int,occupation:int,zipcode:int);
female_users = FILTER users_data  BY (age >= 20 AND age <= 30) AND (gender MATCHES 'F');
female_movies = JOIN female_users BY $0, movieHighRat BY $0;
final_output = FOREACH female_movies GENERATE female_users::userid,zipcode;
distinct_output = distinct final_output;
temp_output = FILTER distinct_output by $1 MATCHES '1*';
final_output = FOREACH temp_output GENERATE userid;
dump distinct_output;

