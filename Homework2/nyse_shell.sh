for x in {A..Z}
do
  mongoimport --db hw2db --collection nyse --type csv --headerline --file /Users/sgopalakrishna/Documents/NYSE/NYSE_daily_prices_${x}.csv
done
