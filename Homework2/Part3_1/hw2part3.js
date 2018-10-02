var part_3_1_map = function(){
  emit(this.stock_symbol,this.stock_price_high);
};


var part_3_1_red = function(key,values){
  var count = 0;
  var sum = 0;
  values.forEach(function(v){
    sum = sum + v;
    count++;
  })
  return sum/count;
}

db.nyse.mapReduce(part_3_1_map,part_3_1_red,{out:"part_3_1_avg"})
