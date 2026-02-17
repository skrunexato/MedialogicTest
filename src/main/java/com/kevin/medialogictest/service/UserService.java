package com.kevin.medialogictest.service;

import com.kevin.medialogictest.enumerazioni.Mesi;

public class UserService {


   String getDataDiNascita(String codiceFiscale){
      //pulisco il codice fiscale da eventuali spazi e lo metto in maiuscolo
      codiceFiscale = codiceFiscale.trim().toUpperCase();

      String annoDiNascita = codiceFiscale.substring(6,8);
      String giornoDiNascita =  codiceFiscale.substring(9,11);
      //attraverso un metodo presente nel mio enum chiamato "Mesi" estraggo il numero corretto del mese
      String meseDiNascita = Mesi.meseNumero(codiceFiscale.substring(8,9));

      //verifico se è maschio o femmina applicando la regola dei +40 per poi estrapolare il giorno corretto
      if(Integer.parseInt(giornoDiNascita)>40){
         giornoDiNascita = String.valueOf(Integer.parseInt(giornoDiNascita)-40);
      }

      //per quanto riguarda l'anno di nascita non posso stabilire il secolo dal CF
      //quindi si è scelto di impostare il secolo piu vicino ad oggi




   }

   /**
    * siccome dal codice fiscale non si può calcolare il secolo si è deciso di usare
    * un determinato secolo secondo queste regole:
    * se applicando la regola 2000 + anno di nascita è superiori all'anno corrente si usa il 1900
    * esempio:
    * anno di nascita 80 non potrà essere 2080 perche non ancora nato quindi sarà 1980
    * se invece l'anno di nascita è minore o uguale all'anno corrente si sceglie l'anno piu vicino ad esempio
    * tra 1907 e 2007 si prenderà in considerazione 2007
    * @param annoDiNascita
    * @return
    */
   public int calcoloDelSecolo(int annoDiNascita){

      //se applicando la regola 2000+l'anno mi viene superiore all'anno corrente utilizzo il secolo 1900
      if(2000+annoDiNascita >java.time.Year.now().getValue() ){
         return 1900+annoDiNascita;
      }

   }
}
