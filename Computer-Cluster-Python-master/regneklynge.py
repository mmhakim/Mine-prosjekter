#referer til ett eller flere Rack-objekter
from rack import Rack
from node import Node

class Regneklynge:
    #tar inn antall noder per rack i konstruktøren
    def __init__(self, filnavn):
        self._filnavn = filnavn
        self._racker = []
        self._nodePerRack = 0
        self.lesFraFil(self._filnavn)

    def lesFraFil(self, filnavn):
        f = open(filnavn)
        self._nodePerRack = int(f.readline())
        for linje in f:
            linje = linje.split(" ")
            for i in range(int(linje[0])):
                node = Node(int(linje[1]), int(linje[2]))
                self.leggTilNode(node)


    def leggTilNode(self, node):
        self._hentTomPlass().leggTilNode(node)

    #går gjennom Rackene
    #returnerer en tom plass hvis det finnes, hvis ikke lages et nytt Rack
    def _hentTomPlass(self):
        for rack in self._racker:
            if rack.erLedig():
                return rack
        #hvis det ikke finnes et ledig rack i racker-listen:
        nyrack = Rack(self._nodePerRack)
        self._racker.append(nyrack)
        return nyrack

    #returnerer antall prosessorer i regneklyngen
    def antProsessorer(self):
        ant = 0
        for rack in self._racker:
            ant += rack.hentProsessorantall()
        return ant

    def antRack(self):
        return len(self._racker)

    def noderMedNokMinne(self, paakrevdMinne):
        ant = 0
        for rack in self._racker:
            ant += rack.noderMedNokMinne(paakrevdMinne)
        print("Noder med minst " + str(paakrevdMinne) + "GB: " + str(ant))
        return ant

    def printInfo(self):
        racknr = 1
        for rack in self._racker:
            print("***** Rack " + str(racknr) + " *****")
            rack.printInfo()
            print()
            racknr += 1
