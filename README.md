1. Putanje za SHA algoritam: <br>
	- dohvati sve GET: password/sha<br>
	- Pošalji json POST: password/sha<br>
2. Putanje za RSA algoritam: <br>
	- dohvati sve GET: password/rsa<br>
	- Pošalji json POST: password/rsa<br>
3. Putanje za AES algoritam: <br>
	- dohvati sve GET: password/aes<br>
	- Pošalji json POST: password/aes<br>
4. Putanje za Blowfish algoritam: <br>
	- dohvati sve GET: password/blowfish	<br>
	- Pošalji json POST: password/blowfish<br>
<br>
Svaki json izgleda:<br>
{<br>
         "password": "",<br>
    "key":""<br>
}<br>
<br>
SHA primjerak:<br>
{<br>
    "password": "frano",<br>
    "key": "mySecretKey12345"<br>
}<br>
<br>
RSA primjerak:<br>
{<br>
    "password": "frano",<br>   "key":"MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAy2QGmtGy9c6Xmp+4omLjBBy1DTNiDOiA7QqGCF8e7BilKyEZfjPl3jtXNFuIpfUmG0hWkScldJDHLfq0oydUR6Jm1pfdTrC0vX4E5OWIjTQZj5g6FkJ6X1CgSyj9K9yekbsm8ckT/fi0pAGHueZKEOl8e8nllhOZ9vo4w4jjvqm8X7s/dQ1ZPzFz5bEz3OeKG1la4ghrUK5nBq8BCR1lC3h0bWkM9nO9+Sk+Y7HX3v5zoPY2WlKlWO8NQ3sb3sBd5lLk3v0HLY+0ibEnJ4Jvg6f2h96I/y+pK3Ocgx7oMQEfNw7bUgBPy+M/Ur2Ue3IJzpn5VOelIaB3D6Sv7H8N7wIDAQAB"<br>
}<br>
<br>
AES primjerak:<br>
{<br>
    "password": "frano",<br>
    "key": "123456781234567812345678"<br>
}<br>
<br>

Blowfish primjerak:<br>
{<br>
    "password": "frano",<br>
    "key": "123456781234567812345678"<br>
}<br>

Docker naredbe:
<br>(preduvjet je instalirati Docker)
```
docker pull asalov21/spm-projekt
```
```
docker run -p 8080:8080 asalov21/spm-projekt
```
Otvoriti <http://localhost:8080/> za korištenje aplikacije
