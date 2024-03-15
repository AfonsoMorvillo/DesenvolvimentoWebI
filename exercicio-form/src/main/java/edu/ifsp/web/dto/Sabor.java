package edu.ifsp.web.dto;

public class Sabor {
   private String valor;
   private String descricao;

   public Sabor( String valor, String descricao ) {
      this.valor = valor;
      this.descricao = descricao;
   }


   public String getValor() {
      return valor;
   }


   public void setValor( String valor ) {
      this.valor = valor;
   }


   public String getDescricao() {
      return descricao;
   }


   public void setDescricao( String descricao ) {
      this.descricao = descricao;
   }

}
