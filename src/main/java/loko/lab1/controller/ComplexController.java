package loko.lab1.controller;

import loko.lab1.entity.Tent;
import loko.lab1.service.ComplexService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@Controller
@AllArgsConstructor
public class ComplexController {

    private ComplexService service;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String mainPage(Model model) {
        List<Tent> tents = service.findAllTents();

        model.addAttribute("tents", tents);
        return "index";
    }

}
