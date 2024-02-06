package id.ac.ui.cs .advprog.eshop.service;
import id.ac.ui.cs.advprog.eshop.model.Product;
import java.util.List;
public interface ProductService {
    public Product create(Product product);
    public void edit(Product product);
    public Product get(String id);
    public void delete(Product product);
    public List<Product> findAll();
}