package com.kevin.medialogictest.enumerazioni;

import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public enum Mesi {

   A(1),
   B(2),
   C(3),
   D(4),
   E(5),
   H(6),
   L(7),
   M(8),
   P(9),
   R(10),
   S(11),
   T(12);

   private final int mese;

   Mesi(int mese) {
      this.mese = mese;
   }

   public int getMese() {
      return mese;
   }

   private static final Map<String, Mesi> codiceMese =
           Stream.of(values())
                   .collect(Collectors.toMap(
                           Enum::name,
                           e -> e
                   ));

   public static int meseNumero(String code) {
      Mesi v = codiceMese.get(code.toUpperCase());
      if (v == null)
         throw new IllegalArgumentException("Codice mese CF non valido: " + code);
      return v.mese;
   }
}
