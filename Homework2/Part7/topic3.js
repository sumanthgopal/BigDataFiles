var map_part7_3 = function(){
  emit(this.userid,1);
}

var red_part7_3 = function(key,values){
  return Array.sum(values)
}

db.movielensratings.mapReduce(
  map_part7_3,
  red_part7_3,
  {out:"users_ratings"}
)
