package Graveyard.controllers;

import lombok.RequiredArgsConstructor;
import Graveyard.data.common.PageResponseDTO;
import Graveyard.data.data_transfer_objects.product.ProductListItemDTO;
import Graveyard.services.ProductService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/products")
@RequiredArgsConstructor
public class ProductsController {

    private final ProductService productService;

    @GetMapping("/list")
    public String listProducts(
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "8") int size,
            @RequestParam(required = false) String search,
            Model model) {

        PageResponseDTO<ProductListItemDTO> pageResponse =
                productService.searchPaginated(search, page, size);

        model.addAttribute("products", pageResponse.getContent());
        model.addAttribute("page", pageResponse.getPage());
        model.addAttribute("search", search); // <----- important
        return "products/list";
    }
}
