package ummon_health_tech.backend;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
public interface BookRepository extends CrudRepository<Book, Integer>{
    Book findBookById(Integer id);

    Page<Book> findAll(Pageable pageable);
}
