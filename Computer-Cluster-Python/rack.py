#referer til en eller flere node-objekter
from node import Node

class Rack:
    def __init__(self, maksNoder):
        self._maksNoder = maksNoder
        self._nodeliste = []

    def hentProsessorantall(self):
        ant = 0
        for node in self._nodeliste:
            ant += node.hentProsessorantall()
        return ant

    #sjekker om det er ledig plass i Racket
    def erLedig(self):
        if len(self._nodeliste) < self._maksNoder:
            return True
        else:
            return False

    #legger til node hvis det er ledig plass i racket
    def leggTilNode(self, node):
        self._nodeliste.append(node)

    #returnerer antall noder i racket med nok minne
    def noderMedNokMinne(self, paakrevdMinne):
        ant = 0
        for node in self._nodeliste:
            if node.nokMinne(paakrevdMinne):
                ant += 1
        return ant

    def printInfo(self):
        nodenr = 1
        for node in self._nodeliste:
            print("Node " + str(nodenr) + ":")
            node.printInfo()
            print()
            nodenr += 1
