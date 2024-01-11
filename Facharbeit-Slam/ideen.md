# Numerische Nullstellenberechnung von Polynomfunktionen des Grades 4 oder höher

## TODOS

-   Einleitung
-   Zugbrücken -> Genau um möglichst wenn

## Weitere Ideen

-   Genaue Bestimmung Startpunkte
-   Ich mag Polynomfunktionen, mögt ihr Polynomfunktionen

### Fluss-Vergleich

-   Wir haben einen Fluss
-   Es soll eine Straße über den Fluss gebaut werden
-   Jetzt soll man Brücken bauen, wo Schiffe die maximal 15 Meter hoch sind durch passen
-   Dabei will man Ressourcen sparen und die Brücke möglichst klein bauen
-   Damit die Schiffe durch passen muss man sich den Mittelpunkt der Brücke angucken
-   Das sollte man so genau wie möglich machen

-   Dazu kann man den Fluss als Ganzrationale Funktion abbilden
-   Die Straße verläuft dabei auf der x-Achse
-   Um die Mittelpunkte der Brücken nun zu finden muss man die Nullstellen der Funktion finden

### Numerik

-   In der Schule lernt man mit der PQ-Formel Nullstellen ganzrationaler Funktionen zweiten Grades zu finden
-   Doch es gibt kein solche Formel für Ganzrationale Funktionen des Grades 4 oder höher
-   Dafür gibt es die Numerik
-   Es wird nicht mehr versucht eine Exakte Lösung zu finden, sondern eine Annährung

### Iteratives Verfahren

-   Um nun zu verstehen wie ein Verfahren in der Numerik funktioniert kann man sich eine Reihe von Zahlen von 0 bis 100 angucken
-   Bei dieser sucht man sich eine zufällige Zahl aus
-   Um diese herrauszufinden hat man die Möglichkeit nach einer Zahl zu frage und daraufhin findet man entweder die Zahl ist drunter oder drüber
-   Immer in der Mitte
    -   Animation Balken -> Halbieren -> ... -> Zahl gefunden
-   Da das immer wieder wiederholt wird nennt man es ein Iteratives verfahren
-   Das wird auch bei der Suche nach den Nullstellen verwendet

### Weierstraß-Iteration

-   Bei den Nullstellen hat man nun das Problem, dass es mehrere Nullstellen gibt
-   Es kann nicht mehr so einfach gefragt werden, ob die Gesuchte Stelle darüber oder drunter liegt
-   Allerdings stehen die Nullstellen durch die Funktion miteinander in Verbindung
-   Es ist nicht jede Nullstelle für sich alleine
-   Das wird sich bei der sogenannten Weierstraß-Iteration zunutze gemacht
-   Bei jedem Ablauf wird nun eine kompexere Formel ausgeführt
-   Da es aber mehrere Nullstellen gibt wird parallel für jede Nullstelle eine eigene Formel ausgeführt
-   Man kann sich das wie mehrere Prozesse, die zur gleichen Zeit ablaufen vorstellen
    -   Grafik mehrere Striche nebeneinander (sollen Prozesse darstellen)

### Programm

-   Jetzt kann man die Formel in dieses Programm eingeben
-   Dies geht die Operation so lange durch bis man zufriedengestellt ist
    -   Output des Programms
-   Jetzt kann man das Testen

### Zurück zu den Brücken

-   Jetzt wo man schnell und einfach die Mittelpunke der Brücken bestimmen kann kann geguckt werden, dass die Brücke zu dieser Stelle hoch genug ist
-   Dabei ist auch das jetzige Ergebnis teilweise ungenau, allerdings immer noch genauer simpeles Augenmaß
