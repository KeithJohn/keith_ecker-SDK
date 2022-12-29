# The One API Client Library for Java

**By Keith Ecker**

## Instalation

Add this dependency to your project's POM:

```xml
<dependency>
  <groupId>io.github.keithjohn</groupId>
  <artifactId>the-one-sdk</artifactId>
  <version>1.0.0</version>
</dependency>
```

Once the POM file is updated you can run the following command to install the dependency

```shell
    mvn clean install
```

## Getting Started

Before getting started please follow the [installation](#instalation) instructions

Once installed you can reference the API classes and methods for the desired request.

## Exmaples

**Get All Lord Of The Rings books**

```java
BookAPI bookAPI = new BookAPI();
List<Book> books = bookAPI.getAllBooks();
```

**Get All Lord Of The Rings books sorted by name in descending order**

```java
    BookAPI bookAPI = new BookAPI();
    String sortParams = "sort=name:desc";
    bookAPI.setAdditionalQueryParams(sortParams);
    List<Book> books = bookAPI.getAllBooks();
```

**Get All Lord Of The Rings books excluding The Two Towers**

```java
    BookAPI bookAPI = new BookAPI();
    String filterQueryParams = "name!=The Two Towers";
    bookAPI.setAdditionalQueryParams(filterQueryParams);
    List<Book> books = bookAPI.getAllBooks();
```
