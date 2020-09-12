/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.una.tramites;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author LordLalo
 */
@RestController
@RequestMapping("/dataInitializer")
@Api(tags = {"DataInitializer"})
public class DataInitializerController {
    @Autowired
     private IDataInitializerService dataInitializerService;
     @GetMapping("/{id}")
    @ApiOperation(value = "Obtiene un permiso de acuerdo al id",tags ="DataInitializer")
    public ResponseEntity<?> initDevelopData() {
     try{
       dataInitializerService.initDevelopData();
       return  new ResponseEntity<>(HttpStatus.OK);
     }catch(Exception e){
         return  new ResponseEntity<>(e,HttpStatus.INTERNAL_SERVER_ERROR);
     }
    }
     @DeleteMapping("/")
    @ApiOperation(value = "elimina todo",tags ="DataInitializer")
    public ResponseEntity<?> deleteAllData() {
     try{
       dataInitializerService.deleteAllData();
       return  new ResponseEntity<>(HttpStatus.OK);
     }catch(Exception e){
         return  new ResponseEntity<>(e,HttpStatus.INTERNAL_SERVER_ERROR);
     }
    }
}
