package ss5.th.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import ss5.th.model.Tel;
import javax.validation.Valid;

@Controller
public class TelController {
    @GetMapping("/")
    public String showForm(Model model){
        model.addAttribute("tel", new Tel());
        return "index";
    }
    @PostMapping("/")
    public String checkValidation (@Valid @ModelAttribute("tel")Tel tel, BindingResult bindingResult, Model model){
        new Tel().validate(tel, bindingResult);
        if (bindingResult.hasFieldErrors()){
            return "index";
        }
        else {
            model.addAttribute("tel", tel);
            return "result";
        }
    }
}
