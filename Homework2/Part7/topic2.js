var map_part7_2 = function(){
  emit(this.gender,1);
}

var red_part7_2 = function(key,values){
  var sum = 0;
  for(var i=0;i<values.length;i++){
    sum+=values[i];
  }
  return sum;
  //return Array.sum(values)
}

db.movielensusers.mapReduce(
  map_part7_2,
  red_part7_2,
  {out:"males_females"}
)
