var map_3_2 = function(){
  emit(this.stock_symbol,{count:1,sum:this.stock_price_high});
};

var red_3_2 = function(key,values){
  var redVal = {count:0,sum:0}
  for(var i=0;i<values.length;i++){
    redVal.sum += values[i].sum;
    redVal.count+=values[i].count;
  }
  return redVal;
}

var fin_3_2 = function(key,redVal){
  redVal.avg = redVal.sum/redVal.count;
  return redVal;
}

db.nyse.mapReduce(map_3_2,
  red_3_2,
  {
    out:"part_3_2_avg",
    finalize : fin_3_2
  }
)
