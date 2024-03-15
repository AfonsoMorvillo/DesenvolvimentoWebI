package edu.ifsp.web.persistencia;

import java.util.List;

import edu.ifsp.web.dto.Sabor;

public class SaboresDAO {

   static public List<Sabor> findAll(){
      return List.of(
            new Sabor( "calabresa", "Calabresa"),
            new Sabor( "mussarela", "Mussarela"),
            new Sabor( "frango", "Frango"),
            new Sabor( "presunto", "Presunto"),
            new Sabor( "portuguea", "Portuguesa")
            );
   }
}
