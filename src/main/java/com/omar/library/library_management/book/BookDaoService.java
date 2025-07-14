package com.omar.library.library_management.book;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BookDaoService {
    private static int bookCount = 0;
    private static List<Book> books = new ArrayList<>();

    static {
        books.add(new Book(++bookCount,"Objects in the Mirror Are Closer Than They Appear","Jenny","OOP",2017));
        books.add(new Book(++bookCount,"Catch Me If You Can","Freddy","Error Handling",2021));
        books.add(new Book(++bookCount,"Hardcoding with Passion","Mr coder","Software Engineering",2019));
    }

    public List<Book> finaAll() {
        return books;
    }
    public Book findById(int id){
        return books.stream()
                .filter(book -> book.getId() == id)
                .findFirst().orElse(null);
    }
    public Book add(Book book) {
        book.setId(++bookCount);
        books.add(book);
        return book;
    }
    public void deleteById(int id) {
        books.removeIf(book -> book.getId() == id);
    }

}
