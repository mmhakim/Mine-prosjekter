from regneklynge import Regneklynge


abel = Regneklynge("regneklynge.txt")

abel.noderMedNokMinne(32)
abel.noderMedNokMinne(64)
abel.noderMedNokMinne(128)

print("\nAntall prosessorer:",abel.antProsessorer())
print("Antall rack:", abel.antRack())
