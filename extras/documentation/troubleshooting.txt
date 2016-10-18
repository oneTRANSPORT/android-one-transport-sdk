Troubleshooting
===============

We have error logging enabled on HTTP requests.  You should see in the
Android log messages like these:

        D/OkHttp  ( 7250): --> GET https://dev.onetransport.uk.net/ONETCSE01/Oxfordshire/v2.0/VariableMessageSign/All/la?rt=3 http/1.1
        D/OkHttp  ( 7250): X-M2M-Origin: C-Y249U2FtcGxlTWFwQXBwMixvdT1yb290
        D/OkHttp  ( 7250): Authorization: Bearer ****************
        D/OkHttp  ( 7250): X-M2M-RI: 5aae25ed-d2ef-4b45-a722-dda5adf9a9ac
        D/OkHttp  ( 7250): Accept: application/json
        D/OkHttp  ( 7250): User-Agent: OneM2M-Android
        D/OkHttp  ( 7250): --> END GET
        D/OkHttp  ( 7250): <-- 200 OK https://dev.onetransport.uk.net/ONETCSE01/Oxfordshire/v2.0/VariableMessageSign/All/la?rt=3 (181ms)
        D/OkHttp  ( 7250): Server: nginx/1.9.10
        D/OkHttp  ( 7250): Date: Tue, 18 Oct 2016 09:40:00 GMT
        D/OkHttp  ( 7250): Content-Type: application/vnd.onem2m-res+json
        D/OkHttp  ( 7250): Content-Length: 157993
        D/OkHttp  ( 7250): Connection: keep-alive
        D/OkHttp  ( 7250): X-M2M-RI: 5aae25ed-d2ef-4b45-a722-dda5adf9a9ac
        D/OkHttp  ( 7250): X-M2M-RSC: 2000
        D/OkHttp  ( 7250): OkHttp-Sent-Millis: 1476783601021
        D/OkHttp  ( 7250): OkHttp-Received-Millis: 1476783601201
        D/OkHttp  ( 7250): {"m2m:cin":{"cnf":"application/json","con": ...
        D/OkHttp  ( 7250): <-- END HTTP (157993-byte body)

Retrofit logs the contents of outgoing and incoming messages outside of the
SSL encryption so you can see what is going back and forth.
