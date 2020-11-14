package com.bielinskim.lab.controllers;

import com.bielinskim.lab.models.Book;
import com.bielinskim.lab.models.CoverType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
@SessionAttributes(value = {"obj1", "obj2"})
public class BookController {

    //ArrayList<Book> bookList = new ArrayList<Book>();
    //List<CoverType> list = new ArrayList<CoverType>();


    public BookController() {


    }

    @GetMapping(path={"/book", "/book/{id}"})
    public String getBookController(Model model, @ModelAttribute("books") List<Book> books, @PathVariable("id") Optional<Long> eid){


        if(eid.isPresent()) {
            var book = books.stream().filter(item -> ((Book) item).getId() == eid.get()).findFirst();
            model.addAttribute(book.get());
        } else {
            var book = books.stream().filter(item -> ((Book) item).getId() == 1).findFirst();
            model.addAttribute(book.get());
        }
        return "book";
    }

    @GetMapping("/list")
    public String showList(@ModelAttribute("books") List<Book> books) {

        System.out.println(books);

       // model.addAttribute("books", bookList);
        return "bookList";
    }

    @GetMapping(path={"/showForm", "/showForm/{id}"})
    public String showForm(Model model, @ModelAttribute("books") List<Book> books, @PathVariable("id") Optional<Long> eid) {

        if(eid.isPresent()) {
            var book = books.stream().filter(item -> ((Book) item).getId() == eid.get()).findFirst();
            model.addAttribute(book.get());
            model.addAttribute("obj1", new Object1());
            return "editBook";
        } else {
            var book = new Book();
            model.addAttribute(book);
            model.addAttribute("obj1", new Object1());
            return "addBook";
        }

    }
    @PostMapping(path={"/showForm", "/showForm/{id}"})
    public String processForm(Book book, @ModelAttribute("books") List<Book> books, @ModelAttribute(value = "obj1") Object1 object1, @PathVariable("id") Optional<Long> eid) {



        //var tmpBook = books.stream().filter(item -> ((Book) item).getId() == eid.get()).findFirst();
        //var coverType = covers.stream().filter(ct -> ((CoverType) ct).getId() == book.getCoverType().getId()).findFirst();
        //book.setCoverType(coverType.get());

        if(!eid.isPresent()) {
            books.add(book);
        } else {
            for(int i=0; i<books.size(); i++) {
                if(books.get(i).getId() == eid.get()) {
                    books.set(i, book);
                }
            }
        }
        return "book";

    }
    @ModelAttribute("coverTypesList")
    public List<CoverType> loadCoverTypes() throws Exception {
        List<CoverType> covers = new ArrayList<CoverType>();
        covers.add(new CoverType(1, "Oprawa twarda tradycyjna"));
        covers.add(new CoverType(2, "Oprawa twarda przemysłowa"));
        covers.add(new CoverType(3, "Oprawa klejona"));
        covers.add(new CoverType(4, "Oprawa szyta"));
        return covers;
    }
    @ModelAttribute("books")
    public List<Book> loadBooks(@ModelAttribute("coverTypesList") List<CoverType> covers) throws Exception {


        List<Book> books = new ArrayList<Book>();


        var book = new Book();
        book.setId(1l);
        book.setTitle("W pustyni i w puszczy");
        book.setAuthor("Henryk Sienkiewicz");
        book.setReleaseDate(LocalDate.of(2005, 12, 23));
        book.setPrice(39.00f);
        book.setBestseller(true);
        book.setCoverType(covers.get(2));

        books.add(book);
        books.add(new Book(2l, "Andrzej Sapkowski", "Sezon burz", LocalDate.of(2008, 11, 13), 39.87f, true, covers.get(1)));
        books.add(new Book(3l, "Tony Halik", "Moja wielka przygoda", LocalDate.of(1988, 11, 13), 29.87f, true, covers.get(3)));
        return books;
    }



    @ModelAttribute(value = "obj2")
    public List<Object2> loadDependencies() {
        System.out.println("wywołano metodę loadDependencies");
        List<Object2> dependenciesList = new ArrayList<Object2>();
        return dependenciesList;
    }

    class Object1{}
    class Object2{}

}
