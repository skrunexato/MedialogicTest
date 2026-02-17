package com.kevin.medialogictest.service;

import com.kevin.medialogictest.UserDto;
import com.kevin.medialogictest.enumerazioni.Mesi;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Period;
import java.time.Year;

@Service
public class UserService {


    public UserDto getDataDiNascitaAndEta(String codiceFiscale) {

        UserDto userDto = new UserDto();
        try {
            //pulisco il codice fiscale da eventuali spazi e lo metto in maiuscolo
            codiceFiscale = codiceFiscale.trim().toUpperCase();

            int annoDiNascita = calcoloAnnoDiNascita(Integer.parseInt(codiceFiscale.substring(6, 8)));
            int giornoDiNascita = Integer.parseInt(codiceFiscale.substring(9, 11));

            //attraverso un metodo presente nel mio enum chiamato "Mesi" estraggo il numero corretto del mese
            int meseDiNascita = Mesi.meseNumero(codiceFiscale.substring(8, 9));

            //verifico se è maschio o femmina applicando la regola dei +40 per poi estrapolare il giorno corretto
            if (giornoDiNascita > 40) {
                giornoDiNascita = giornoDiNascita - 40;
            }



            LocalDate dataDiNascita = LocalDate.of(annoDiNascita, meseDiNascita, giornoDiNascita);
            int eta = calcolaEta(dataDiNascita);

            userDto.setDataDiNascita(dataDiNascita);
            userDto.setEta(eta);
        }catch (Exception e) {
            return userDto;
        }
        return userDto;

    }

    /**
     * Siccome dal codice fiscale non si può calcolare il secolo si è deciso di usare
     * un determinato secolo secondo queste regole:
     * se applicando la regola 2000 + anno di nascita è superiore all'anno corrente si usa il 1900
     * esempio:
     * anno di nascita 80 non potrà essere 2080 perche non ancora nato quindi sarà 1980
     * se invece l'anno di nascita è minore o uguale all'anno corrente si sceglie l'anno piu vicino ad esempio
     * tra 1907 e 2007 si prenderà in considerazione 2007
     *
     * @param annoDiNascita
     * @return anno di nascita piu vicino tra i due
     */
    public int calcoloAnnoDiNascita(int annoDiNascita) {

        //se applicando la regola 2000+l'anno mi viene superiore all'anno corrente utilizzo il secolo 1900
        if (2000 + annoDiNascita > java.time.Year.now().getValue()) {
            return 1900 + annoDiNascita;
        }

        int anno1 = 1900 + annoDiNascita;
        int anno2 = 2000 + annoDiNascita;

        //utilizzo math abs per ottenere il valore assoluto senza segno
        int annoDiNascitaPiuVicino = Math.abs(anno1 - Year.now().getValue())
                <= Math.abs(anno2 - Year.now().getValue())
                ? anno1 : anno2;

        return annoDiNascitaPiuVicino;
    }

    /**
     * Metodo per calcolare l'età partendo dalla data di nascita, utilizzo Period.between per farmi restituire un oggetto
     * che contiene anno, mese e giorno e io con getYears ottengo gli anni che sono quelli che mi interessano
     * @param dataNascita
     * @return l'età ottenuta partendo dalla data di nascita
     */
    public int calcolaEta(LocalDate dataNascita) {
        LocalDate oggi = LocalDate.now();
        return Period.between(dataNascita, oggi).getYears();
    }
}
