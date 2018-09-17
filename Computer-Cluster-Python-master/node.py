class Node:

    def __init__(self, minnestorrelse, prosessorantall):
        self._minnestorrelse = minnestorrelse
        self._prosessorantall = prosessorantall

    def hentProsessorantall(self):
        return self._prosessorantall

    #returnerer True hvis Noden har nok minne
    def nokMinne(self, paakrevdMinne):
        return paakrevdMinne <= self._minnestorrelse

    def printInfo(self):
        print("Minnestørrelse:", self._minnestorrelse)
        print("Prosessorantall:", self._prosessorantall)
