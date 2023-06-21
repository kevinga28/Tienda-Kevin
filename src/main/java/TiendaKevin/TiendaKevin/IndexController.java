package TiendaKevin.TiendaKevin;
import TiendaKevin.TiendaKevin.service.IProductService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

//Le dijo a SB que esto es un controller
@Controller
public class IndexController {
      private final IProductService productService;
    public IndexController(IProductService productService) {
        this.productService = productService;
    }
    @GetMapping("/")
   public String index(Model model) {
        var productos = this.productService.getAllProducts();
        model.addAttribute("products", productos);
        return "index";
        //Este mae va a ir a retornar lo que hay dentro de templates
    }
}


