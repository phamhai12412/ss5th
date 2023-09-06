package ss5.th.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import ss5.th.model.Product;
import ss5.th.service.IProductService;


@Controller
public class ProductController {
    @Autowired
    IProductService productService;
    @GetMapping(value = {"/", "/product"})
    public ModelAndView listProduct(@PageableDefault(sort = "name",size = 3) Pageable pageable){
        Page<Product> products = productService.findAll(pageable);
        return new ModelAndView ("list","products",products);
    }
    @GetMapping("/search")
    public ModelAndView searchProduct(@RequestParam("keyword") String search, Pageable pageable){
        Page<Product> products;
        if(!search.trim().equals("")){
            products = productService.findByNameProduct(search,pageable);
        } else {
            products = productService.findAll(pageable);
        }
        return new ModelAndView ("list","products",products);
    }
    @GetMapping("/add")
    public ModelAndView showFormCreateProduct(){
        return new ModelAndView("add","product",new Product());
    }
    @PostMapping("/add")
    public String createProduct(@Validated @ModelAttribute Product product, BindingResult bindingResult){
        if(bindingResult.hasFieldErrors()){
            return "add";
        }
        productService.save(product);
        return "redirect:/";
    }

}
