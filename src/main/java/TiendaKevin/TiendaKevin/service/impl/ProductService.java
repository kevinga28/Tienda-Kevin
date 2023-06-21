package TiendaKevin.TiendaKevin.service.impl;
import TiendaKevin.TiendaKevin.db.IProductRepository;
import TiendaKevin.TiendaKevin.entities.Product;
import TiendaKevin.TiendaKevin.service.IProductService;
import org.springframework.stereotype.Service;
import java.util.List;
@Service
public class ProductService implements IProductService {
    private final IProductRepository productRepository;
    public ProductService(IProductRepository productRepository) {
        this.productRepository = productRepository;
    }
    public List<Product> getAllProducts() {
        return (List<Product>) this.productRepository.findAll();
    }
}