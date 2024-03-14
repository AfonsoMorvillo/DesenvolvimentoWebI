package edu.ifsp.pizzaria.persistencia;

import java.util.List;

import edu.ifsp.pizzaria.FormaContato;

public class FormaContatoDAO {

   static public List<FormaContato> findAll() {
      return List.of( new FormaContato( "wa", "WhatsApp" ), new FormaContato( "carta", "E-mail" ), new FormaContato( "email", "Carta" ) );

   }
}
