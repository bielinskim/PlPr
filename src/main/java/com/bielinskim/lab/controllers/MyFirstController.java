package com.bielinskim.lab.controllers;

import com.bielinskim.lab.models.Book;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;

@Controller
public class MyFirstController {

    @GetMapping("/page1.html")
    public void firstControllerMethod(HttpServletResponse response) throws IOException {
        response.getWriter().write(String.valueOf(java.time.LocalDate.now()));
    }

    @GetMapping("/page2")
    public String secondControllerMethod(Model model){
        model.addAttribute("first_name", "Mateusz");
        model.addAttribute("last_name", "Bieliński");
        return "page2";
    }

    @GetMapping("/page3")
    public ModelAndView thirdControllerMethod(){
        var mav = new ModelAndView( "page3");
        mav.addObject("os_arch", System.getProperty("os.arch") );
        mav.addObject("os_name", System.getProperty("os.name") );
        mav.addObject("java_vendor", System.getProperty("java.vendor") );
        mav.addObject("java_version", System.getProperty("java.version") );

        return mav;

    }
    @GetMapping("/book")
    public ModelAndView getBookController(){
        var mav = new ModelAndView( "book");
       var book = new Book();
       book.setTitle("W pustyni i w puszczy");
       book.setAuthor("Henryk Sienkiewicz");
       book.setReleaseDate(LocalDate.of(2005, 12, 23));
       book.setPrice(39.00f);
       book.setBestseller(true);
       mav.addObject(book);

        return mav;

    }

}
