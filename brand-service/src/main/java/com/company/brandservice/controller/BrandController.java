package com.company.brandservice.controller;

import com.company.brandservice.entity.Brand;
import com.company.brandservice.entity.Message;
import com.company.brandservice.services.BrandService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;


@RestController
@RequestMapping("/v1/brand")
public class BrandController {

    protected static final Logger logger = Logger.getLogger(Brand.class.getName());

    private final BrandService service;

    public BrandController(BrandService service) {
        this.service = service;
    }

    @GetMapping(produces = {MediaType.APPLICATION_JSON_VALUE,MediaType.APPLICATION_ATOM_XML_VALUE})
    public ResponseEntity<List<Brand>> getAllBrands() {
        Optional<List<Brand>> brandList;
        try {
            //brand ha ro az service migirim
            brandList = service.getAllBrand();
        } catch (Exception e) {
            //age moshkeli bud be user migim
            logger.log(Level.SEVERE, "doshvari darim ba database", e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        //age brandi dashtim ke return mikonim
        return brandList.map(brands -> new ResponseEntity<>(brands, HttpStatus.OK))
                //age brandi nadashtim HttpStatus.NO_CONTENT return mikonim
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NO_CONTENT));
    }

    @GetMapping(value = "/{brand-name}")
    public ResponseEntity<Brand> getBrandByName(@RequestParam(value = "brand-name") String name) {
        Optional<Brand> brand;
        try {
            //brand ro az database migirim
            brand = service.getByName(name);
        } catch (Exception e) {
            //age doshvari bevojod omad migim be user
            logger.log(Level.SEVERE, "some internal ex daim", e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        //dar akhar age brandi ba in name to database dashtim return mikonim
        return brand.map(value -> new ResponseEntity<>(value, HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(HttpStatus.NO_CONTENT));
    }

    @PostMapping
    public ResponseEntity<?> createBrand(@RequestBody @Valid Brand brand) {
        //check mikonim ke tekrari nabashe chom ma name Brand ro Uniq gozashtim
        if (service.getByName(brand.getName()).isPresent()) {
            //age brand ba in nam vojod dasht be karbar etela midim
            return new ResponseEntity<>(new Message("Not Found!!!"), HttpStatus.BAD_REQUEST);
        }
        try {
            //inja brand ro save mikonim
            service.createAndUpdateBrand(brand);
        } catch (Exception e) {
            System.err.println(e.getCause());
            //agaram doshvari bud be user migim ke Object ke frestadi ye doshvari dare ma hese check kardanesho nadarim khodet ye nega besh bendaz
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

        //age moshkeli nabud be user migim ke create shod
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<?> updateBrand(@RequestBody Brand brand) {
        try {
            //inja brand ro save mikonim
            //jpa shoor dare mifahme ka bayad update kone
            service.createAndUpdateBrand(brand);
        } catch (Exception e) {
            //agaram doshvari bud be user migim ke Object ke frestadi ye doshvari dare ma hese check kardanesho nadarim khodet ye nega besh bendaz blah blah blah
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        //age moshkeli nabud be user migim ke update  shod
        return new ResponseEntity<>(new Message("Brand Updated"), HttpStatus.OK);
    }

    @DeleteMapping(value = "/{brand-id}")
    public ResponseEntity<?> deleteById(@RequestParam(value = "brand-id") Long id) {
        try {
            service.deleteById(id);
        } catch (Exception e) {
            //agaram doshvari bud be user migim ke Object ke frestadi ye doshvari dare ma hese check kardanesho nadarim khodet ye nega besh bendaz blah blah blah
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
