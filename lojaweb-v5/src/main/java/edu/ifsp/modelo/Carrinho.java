package edu.ifsp.modelo;

public class Carrinho extends Entity {
   private Usuario usuario = new Usuario();
   private Produto produto = new Produto();

   public Carrinho( Usuario usuario, Produto produto ) {
      this.usuario = usuario;
      this.produto = produto;
   }


   public Carrinho() {
   }


   public Usuario getUsuario() {
      return usuario;
   }


   public void setUsuario( Usuario usuario ) {
      this.usuario = usuario;
   }


   public Produto getProduto() {
      return produto;
   }


   public void setProduto( Produto produto ) {
      this.produto = produto;
   }

}
