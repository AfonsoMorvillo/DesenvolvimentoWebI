package edu.ifsp.modelo;

public class Cliente {
   private long   id     = -1;
   private long   userId = -1;
   private String nome;
   private String email;
   private String logradouro;
   private String bairro;
   private String cidade;
   private String estado;
   private String cep;
   private String password;
   
   public boolean isNew() {
      return id == -1;
   }

   public long getId() {
      return id;
   }


   public void setId( long id ) {
      this.id = id;
   }


   public long getUserId() {
      return userId;
   }


   public void setUserId( long userId ) {
      this.userId = userId;
   }


   public String getNome() {
      return nome;
   }


   public void setNome( String nome ) {
      this.nome = nome;
   }


   public String getEmail() {
      return email;
   }


   public void setEmail( String email ) {
      this.email = email;
   }


   public String getLogradouro() {
      return logradouro;
   }


   public void setLogradouro( String logradouro ) {
      this.logradouro = logradouro;
   }


   public String getBairro() {
      return bairro;
   }


   public void setBairro( String bairro ) {
      this.bairro = bairro;
   }


   public String getCidade() {
      return cidade;
   }


   public void setCidade( String cidade ) {
      this.cidade = cidade;
   }


   public String getEstado() {
      return estado;
   }


   public void setEstado( String estado ) {
      this.estado = estado;
   }


   public String getCep() {
      return cep;
   }


   public void setCep( String cep ) {
      this.cep = cep;
   }


   public String getPassword() {
      return password;
   }


   public void setPassword( String password ) {
      this.password = password;
   }

}
