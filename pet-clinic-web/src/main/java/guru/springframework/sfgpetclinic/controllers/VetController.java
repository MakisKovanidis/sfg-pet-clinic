package guru.springframework.sfgpetclinic.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class VetController {

    @RequestMapping({"/vets", "/vet/index", "/vets/index.html"})
    public String listVets(){

        return "vets/index";
    }
}
