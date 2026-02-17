package com.kevin.medialogictest;

public class ResponseWrapper<T> {

   private String status        = null;
   private String description   = null;
   private T      message       = null;


   public ResponseWrapper() {
      this.status      = "OK";
      this.description = "";
   }

   public ResponseWrapper(String status, String description) {
      this.status      = status;
      this.description = description;
   }

   public ResponseWrapper(String status, String description, T message) {
      this.status      = status;
      this.description = description;
      this.message     = message;
   }
   // Metodo statico per successo con dati specifici
   public static <T> ResponseWrapper<T> ok(T data) {
      return new ResponseWrapper<>("OK", "Operazione completata con successo.", data);
   }

   // Metodo statico per errore solo con messaggio
   public static <T> ResponseWrapper<T> error(String message) {
      return new ResponseWrapper<>("ERROR", message);
   }

   public String getStatus() {
      return status;
   }

   public void setStatus(String status) {
      this.status = status;
   }

   public String getDescription() {
      return description;
   }

   public void setDescription(String description) {
      this.description = description;
   }

   public T getMessage() {
      return message;
   }

   public void setMessage(T message) {
      this.message = message;
   }
}
