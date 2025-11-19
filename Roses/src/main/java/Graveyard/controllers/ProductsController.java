package Graveyard.controllers;

import lombok.RequiredArgsConstructor;
import Graveyard.data.common.PageResponseDTO;
import Graveyard.data.data_transfer_objects.product.ProductListItemDTO;
import Graveyard.data.search.ProductSearchDTO;
import Graveyard.services.CategoryService;
import Graveyard.services.ProductService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/products")
@RequiredArgsConstructor
public class ProductsController {

    private final ProductService productService;
    private final CategoryService categoryService;

    @GetMapping("/list")
    public String listUsers(
            @ModelAttribute ProductSearchDTO search,
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "2") int size,
            Model model) {
        PageResponseDTO<ProductListItemDTO> pageResponse = productService
                .getAllPaginated(page, size, search);
        model.addAttribute("products", pageResponse.getContent());
        model.addAttribute("page", pageResponse.getPage());
        model.addAttribute("search", search);

        model.addAttribute("categories", categoryService.getAll());
        return "products/list";
    }
}
