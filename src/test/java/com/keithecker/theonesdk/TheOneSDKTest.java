package com.keithecker.theonesdk;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Test;

/**
 * Unit test for The One SDK
 */
public class TheOneSDKTest {

    @Test
    public void testGetAllBooks() {
        BookAPI bookAPI = new BookAPI();
        try {
            List<Book> books = bookAPI.getAllBooks();
            assertTrue("All three books are not returned", books.size() == 3);

            assertEquals("The Fellowship Of The Ring is not returned", books.get(0).getName(),
                    "The Fellowship Of The Ring");

            assertEquals("The Two Towers is not returned", books.get(1).getName(), "The Two Towers");

            assertEquals("The Return Of The King is not returned", books.get(2).getName(), "The Return Of The King");
        } catch (Exception e) {
            e.printStackTrace();
            assertTrue("Exception as occured during test execution", false);
        }
    }

    @Test
    public void testGetAllBooksPagination() {
        BookAPI bookAPI = new BookAPI();
        bookAPI.setLimit(1);
        try {
            List<Book> books = bookAPI.getAllBooks();
            assertTrue("All three books are not returned", books.size() == 1);
            assertEquals("", books.get(0).getName(), "The Fellowship Of The Ring");
        } catch (Exception e) {
            e.printStackTrace();
            assertTrue("Exception as occured during test execution", false);
        }
    }

    @Test
    public void testGetAllBooksSorted() {
        BookAPI bookAPI = new BookAPI();
        String sortParams = "sort=name:asc";
        bookAPI.setAdditionalQueryParams(sortParams);
        try {
            List<Book> books = bookAPI.getAllBooks();
            assertTrue("All three books are not returned", books.size() == 3);
            assertEquals("First Book is not in the correct order", books.get(0).getName(),
                    "The Fellowship Of The Ring");
            assertEquals("Second Book is not in the correct order", books.get(1).getName(), "The Return Of The King");
            assertEquals("Third Book is not in the correct order", books.get(2).getName(), "The Two Towers");
        } catch (Exception e) {
            e.printStackTrace();
            assertTrue("Exception as occured during test execution", false);
        }
    }

    @Test
    public void testGetAllBooksFiltered() {
        BookAPI bookAPI = new BookAPI();
        String filterQueryParams = "name!=The Two Towers";
        bookAPI.setAdditionalQueryParams(filterQueryParams);
        try {
            List<Book> books = bookAPI.getAllBooks();
            assertTrue("All three books are not returned", books.size() == 2);
            assertTrue("The Two Towers was not filtered out",
                    !books.get(0).getName().equals("The Two Towers"));
            assertTrue("The Two Towers was not filtered out", !books.get(1).getName().equals("The Two Towers"));
        } catch (Exception e) {
            e.printStackTrace();
            assertTrue("Exception as occured during test execution", false);
        }
    }
}
