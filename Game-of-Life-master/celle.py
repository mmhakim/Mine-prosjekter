#En celle kan settes til å være levende og død, sjekke om den er levende,
#og hente dens status i tegn form
class Celle:
    def __init__(self):
        #False vil si at cellen er død og vice-versa
        self._status = False

    def settDoed(self):
        self._status = False

    def settLevende(self):
        self._status = True

    def erLevende(self):
        return self._status

    def hentStatusTegn(self):
        if self.erLevende():
            return "O"
        else:
            return "."
