
Datoformat i URL query params:
----------------------------------------------

Inntil videre må vi bruke dette formatet (som er standard):   Sun, 06 Nov 1994 08:49:37 GMT

Disse to requestene er gyldige og fungerer:

http://localhost:8080/sensorweb/api/sensorGroups/101/readings/between?from=Fri,%2009%20Jan%202015%2008:30:30%20GMT&to=Fri,%2009%20Jan%202015%2009:30:30%20GMT

http://localhost:8080/sensorweb/api/sensorGroups/101/readings/after?after=Fri,%2009%20Jan%202015%2014:30:00%20GMT


Query på siteIdent eksempel:
--------------------------------------------

http://localhost:8080/sensorweb/api/sites/query/ident?siteIdent=JanBananSite
