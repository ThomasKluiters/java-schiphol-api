# java-schiphol-api
A wrapper for the Schiphol API written in Java

## TODO

- implement pagination
- refactor Builders
- test thoroughly
- verify sorting parameters

## Usage

### Installing

Using maven

```xml
<dependency>
      <groupId>com.github.ThomasKluiters</groupId>
      <artifactId>java-schiphol-api</artifactId>
</dependency>
```

### List Flights

```Java
// initalize APi
Schiphol schiphol = new Schiphol("my_app_id", "my_app_key");

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
// initalize APi
Schiphol schiphol = new Schiphol("my_app_id", "my_app_key");

// find flight with ID 121405342663252830
Flight flight = schiphol.flight()
      .id(121405342663252830)
    .execute();
```

### List all aircraft types

```Java
// initalize APi
Schiphol schiphol = new Schiphol("my_app_id", "my_app_key");

AircraftTypes types = schiphol.aircraft()
    .execute();
```

### List all destinations

```Java
// initalize APi
Schiphol schiphol = new Schiphol("my_app_id", "my_app_key");

Destinations destinations = schiphol.destinations()
    .execute();
```

### Find destination

```Java
// initalize APi
Schiphol schiphol = new Schiphol("my_app_id", "my_app_key");

Destination destination = schiphol.destination()
        .iata("AMS")
    .execute();
```

