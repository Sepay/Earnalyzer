package org.example.controllers;

import org.example.Executer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import javax.crypto.SealedObject;
import java.io.IOException;

@Controller
public class IndexController {

    @Autowired
    Executer executer;

    @GetMapping(value = "/")
    public ModelAndView index() {
        ModelAndView mav = new ModelAndView("index");
        boolean showElements = false;// true or false based on your conditions
        mav.addObject("showElements", showElements); // Add the showElements variable to the model
        return mav;
    }

    @PostMapping("/compare")
    public ModelAndView compare(@RequestParam("amount") String amount, @RequestParam("crypto") String crypto,
                                @RequestParam("duration") String duration, @RequestParam("lock") String lock)
            throws InterruptedException, IOException {
        ModelAndView mav = new ModelAndView("index");
        String[] results = executer.execute(crypto, amount, duration, lock);
        String APRCRO = results[0];
        String AnualCRO = results[1];
        String SemanalCRO = results[2];
        String anualCryptoCRO = results[3];
        String APRBinance = results[4];
        String anualBinance = results[5];
        String semanalBinance = results[6];
        String anualCryptoB = results[7];
        String mValue = results[8];

        // Determine the condition to show or hide the elements
        boolean showElements = true; // true or false based on your conditions

        mav.addObject("APRCRO", APRCRO);
        mav.addObject("AnualCRO", AnualCRO);
        mav.addObject("SemanalCRO", SemanalCRO);
        mav.addObject("anualCryptoCRO", anualCryptoCRO);
        mav.addObject("APRBinance", APRBinance);
        mav.addObject("anualBinance", anualBinance);
        mav.addObject("semanalBinance", semanalBinance);
        mav.addObject("anualCryptoB", anualCryptoB);
        mav.addObject("crypto", crypto);
        mav.addObject("mValue", mValue);
        mav.addObject("amount", amount);
        mav.addObject("showElements", showElements); // Add the showElements variable to the model

        return mav;
    }
}
