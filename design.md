# The One SDK design

## Overview

_The One SDK_ is an SDK for the Lord of the Rings API at https://the-one-api.dev.

## Design

My intial thoughts on the design for this SDK is to create classes for each specific API Route grouping. For example, The One API has 5 API Route groupings (/book, /movie, /character, /quote, /chapter). For this project it was instructed to only include one API endpoint from the book API route grouping so I would only create one class (BookAPI.java). I chose to use the /book endpoint which will return the entire list of Lord of the Rings books. I identified that the requests to _The One API_ will contain a set of data common to all of the requests. This data includes Bearer Token, Pagination, Sorting, and Filtering. My plan is to create a abstract request class that can be extended by each of the API classes. The API classes will then use the common data and methods to make the request to the API endpoints within its API Route groupings.

### Classes

**TheOneAPIRequest.java**
An abstract class that will be extended by each API class. This class uses the Builder design paradigm to build the API Request based on the information that is set on the class. The user is able to specify information for the request such as a Bearer Token, Pagination, Sorting, and Filtering. This information is then used when the child API class calls the getURL method. The information is parsed and added to the request URL that will be sent to _The One API_.

**BookAPI.java**
An API request class for the book API route group on _The One API_. This class would contain the methods that correlate to any book related API requests. In this project it contains the getAllBooks method that will submit the GET request to _The One API_, parse the response data, and return the list of objects to the user.

**Book.java**
A POJO class for the Book object returned by the Book API requests. This is a simple object containing the ID and name of the book.

## Conclusion

If I were to continue this project there would be a few improvements I would like to make:

- I would like to improve how a user would be able to specify filtering and sorting information. Currently the user would need to provide the sorting and filtering query parameters in the 'additionalQueryParams' attribute on the parent APIRequest class. One problem that would need to be considered is how multiple filters/sorting options would be handled on the same attribute. Ideally the user would be able to add/remove/clear a list of filtering/sorting options through simple methods and the logic and validation would be handled by the parent API request class.

- When adding additional API classes and methods I would most likely abstract some of the logic for creating the Java HTTP connection as there would be a lot of duplicated code.

- I would like to create a response class that could handle the pagination for the user. The user would be able to automatically request the next page of data for the user based on the pagination information returned by the request. I would make this paginated response a generic class where we can specify what type of data would be returned within the paginated response such as book, movie, character, etc.
