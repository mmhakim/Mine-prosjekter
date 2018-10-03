from spillebrett import Spillebrett

print("*** Velkommen til Game Of Life spillet! ***\n")
print("Skriv inn dimensjonene du vil ha på spillebrettet ditt.")

rader = int(input("Rader: "))
kolonner = int(input("Kolonner: "))

#tegner spillebrettet og skriver det ut
spillebrett = Spillebrett(rader,kolonner)
spillebrett.tegnBrett()
spillebrett.printInfo()


kommando = None

#fortsetter spillet helt til brukern ønsker å avslutte
while kommando != "q":
    kommando = input("\n- Trykk f for å fortsette.\n- Trykk q for å avslutte\n")

    #hvis brukeren ønsker å fortsette, oppdateres breddet og printes ut
    if kommando == "f":
        spillebrett.oppdatering()
        spillebrett.tegnBrett()
        spillebrett.printInfo()

    elif kommando != "q" and kommando != "f":
        print("\nDu skrev inn feil bokstav, mann!")
