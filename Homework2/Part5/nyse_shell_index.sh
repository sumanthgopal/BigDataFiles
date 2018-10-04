for x in {A..Z}
do
  mongoimport --db hw2dbindexpart6 --collection nyse --type csv --headerline --file /Users/sgopalakrishna/Documents/NYSE/NYSE_daily_prices_${x}.csv
done
