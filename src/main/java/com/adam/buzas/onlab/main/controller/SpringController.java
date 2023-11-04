//package com.adam.buzas.onlab.main.controller;
//
//import com.adam.buzas.onlab.main.model.*;
//import com.adam.buzas.onlab.main.services.*;
//import jakarta.servlet.http.HttpSession;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.ModelAttribute;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.servlet.mvc.support.RedirectAttributes;
//
//import java.time.LocalDateTime;
//import java.util.HashMap;
//import java.util.Map;
//
//
//@Controller
//public class SpringController {
//
//    @Autowired
//    private UserService userService;
//    @Autowired
//    private OrderService orderService;
//    @Autowired
//    private CategoryService categoryService;
//    @Autowired
//    private BookService bookService;
//    @Autowired
//    private OrderedBookService orderedBookService;
//    @Autowired
//    private CartService cartService;
//    @GetMapping("/")
//    public String getIndexHtml(Model model, HttpSession session){
//        if(session.getAttribute("szuresiKategoria") == null &&
//                (session.getAttribute("keresoSzo") == "" ||
//                session.getAttribute("keresoSzo") == null)){
//            model.addAttribute("konyvek", bookService.getAllBook());
//        }
//        else if(session.getAttribute("szuresiKategoria") != null &&
//                (session.getAttribute("keresoSzo") == "" ||
//                session.getAttribute("keresoSzo") == null)){
//            int id = (Integer) session.getAttribute("szuresiKategoria");
//            Category category = categoryService.getCategory(id).get();
//            model.addAttribute("konyvek", bookService.getBookCategory(category));
//        }
//        else if(session.getAttribute("szuresiKategoria") == null &&
//                session.getAttribute("keresoSzo") != ""){
//            String keres = (String) session.getAttribute("keresoSzo");
//            model.addAttribute("keresoSzo", keres);
//            model.addAttribute("konyvek", bookService.getSearchedBooks(keres));
//        }
//        else{
//            String keres = (String) session.getAttribute("keresoSzo");
//            int id = (Integer) session.getAttribute("szuresiKategoria");
//            Category category = categoryService.getCategory(id).get();
//            model.addAttribute("keresoSzo", keres);
//            model.addAttribute("konyvek", bookService.getBookCategoryAndSearch(category, keres));
//        }
//        model.addAttribute("kategoriak", categoryService.getAll());
//
//        if(session.getAttribute("kosar")==null){
//            session.setAttribute("kosar", new Cart());
//        }
//        return "index";
//    }
//
//    @GetMapping("/kijelentkezes")
//    public String getKijelentkezesHtml(HttpSession session){
//        session.invalidate();
//        return "redirect:/";
//    }
//
//    @GetMapping("/bejelentkezes")
//    public String getBejelentkezesHtml(HttpSession session){
//        return "bejelentkezes";
//    }
//
//    @GetMapping("/konyv")
//    public String getKonyvHtml(Model model, @RequestParam int id){
//        model.addAttribute("konyv", bookService.getBookById(id).get());
//        return "konyv";
//    }
//
//    @GetMapping("/kosar")
//    public String getKosarHtml(HttpSession session){
//        return "kosar";
//    }
//
//    @GetMapping("/szallitas")
//    public String getSzallitasHTML(){return "szallitas";}
//
//    @GetMapping("/szallitasCimMegadas")
//    public String szallitas(@RequestParam("iranyitoSzam") String iranyitoSzam,
//                            @RequestParam("helyseg") String helyseg,
//                            @RequestParam("utca") String utca,
//                            HttpSession session) {
//        String szallitasiAdatok = iranyitoSzam + ", " + helyseg + ", " + utca;
//        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
//        session.setAttribute("szallitasiAdatok", szallitasiAdatok);
//        return "redirect:penztar";
//    }
//
//    @GetMapping("/penztar")
//    public String getPenztarHtml(HttpSession session){
//        return "penztar";
//    }
//
//
////    @PostMapping("/rendelesLeadas")
////    public String rendelesLeadas(HttpSession session, RedirectAttributes redirectAttributes){
////        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
////        String cim = (String) session.getAttribute("szallitasiAdatok");
////        Order order = orderService.newOrder(LocalDateTime.now(), cim, userService.getUserByUsername(auth.getName()));
////
////        Cart k = (Cart) session.getAttribute("kosar");
////        Map<Integer, Integer> konvyIdDb = new HashMap<>();
////        for(int i = 0; i<k.getCartContent().size(); i++){
////            int db = 1;
////            for(int j = 0; j<k.getCartContent().size(); j++){
////                if(k.getCartContent().get(i).getId()==k.getCartContent().get(j).getId() && i!=j){
////                    db++;
////                }
////            }
////            if(!konvyIdDb.containsKey(k.getCartContent().get(i).getId())){
////                konvyIdDb.put(k.getCartContent().get(i).getId(), db);
////            }
////        }
////
////        for (Map.Entry<Integer, Integer> entry : konvyIdDb.entrySet()) {
////            orderedBookService.newOrderedBook(orderService.getOrder(order).get(),
////                    bookService.getBookById(entry.getKey()).get(),
////                    entry.getValue());
////        }
////
////
////
////
////
////        session.removeAttribute("kosar");
////        session.removeAttribute("kereses");
////        session.removeAttribute("szuresiKategoria");
////        session.removeAttribute("szallitasiAdatok");
////        redirectAttributes.addFlashAttribute("rendelesEredmeny", "Köszönjük a rendelését!");
////
////        return "redirect:/";
////    }
//
//    @GetMapping("/regisztracio")
//    public String getRegisztracioHtml(Model model){
//        model.addAttribute("felhasznalo", new User());
//        return "regisztracio";
//    }
//
//    @PostMapping("/kosarba")
//    public String postKosar(@RequestParam int id, HttpSession session){
//        Book book = bookService.getBookById(id).get();
//        Cart cart = (Cart) session.getAttribute("kosar");
//        cartService.newElementIntoCart(book, cart);
//        session.setAttribute("kosar", cart);
//        return "redirect:/kosar";
//    }
//
//    @PostMapping("/kosarbolTorol")
//    public String deleteKosarbol(@RequestParam int id, HttpSession session){
//        Book book = bookService.getBookById(id).get();
//        Cart cart = (Cart) session.getAttribute("kosar");
//        cartService.elementDeleteFromCart(book, cart);
//        session.setAttribute("kosar", cart);
//        return "redirect:/kosar";
//    }
//
//    @GetMapping("/kategoriaSzures")
//    public String kategoriaSzur(HttpSession session, Integer kategoria){
//        session.setAttribute("szuresiKategoria", kategoria);
//        return "redirect:/";
//    }
//
//    @GetMapping("/kereses")
//    public String kereses(Model model, HttpSession session, @RequestParam("keresoSzo") String kereses) {
//        session.setAttribute("keresoSzo", kereses);
//        return "redirect:/";
//    }
//
//
//
//
//    @GetMapping("/ujFelhasznalo")
//    public String ujFelhasznaloRegisztral(User f, RedirectAttributes redirectAttributes){
//        if(userService.getUsername(f.getUsername()) == "" &&
//            userService.getUserEmail(f.getEmail()) == "")   {
//            f.setRole(Role.USER);
//            userService.newUser(f);
//        }
//        else if(userService.getUsername(f.getUsername()) != ""){
//            redirectAttributes.addFlashAttribute("regisztacioUzenet", "Van a rendszerben ilyen felhasználónévvel vagy email címmel felhasználó!");
//        }
//        else{
//            redirectAttributes.addFlashAttribute("regisztacioUzenet", "Van a rendszerben ilyen felhasználónévvel vagy email címmel felhasználó!");
//        }
//        return "redirect:/";
//    }
//
//    @GetMapping("/elemFelvetel")
//    public String elemFelvetelMenu(Model model){
//        model.addAttribute("ujKonyv", new Book());
//        model.addAttribute("kategoriak", categoryService.getAll());
//        return "elemFelvetel";
//    }
//
//    @GetMapping("/ujKonyv")
//    public String ujKonyv(@ModelAttribute("ujKonyv") Book k, Integer kategoria){
//        Category ujKonyvKategoriaja = categoryService.getCategory(kategoria).get();
//        if(ujKonyvKategoriaja != null){
//            System.out.println(kategoria);
//            System.out.println(k.getTitle());
//            System.out.println(ujKonyvKategoriaja.getName());
//        }
//
//        k.setCategory(ujKonyvKategoriaja);
//        bookService.newBook(k);
//        return "redirect:/";
//    }
//
//    @GetMapping("/ujKategoria")
//    public String ujKategoria(@RequestParam("kategoriaFel") String kategoria){
//        if(!kategoria.equals(""))
//            categoryService.newCategory(kategoria);
//        return "redirect:/elemFelvetel";
//    }
//
//
//}
//
