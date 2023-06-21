package TiendaKevin.TiendaKevin.db;
import TiendaKevin.TiendaKevin.entities.Product;
import org.springframework.data.repository.CrudRepository;
public interface IProductRepository extends CrudRepository<Product, Integer> {
}