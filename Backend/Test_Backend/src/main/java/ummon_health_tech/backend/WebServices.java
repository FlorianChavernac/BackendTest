package ummon_health_tech.backend;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.data.domain.Pageable;

import java.util.List;

@RestController
public class WebServices {

    /*
    Liste des requêtes:
    - POST: http://localhost:8080/add
    - GET: http://localhost:8080/all
    - GET: http://localhost:8080/find/1
    - PUT: http://localhost:8080/update/1
    - DELETE: http://localhost:8080/delete/1
     */

    @Autowired
    private BookRepository bookRepository;

    /*
    requête POST avec l'URL suivante:
    http://localhost:8080/add
    et on doit ajouter les paramètres suivants :
    dans le header:
    Content-Type: application/json
    dans le body:
    {
        "title": "Titre_du_livre
        "author": "Auteur_du_livre",
        "isbn": "123456789"
     */
    @PostMapping(value = "/add", consumes = "application/json")
    public Book addBook(@RequestBody Book book) {
        bookRepository.save(book);
        return book;
    }

    /*
    requête GET avec l'URL suivante:
    http://localhost:8080/all
    on peut ajouter les paramètres suivants:
    ?pageNo=0&pageSize=2
     */
    @GetMapping("/all")
    public ResponseEntity<List<Book>> getAllBooks(
            @RequestParam(name = "pageNo", defaultValue = "0") Integer pageNo,
            @RequestParam(name = "pageSize", defaultValue = "2") Integer pageSize
    ) {
        Pageable paging = PageRequest.of(pageNo, pageSize);
        Page<Book> pagedResult = bookRepository.findAll(paging);
        if (pagedResult.hasContent()) {
            return new ResponseEntity<>(pagedResult.getContent(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    /*
    requête GET avec l'URL suivante:
    http://localhost:8080/find/1
     */
    @GetMapping("/find/{id}")
    public ResponseEntity<Book> findBookById(@PathVariable Integer id) {
        Book book = bookRepository.findBookById(id);
        if (book != null) {
            return new ResponseEntity<>(book, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    /*
    requête PUT avec l'URL suivante:
    http://localhost:8080/update/1
    et on doit ajouter les paramètres suivants:
    {
        "title": "The Lord of the Rings",
        "author": "J.R.R. Tolkien",
        "isbn": "978-0544003415"
     */
    @PutMapping("/update/{id}")
    public ResponseEntity<Book> updateBook(@PathVariable Integer id, @RequestBody Book book) {
        // Vérifier si le livre existe
        if (bookRepository.findBookById(id) == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            Book bookToUpdate = bookRepository.findBookById(id);
            bookToUpdate.setTitle(book.getTitle());
            bookToUpdate.setAuthor(book.getAuthor());
            bookToUpdate.setIsbn(book.getIsbn());
            bookRepository.save(bookToUpdate);
            return new ResponseEntity<>(bookToUpdate, HttpStatus.OK);
        }
    }

    /*
    requête DELETE avec l'URL suivante:
    http://localhost:8080/delete/1
     */
    @DeleteMapping("/delete/{id}")
    public String deleteBook(@PathVariable Integer id) {
        bookRepository.deleteById(id);
        return "Book whit id : "+id+" was deleted";
    }
}
