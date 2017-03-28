# java-schiphol-api
A wrapper for the Schiphol API written in Java

## Usage

### Installing

#### Maven

Add jitpack to your repositories:
```xml
<repository>
    <id>jitpack.io</id>
    <url>https://jitpack.io</url>
</repository>
```

Add the dependancy to the dependancies:
```xml
<dependency>
      <groupId>com.github.ThomasKluiters</groupId>
      <artifactId>java-schiphol-api</artifactId>
      <version>v1.0.3</version>
</dependency>
```

### Initialization

```java
SchipholCredentials credentials = SchipholCredentialsUtil.loadFrom("secrets.json");
Schiphol schiphol = new Schiphol(credentials);
```

where secrets.json has the following format:
```json
{
  "id" : "your_id",
  "key" : "your_key"
}
```

or

```java
Schiphol schiphol = new Schiphol("app_id", "app_key");
```

### Easily find all aircraft types

Easily iterate over query results with Iterator and Pagination support.

```Java
AircraftTypes types = schiphol.aircraft().execute();

for (AircraftTypes aircraftTypes : types.all()) {
	for (AircraftType aircraftType : aircraftTypes) {
	    System.out.println(aircraftType.getLongDescription());
	}
}
```

### Navigate results with ease

```Java
Flights flights = schiphol.flights()
    .airline("KL")
    .execute();

Flights nextFlights = flights.next();
Flights lastFlights = nextFlights.last();
Flights prevFlights = lastFlights.previous();
Flights firstFlights = prevFlights.first();
```

### List Flights

```Java
// first sort on date, ascending, then sort on time, descending
SortBuilder sort = new SortBuilder()
	.field("scheduledate").ascending()
	.field("scheduletime").descending();

// find all flights with airline "KL", departing
Flights flights = schiphol.flights()
	.airline("KL")
	.direction(FlightDirection.DEPARTING)
	.sort(sort)
    .execute();
```

### Find single Flight

```Java
// find flight with ID 121405342663252830
Flight flight = schiphol.flight()
      .id(121405342663252830)
    .execute();
```

### List aircraft types

```Java
AircraftTypes types = schiphol.aircraft()
    .execute();
```

### List all destinations

```Java
Destinations destinations = schiphol.destinations()
    .execute();
```

### Find destination

```Java
Destination destination = schiphol.destination()
        .iata("AMS")
    .execute();
```

### Iteration

```Java
Airlines airlines = schiphol.airlines().execute();

for(Airline airline : airlines) {
      System.out.println(airline.getPublicName());
}
```

### Pagination

```Java
Airlines airlines = schiphol.airlines().execute();

if(airlines.hasNext()) {
      airlines = airlines.next();
}
```

