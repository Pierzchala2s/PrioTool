# PrioTool

Zielsetzung:
Die zu entwickelnde Analyse-Funktion soll die Qualität von eingegeben User Stories
anhand einer Metrik bewerten. Diese Metrik (eine Funktion) nimmt eine User Story als
Eingabe und liefert ein Maß für die Qualität der User Story.

Funktionale Anforderungen:
US_MD_1:
Als Anwender möchte ich eine bereits eingegebene User Story analysieren können, um
ihre Qualität festzustellen, und um somit auch die Qualität aller User Stories langfristig zu
optimieren und sicherzustellen.

Details:
Die Ausführung der Analyse-Funktion soll mit Hilfe des Befehls analyze mit der Angabe
einer bekannten ID einer User Story erfolgen.
Eine Ausgabe soll dann die bewertete Qualität der User Story in Prozent (%)
ausdrücken.

Beispiel (Die Zahl 1 entspricht der ID einer gegebenen User Story):
> analyze 1
Die User Story mit der ID 1 hat folgende Qualität:
100% (sehr gut)

Der Befehl analyze kann auch mit Parameter verwendet werden. Der Parameter –
all dient dazu, die durchschnittliche Qualität aller User Stories zu bewerten.

Beispiel:
> analyze – all
Ihre 13 User Stories haben durchschnittlich folgende Qualität:
87,5% (gut)

Die genaue bzw. vollständige prozentuale Verteilung zur Bewertung der Qualität sollten
sie im Rahmen ihrer Metrik selber definieren.
Mit dem Parameter – details lassen sich die identifizierten Defizite bei der Analyse
einer User Story ausgeben.

Beispiel Nr. 1:
> analyze 2 – details
Die User Story mit der ID 2 hat folgende Qualität:
70% (befriedigend)
Details:
Kein schriftlicher Mehrwert zu erkennen (- 30%)

Beispiel Nr. 2:
> analyze 1 – details
Die User Story mit der ID 1 hat folgende Qualität:
100% (sehr gut)
Details:
Alles ok

Beispiel Nr. 3: Mit dem Parameter – hints können zusätzliche Hinweise (engl.: hints)
zur Optimierung ausgegebenen werden:
> analyze 3 – details – hints
Die User Story mit der ID 3 hat folgende Qualität:
50% (ausreichend)
Details:
Kein schriftlicher Mehrwert zu erkennen (- 30%)
Akteur („Student“) ist nicht bekannt (- 20%)
Hints:
Fügen sie einen schriftlichen Mehrwert hinzu!
Registrieren sie einen neuen Akteur!

Mindestens ein weiteres Defizit, das zu einer prozentualen Herabstufung der Qualität
einer User Story führt, sollten sie selber definieren und als Algorithmus implementieren.
Falls ein Akteur in einer User Story nicht bekannt ist, so kann er in ihrem System
hinzugefügt werden.

Dazu dient folgende User Story:
US_MD_2:
Als Anwender möchte ich Bezeichnungen für Akteure in das Prio-Tool registrieren, um
die Qualitätsanalyse bei einer User Story zu optimieren.
Detail: Zur Eintragung von Bezeichnungen für Akteure soll der Befehl addElement
dienen. Ein Akteur kann mit dem zusätzlichen Parameter – actor registriert werden.
Zusammenhängendes Beispiel (in Zusammenhang mit dem obigen Beispiel):
> analyze 3 – details – hints
Die User Story mit der ID 3 hat folgende Qualität:
50% (ausreichend)
Details:
Kein schriftlicher Mehrwert zu erkennen (- 30%)
Akteur („Student“) ist nicht bekannt (- 20%)
Hints:
Fügen sie einen schriftlichen Mehrwert hinzu!
Registrieren sie einen neuen Akteur!

> addElement – actor Student

Der Akteur Student wurde im System registriert!

> analyze 3 – details

Die User Story mit der ID 3 hat folgende Qualität:
30% (befriedigend)
Details:
Kein schriftlicher Mehrwert zu erkennen (- 30%)

Die aktuell registrierten Akteure können mit dem Befehl actors ausgeben werden.

Beispiel:
> actors
Student
Professor

US_MD_3:
Als Anwender möchte ich den zuletzt eingegebenen Befehl rückgängig machen, damit
ich falsch eingegebene Eingaben löschen kann. Damit soll allgemein die Usability
(Bedienbarkeit) des Tools verbessert werden.

Details:
Das Rückgängigmachen von Befehlen soll durch den Befehl undo erfolgen. In dieser
Version des Prio-Tools soll das Eintragen von User Stories sowie von Akteuren
rückgängig gemacht werden.
Zusammenhängendes Beispiel, welches in ihrem Tool so abspielbar sein sollte:

> addElement – actor Professor
The actor Professor was registered in the system!

> addElement – actor Student
The actor Student was registered in the system!

> actors
Professor
Student

> undo

> actors
Professor

> undo

> actors
no actors given

> undo

nothing to undo!

Auch das Controlling von User Stories soll in ihrem Tool optimiert werden. Dazu dient
folgende User Story:

US_MD_4:
Als Anwender möchte ich angeben, dass eine User Story abgeschlossen wurde, um
somit ein einfaches Tracking meiner Stories durchzuführen.
Details:

Drei Status sollten unterstützt werden: done, progess, todo
Der Befehl dump soll über eine entsprechende Funktionalität zur Ausgabe von User
Stories mit einem bestimmten Status ergänzt werden.

Beispiel:
> status 3 done
Die User Story mit der ID 3 wurde auf den Status done gesetzt

> dump – status done
[Strukturierte Ausgabe der User Stories, die den Status „done“
haben]

