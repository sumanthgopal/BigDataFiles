var map_4 = function(){
  emit(this.stock_symbol,{
    count:1,
    sum_high:this.stock_price_high,
    sum_open:this.stock_price_open,
    sum_low:this.stock_price_low,
    sum_close:this.stock_price_close,
    sum_adj_close:this.stock_price_adj_close
  });
};

var red_4 = function(key,values){
  var redVal = {
    count:0,
    sum_high:0,
    sum_open:0,
    sum_low:0,
    sum_close:0,
    sum_adj_close:0
  }
  for(var i=0;i<values.length;i++){
    redVal.sum_high += values[i].sum_high;
    redVal.sum_open += values[i].sum_open;
    redVal.sum_low += values[i].sum_low;
    redVal.sum_close += values[i].sum_close;
    redVal.sum_adj_close += values[i].sum_adj_close;
    redVal.count+=values[i].count;
  }
  return redVal;
}

var fin_4 = function(key,redVal){
  redVal.avg_high = redVal.sum_high/redVal.count;
  redVal.avg_open = redVal.sum_open/redVal.count;
  redVal.avg_low = redVal.sum_low/redVal.count;
  redVal.avg_close = redVal.sum_close/redVal.count;
  redVal.avg_adj_close = redVal.sum_adj_close/redVal.count;
  return redVal;
}

db.nyse.mapReduce(map_4,
  red_4,
  {
    out:"part_4_avg",
    finalize : fin_4
  }
)
