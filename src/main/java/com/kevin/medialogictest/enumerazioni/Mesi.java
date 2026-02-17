package com.kevin.medialogictest.enumerazioni;

import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public enum Mesi {

   A("01"),
   B("02"),
   C("03"),
   D("04"),
   E("05"),
   H("06"),
   L("07"),
   M("08"),
   P("09"),
   R("10"),
   S("11"),
   T("12");

   private final String mm;

   Mesi(String mm) { this.mm = mm; }

   public String mm() { return mm; }

   private static final Map<String, Mesi> codiceMese =
         Stream.of(values()).collect(Collectors.toMap(
               e -> e.name(),
               e -> e
         ));

   public static String meseNumero(String code) {
      Mesi v = codiceMese.get(code.toUpperCase());
      if (v == null) throw new IllegalArgumentException("Codice mese CF non valido: " + code);
      return v.mm();
   }
}
