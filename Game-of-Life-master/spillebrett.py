from random import randint
from celle import Celle
import sys


class Spillebrett:
    def __init__(self, rader, kolonner):
        self._rader = rader
        self._kolonner = kolonner
        self._rutenett = []
        self._generasjonsnummer = 0

        #lager rader og kolonner i spillebrettet
        for r in range(self._rader):
            #lager en liste for raden
            self._rutenett.append([])
            for k in range(self._kolonner):
                celle = Celle()
                self._rutenett[r].append(celle)

        #spillebrettet genereres etter at den har brettet har blitt laget
        self.generer()


    #tar et random tall mellom 0 og 3. Hvis det er lik 3 settes cellen til å
    #være levende
    def generer(self) :
        for i in range (self._rader):
            for j in range (self._kolonner):
                rand = randint(0,3)

                if rand == 3 :
                    self._rutenett[i][j].settLevende()


    #printer ut spillebrettet representert med tegn
    def tegnBrett(self):
        print("\n"*50)
        for rad in self._rutenett:
            for kolonne in rad:
                print(kolonne.hentStatusTegn(), end='')
            print()

    #oppdaterer cellene for den nye generasjonen
    #etter at den er ferdig oppdatert, øker generasjonsnummer med 1
    def oppdatering(self):
        dodTilLevende = []
        levendeTilDod = []


        for r in range(self._rader):
            for k in range(self._kolonner):
                denneCellen = self._rutenett[r][k]

                #lager en liste med cellens naboer vha. finnNabo()
                naboliste = self.finnNabo(r,k)
                levendeNaboer = 0

                #finner ut hvor mange levende naboceller det er
                for nabo in naboliste:
                    if nabo.erLevende():
                        levendeNaboer += 1

                #hvis cellen lever
                if denneCellen.erLevende():

                    #færre enn to levende naboceller --> cellen dør
                    if levendeNaboer < 2:
                        levendeTilDod.append(denneCellen)

                    #ved to eller tre levende naboceller --> cellen lever videre
                    if levendeNaboer == 2 or levendeNaboer == 3:
                        continue

                    #hvis cellen har mer enn 3 levende naboceller --> cellen dør
                    if levendeNaboer > 3:
                        levendeTilDod.append(denneCellen)

                #hvis cellen er død
                elif not denneCellen.erLevende():

                    #blir levende hvis levendeNaboer == 3
                    if levendeNaboer == 3:
                        dodTilLevende.append(denneCellen)

                #endrer status på celler før oppdateringen
                for celle in levendeTilDod:
                    celle.settDoed()

                for celle in dodTilLevende:
                    celle.settLevende()

        #øker generasjonsnummer når oppdateringen er ferdig
        self._generasjonsnummer += 1

    #returner antall levende celler i spillebrettet
    def finnAntallLevende(self):
        antLevende = 0
        for rad in self._rutenett:
            for kolonne in rad:
                if kolonne.erLevende():
                    antLevende +=1
        return antLevende


    #tar inn koordinater til en celle og returnerer en liste med dens naboer
    def finnNabo (self, rad, kolonne) :
        naboliste = []
        for i in range (-1, 2) :
            for j in range (-1, 2) :
                naboRad = rad+i
                naboKolonne = kolonne+j
                if (naboRad == rad and naboKolonne == kolonne) != True :
                    if (naboRad < 0 or naboKolonne < 0 or naboRad > self._rader-1\
                    or naboKolonne > self._kolonner-1) != True:
                        naboliste.append(self._rutenett[naboRad][naboKolonne])
        return naboliste

    #printer ut generasjonsnummer og antall levende celler
    def printInfo(self):
        print("Generasjonnummer:", self._generasjonsnummer, "- Antall levende\
 celler:", self.finnAntallLevende())
