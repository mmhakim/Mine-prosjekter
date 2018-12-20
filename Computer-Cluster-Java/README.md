# Computer cluster
> Dette prosjektet ble gjennomført på starten av studieløpet mitt.
> Oversikt over alle komponenter i en computer cluster, som brukes til å fordele tunge
> beregninger på mange maskiner slik at de kan jobbe i parallell. På den måten kan en
> imulering som ville tatt en måned å kjøre på en vanlig maskin, kjøres på computer
> cluster på noen timer i stedet.

- .txt-filene med et gitt format blir lest av Regneklyngen og lager Regneklyngen etter spesifikasjoner
i filen.

- Filene er bygget opp slik at første linje beskriver antall noder per rack, mens de neste linjene
beskriver hvor mange noder, med hvor mye minne og antall prosessorer som skal settes inn, slik.
```
Max antall noder per rack
AntallNoder MinnePerNode AntallProsessorerPerNode
AntallNoder MinnePerNode AntallProsessorerPerNode
...
```
- Hovedprogram gir oss et eksempel på bruk av Regneklyngen.
