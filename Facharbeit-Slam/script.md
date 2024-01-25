#### SCRIPT

# EINLEITUNG

-   Wir haben einen Fluss
-   Dort bauen wir eine Straße drüber
-   Deswegen müssen Brücken gebaut werden

# EINLEITUNG MITTELPUNKTE DER BRÜCKEN

-   Schiffe sollen drunter durch
-   Schiffe sind hoch, fahren aber nur in der Mitte
-   Also muss die Brücke nur in der Mitte hoch genug sein und es können Ressaurcen gespart werden

# EINLEITUNG FUNKTION

-   Wir wollen die Mittelpunkte möglichst genau haben
-   Dafür stelle ich jetzt ein Verfahren vor

-   Zuerst kann der Fluss, bzw. die Mitte des Flusses mit einer ganzrationalen Funktion abgebildet werden
-   Dabei verläuft die Straße auf der x-Achse
-   Die Nullstellen der Funktion sind die Mittelpunkte der Brücken

-   Unser Ziel ist es jetzt ein Werkzeug zu finden mit dem man dieses Problem einfach aber genau lösen kann

# PQ-FORMEL

-   Diese Funktion ist allerdings sehr Komplex deswegen fangen wir bei einer einfacheren Funktion an und arbeiten uns langsam hoch
-   Dafür gucken wir zurück in die 10. Klasse wo man die PQ-Formel gelernt hat
-   Diese nimmt eine Funktion und gibt uns die Nullstellen dieser
    -> Damit könnte man unser Problem lösen, jedoch funktioniert die PQ-Formel nur für diese einfacheren Funktionen

# WEIERSTRAß-KORREKTUR

-   Beispielsweise funktioniert die PQ-Formel bei dieser Funktion nicht
-   Deswegen benutzen wir ein anderes Verfahren unzwar die Weierstraß-Korrektur
-   Ist allerdings fundamental anders als die PQ-Formel
-   Gibt nicht direkt die Nullstellen zurück
-   Man gibt einen Wert und bekommt einen näheren an der Nullstelle zurück

-   Visualisiert

-   Also gebe ich diesen Wert ein
-   Und bekomme einen genaueren Zurück

# WEIERSTRAß-ITERATION

-   Jetzt sollte aber auffallen, dass man die Nullstellen nur damit nicht findet
-   Jedoch kann man diese Weierstraß-Korrektur öfters hintereinander ausführen
-   Zeigen
-   Dabei ist es jedoch so, dass man niemals genau die Nullstelle findet, sondern nur sich annährt
-   Das ist allerdings kein Problem, da beispielsweise bei der Brücke Nanometer keine Rolle mehr spielen und es nicht soooo genau seien muss

-   Dises ganze Wiederholen nennen wir Weierstraß-Iteration
-   Wie man sehen kann ist es nur eine Kürzere schreibweise für das Wiederholen von vielen Weierstraß-Korrekturen

# WEIERSTRAß FÜR MEHRERE NULLSTELLEN

-   Was ist nun wenn wir allerdings mehrere oder alle Nullstellen finden wollen?
-   Dazu können wir die es parallel für alle Nullstellen laufen lassen
-   Dabei beschäftigt jede Weierstraß-Iteration sich mit einer Nullstelle
    -> Das bedeutet wir können jetzt Nullstellen einer komplexerern Funktion finden bzw. uns annähren

# ANFANGSPROBLEM

-   Zurück zum Anfangsproblem: Wie finden wir nun die Nullstellen bzw. Mittelpunkte der Brücken
-   Dazu haben wir grade unser "Werkzeug" die Weierstraß-Iteration erarbeitet
-   Dieses brauch jedoch für den Anfang Startpunkte

# STARTPUNKTE

-   Dabei haben wir glück, da ja ungefähr klar ist wo sich die Brücken befinden
-   Deshalb wählen wir einfach einen zufälligen Punkt in der nähe oder auf jeder Brücke

# ENTSPURT

-   Diese Punkte geben wir in die jeweilige Weierstraß-Iteration und bekommen unsere Mittelpunkte der Brücke
-   Jetzt können die Brücken, mit möglichst wenig Material gebaut werden und die Schiffe passen durch
-   Dieses Verfahren kann nun auch für andere Fälle genutzt werden

# ZUSAMMENFASSUNG

TODO ÜBERGANG

1. Fluss als ganzrationale Funktion abbilden
2. Startpunkte in der Nähe der Nullstellen "erraten"
3. Startpunkte der Weierstraß-Iteration geben und man bekommt das Ergebnis
