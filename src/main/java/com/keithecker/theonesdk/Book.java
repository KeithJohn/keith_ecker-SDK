package com.keithecker.theonesdk;

public class Book {
    private String Id;
    private String Name;

    /**
     * A POJO representing a Book object from The One API
     * 
     * @param id
     * @param name
     */
    public Book(String id, String name) {
        this.Id = id;
        this.Name = name;
    }

    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

}
