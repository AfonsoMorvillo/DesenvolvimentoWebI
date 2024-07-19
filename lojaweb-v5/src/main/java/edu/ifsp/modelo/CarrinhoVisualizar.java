package edu.ifsp.modelo;

import java.util.ArrayList;
import java.util.List;

public class CarrinhoVisualizar {
   private List<Produto> produtos = new ArrayList<>();
   private Double        valorTotal;

   public List<Produto> getProdutos() {
      return produtos;
   }


   public void setProdutos( List<Produto> produtos ) {
      this.produtos = produtos;
   }


   public Double getValorTotal() {
      return valorTotal;
   }


   public void setValorTotal( Double valorTotal ) {
      this.valorTotal = valorTotal;
   }

}
