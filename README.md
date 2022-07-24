# Vending Machine Change Tracker

VendingMachineChangeTracker - is a part of VendingMachine application tracks change (as in coins) within the vending
machines.
Implementation is a dynamic programming algorithm with memorization wrapped in a spring-shell application.

### This component provides an API which:

* Initialise the vending machine to a known state, for use when the machine is
  set up. This should include setting the initial float (the coins placed in the
  machine for customer change) which should be accepted as a parameter.
* Register coins that have been deposited by a user
* Return the correct change to a user as coins when an order is received (a
  parameter for the value of a product) and remove the coins from the
  machine

#### Vending Machine Service Commands

* ii: Initialize the vending machine with items (example: ii -v items.csv or ii -v or ii -v default)
* ic: Initialize the vending machine with coins (example: ic -v coins.csv or ic -v or ic -v default)
* lk: Lookup Vending Machine inventory coins (example: lk)

#### Vending Machine User Commands

* add: Top up user balance by coin (example: add -v 50) accepted coin denominations are [5,10,20,50,100,200]
* get: Make order (example: get -v cola) [cola,crackers,snack,beer,crisp,cider]
* return: Return deposited coins (example: return)

### Init Files Description

#### Init Coin

CLI accepts *.csv files in format below (coins should be provided in denomination descending order)

|Denomination | Amount|
|------------- | -------------|
|TWO_POUND  | 50|
|ONE_POUND  | 50|
|FIFTY_PENCE  | 50|
|TWENTY_PENCE  | 50|
|TEN_PENCE  | 50|
|FIVE_PENCE  | 50|

#### Init Items

CLI accepts *.csv files in format below

| Denomination | Price | Amount|
|--------------|-------|-------|
| cola         | 50 | 4|
| crackers     | 140 | 4|
| snack        | 145 | 4|
| beer         | 190 | 4|
| crisp        | 200 | 4|
| cider        | 220 | 4|

### How to Run

* need to have Java17 installed on machine
* java -jar (path to *.jar file) example "java -jar vending-machine-change-tracker-0.0.1.jar"

### Scenario to play with

#### Default Init Machine Make order get change

* java -jar vending-machine-change-tracker-0.0.1.jar
* ic -v
* ii -v
* add -v 100
* return
* add -v 20
* mk -v cola
* add -v 50
* mk -v cola
* exit

#### Custom Init Machine Lookup inventory coins

* place coins.csv and items.csv files in same directory with *.jar file
* java -jar vending-machine-change-tracker-0.0.1.jar
* ic -v coins.csv
* ii -v items.csv
* lk
* exit
