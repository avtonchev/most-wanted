# Most Wanted App

The application imports hard-formatted data from XML- and JSON files into MySQL database.
the following entities are imported from JSON file:
- Town
- District
- Racer
- Car

from XML:
- Race
- RaceEntry
Once data is imported, on the home screen there are two options for data export:

### Racing Towns 
Export all towns which have any racers in them<br> <br> 
<i>Export Constraints:</i>
1. Exports only the town name (as name) and count of racers (as racers).
2.	Orders them descending, by count of racers they have, and then by town name alphabetically.

### Racing Cars
 Export all racers which have any cars<br> <br> 
<i>Export Constraints:</i>
1.	Exports the racer’s name, age, list of cars.
2.	In case the racer’s age property is NULL, do NOT include it.
3.	The cars are strings in the following format: “{brand} {model} {yearOfProduction}”. 
4.	Orders them descending, by count of cars they have, and then by racer name alphabetically. 

## Usage

## Built With
MVP Pattern
Spring Boot 2.1.0
Spring Data
Thymeleaf
Google Gson
JAXB (Java Architecture for XML Binding)
ModelMapper
