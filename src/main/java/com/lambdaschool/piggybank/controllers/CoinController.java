package com.lambdaschool.piggybank.controllers;

import com.lambdaschool.piggybank.models.Coin;
import com.lambdaschool.piggybank.repositories.CoinRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class CoinController {
    @Autowired
    private CoinRepository coinRepository;

    //http://localhost:2019/total
    //Create an endpoint http://localhost:2019/total that prints to console the contents of the Piggy Bank as follows and returns an HTTP Status of OK:
    @GetMapping(value = "/total", produces = "application/json")
    public ResponseEntity<?> printTotal() {
        List<Coin> coinList = new ArrayList<>();
        coinRepository.findAll().iterator().forEachRemaining(c -> coinList.add(c));

        double total = 0.00;
        for (Coin c : coinList) {
            total += c.getQuantity() * c.getValue();
        }

        int i = 0;
        for (i = 0; i < coinList.size(); i++){
            if(coinList.get(i).getQuantity() > 1){
                System.out.println(coinList.get(i).getQuantity() + " " + coinList.get(i).getNameplural());
            }else{
                System.out.println(coinList.get(i).getQuantity() + " " + coinList.get(i).getName());
            }
        }
        System.out.println(
                "The piggy bank holds " + total
        );

        //MVP in console
        //    1 Quarter
        //    1 Dime
        //    5 Dollars
        //    3 Nickels
        //    7 Dimes
        //    1 Dollar
        //    10 Pennies
        //    The piggy bank holds 7.3

        return new ResponseEntity<>(HttpStatus.OK);
    }



    //Note: 7.30 = ( 1 * 0.25) + ( 1 * 0.10) + ( 5 * 1.00) + ( 3 * 0.05) + ( 7 * 0.10) + ( 1 * 1.00) + (10 * 0.10)
    //
    //Note: that when you have more than 1 coin, the plural of the coin's name is printed.
}
