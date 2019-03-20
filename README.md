# assignment

A simple command line tool and RESTful service for processing some
data and printing it out in sorted order.

## Requirements

   * java
   * leiningen
   * curl
   * jq (optional, for pretty-printing the JSON output)

## Usage

There are two ways to use this repo.  One is to call it as
command-line application with a file as input to print out the sorted
data, and the other is to start the service, load data in, and make
requests for sorted data.

The direct command-line use is handy if you just want to pass a file
of data in and see it printed out in sorted order.  Also the direct
CLI supports composing sort arguments, whereas the REST service does
not.

The REST service usage will be more appropriate for those who wish to
accumulate a sizable amount of data over time, perhaps from different
users.  Also, since it responds with JSON, it's more convenient for
programs as consumers, such as web front ends.

Note: the service does not employ a persistent datastore, so any data
loaded into it will be lost when the service is stopped.

I've provided some scripts in `bin` for the direct use and the
service-based use at the command line.

### Using directly, without the REST API

To demonstrate usage, I have included in the project root directory
three example data files `resources/data.csv`, `resource/data.psv`,
and `resource/data.ssv` delimited by commas, pipes, and spaces
respectively.

```
cd $PROJECT_ROOT

# Example 1: sorted by gender (females before males)
# then by last name ascending.
./bin/sort-file resources/data.csv --gender asc --name asc

# Example 2: sorted by birth date, ascending
./bin/sort-file resources/data.csv --birthdate asc # print sorted by date of birth

# Example 3: sorted by last name, descending
./bin/sort-file resources/data.csv --name desc
```

### Using with the REST API

```
cd $PROJECT_ROOT

# in a shell, start the service
./bin/start

# in another shell,

# this helper script calls the POST endpoint for each line of the file
# to load all the data in
./bin/load resources/data.csv
./bin/load resources/data.psv
./bin/load resources/data.ssv

# Example 1: request records sorted by gender
./bin/sort-records gender

# Example 2: request records sorted by birthdate
./bin/sort-records birthdate

# Example 3: request records sorted by name
./bin/sort-records birthdate

```

## Tests

```
lein test
```
