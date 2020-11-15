## ParkyPark
Dette er en prototype for en parkeringsløsning. 

## Beskrivelse
ParkyPark er en webapplikasjon som lar brukere legge ut og leie parkeringsplasser. 
Tanken er at leietakeren sparer penger på å ikke parkere i (for eksempel)
et parkeringshus, mens utleieren tjener penger ved å legge prisen lavere enn andre parkeringsplasser
i området. Man kan på et vis si at applikasjonen er som AirBnB for parkeringsplasser.

## Hvordan komme i gang
- Last ned og installer IntelliJ. Community Edition er tilstrekkelig.  ([Last ned her](https://www.jetbrains.com/idea/download/#section=windows))
- Åpne IntelliJ
- Velg Get from Version Control
![](images/get_from_vc.png)
- Last ned og installer Git
![](images/install_git.png)
- Hent url fra Github. Trykk på den grønne Code knappen øverst og kopier linken.

![](images/url_github.png)
- Lim inn URLen og trykk Clone nederst.
![](images/lim_inn_url.png)
- Vent mens prosjektet bygger.
![](images/vent.png)
- Trykk på Load Gradle Changes

![](images/load_gradle_changes.png)
- Finn frem til main i prosjekt strukturen som vist på bildet under. Høyre klikk på main og velg Run 'Main.main()'.
![](images/kjor_main.png)
- Nederst i IntelliJ vil det åpne seg et nytt vindu. Trykk på linken markert nedenfor og webapplikasjonen vil åpne seg.(Fungerer ikke å åpne i Internet Explorer)
![](images/javalin.png)

## Hvordan kjøre tester
- Finn frem til test i prosjekt strukturen som vist på bildet under. Høyre klikk på test og velg Run 'Tests in 'parkypark....'.
![](images/kjor_test.png)

## Avhengigheter for prototypen
- [Javalin (bruker slf4j-simple](https://javalin.io/documentation)
- [Vue](https://vuejs.org/v2/guide/)
- [Jackson](https://github.com/FasterXML/jackson-docs)

Avhengigheter for testing
- [JUnit5](https://junit.org/junit5/docs/current/user-guide/)
- [Mockito](https://site.mockito.org/)
