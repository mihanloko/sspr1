package loko.lab1.controller;

import loko.lab1.entity.Property;
import loko.lab1.entity.Tent;
import loko.lab1.repository.ManufacturerRepository;
import loko.lab1.repository.TentRepository;
import loko.lab1.service.ComplexService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@AllArgsConstructor
public class ComplexController {

    private ComplexService service;
    private ManufacturerRepository manufacturerRepository;
    private TentRepository tentRepository;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String mainPage(Model model,
                           @RequestParam(required = false, defaultValue = "0") long priceMin,
                           @RequestParam(required = false, defaultValue = "1000000") long priceMax,
                           @RequestParam(required = false, defaultValue = "0") long weightMin,
                           @RequestParam(required = false, defaultValue = "40") long weightMax,
                           @RequestParam(required = false, defaultValue = "0") long placeMin,
                           @RequestParam(required = false, defaultValue = "20") long placeMax,
                           @RequestParam(required = false, defaultValue = "0") long manufacturer) {
        List<Tent> tents = service.findAllTents();

        tents = tents.stream().filter(tent -> tent.getPrice() >= priceMin).collect(Collectors.toList());
        tents = tents.stream().filter(tent -> tent.getPrice() <= priceMax).collect(Collectors.toList());

        tents = tents.stream().filter(tent -> tent.getWeight() >= weightMin).collect(Collectors.toList());
        tents = tents.stream().filter(tent -> tent.getWeight() <= weightMax).collect(Collectors.toList());

        tents = tents.stream().filter(tent -> tent.getPlaces() >= placeMin).collect(Collectors.toList());
        tents = tents.stream().filter(tent -> tent.getPlaces() <= placeMax).collect(Collectors.toList());

        if (manufacturer > 0)
            tents = tents.stream().filter(tent ->
                    tent.getManufacturer().getId() == manufacturer).collect(Collectors.toList());

        model.addAttribute("priceMin", priceMin);
        model.addAttribute("priceMax", priceMax);
        model.addAttribute("weightMin", weightMin);
        model.addAttribute("weightMax", weightMax);
        model.addAttribute("placeMin", placeMin);
        model.addAttribute("placeMax", placeMax);
        model.addAttribute("manufacturer", manufacturer);
        model.addAttribute("tents", tents);
        return "index";
    }

    @RequestMapping(value = "/edit_page", method = RequestMethod.POST)
    public String newTentPage(Model model, @RequestParam(name = "id") Long id) {

        model.addAttribute("tent", tentRepository.findById(id).get());

        return "edit_product";
    }

    @RequestMapping(value = "/watch", method = RequestMethod.POST)
    public String watch(Model model, @RequestParam(name = "id") Long id) {

        model.addAttribute("tent", tentRepository.findById(id).get());

        return "element";
    }

    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public String delete(Model model, @RequestParam(name = "id") Long id) {

        service.deleteTent(id);

        return "redirect:/";
    }

    @RequestMapping(value = "/new_tent", method = RequestMethod.GET)
    public String newTentPage(Model model) {
        return "new_product";
    }

    @RequestMapping(value = "/process_edit", method = RequestMethod.POST)
    public String editTent(@RequestParam(name = "id") Long id,
                           @RequestParam(name = "fullLength") Integer fullLength,
                           @RequestParam(name = "fullWidth") Integer fullWidth,
                           @RequestParam(name = "fullHeight") Integer fullHeight,
                           @RequestParam(name = "smallLength") Integer smallLength,
                           @RequestParam(name = "smallWidth") Integer smallWidth,
                           @RequestParam(name = "smallHeight") Integer smallHeight,
                           @RequestParam(name = "price") Integer price,
                           @RequestParam(name = "places") Integer places,
                           @RequestParam(name = "mass") Double weight,
                           @RequestParam(name = "manufacturer") Long manufacturer,
                           @RequestParam(name = "inputProductName") String inputProductName,
                           @RequestParam(name = "inputFile") String inputFile,
                           @RequestParam(name = "name1") String name1,
                           @RequestParam(name = "description1") String description1,
                           @RequestParam(name = "name2") String name2,
                           @RequestParam(name = "description2") String description2,
                           @RequestParam(name = "name3") String name3,
                           @RequestParam(name = "description3") String description3,
                           @RequestParam(name = "name4") String name4,
                           @RequestParam(name = "description4") String description4,
                           @RequestParam(name = "description") String description) {

        Tent tent = new Tent();
        if (id != 0) {
            tent.setId(id);
            service.deleteProperties(id);
        }
        tent.setHeightFull(fullHeight);
        tent.setHeightSmall(smallHeight);

        tent.setWidthFull(fullWidth);
        tent.setWidthSmall(smallWidth);

        tent.setLengthFull(fullLength);
        tent.setLengthSmall(smallLength);

        tent.setWeight(weight);
        tent.setPlaces(places);

        tent.setTentName(inputProductName);
        tent.setTentDescription(description);

        List<Property> properties = new LinkedList<>();
        if (!name1.isEmpty() && !description1.isEmpty()) {
            Property property = new Property();
            property.setTitle(name1);
            property.setFullText(description1);
            properties.add(property);
        }
        if (!name2.isEmpty() && !description2.isEmpty()) {
            Property property = new Property();
            property.setTitle(name2);
            property.setFullText(description2);
            properties.add(property);
        }
        if (!name3.isEmpty() && !description3.isEmpty()) {
            Property property = new Property();
            property.setTitle(name3);
            property.setFullText(description3);
            properties.add(property);
        }
        if (!name4.isEmpty() && !description4.isEmpty()) {
            Property property = new Property();
            property.setTitle(name4);
            property.setFullText(description4);
            properties.add(property);
        }
        tent.setProperties(properties);

        tent.setManufacturer(manufacturerRepository.findById(manufacturer).get());
        tent.setImageFile(inputFile);

        tent.setPrice(price);

        service.saveTent(tent);

        return "redirect:/";
    }

}
