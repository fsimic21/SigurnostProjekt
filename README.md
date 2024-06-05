Putanje za SHA algoritam: 
	dohvati sve GET: password/sha
	Pošalji json POST: password/sha
Putanje za RSA algoritam: 
	dohvati sve GET: password/rsa
	Pošalji json POST: password/rsa
Putanje za AES algoritam: 
	dohvati sve GET: password/aes
	Pošalji json POST: password/aes
Putanje za Blowfish algoritam: 
	dohvati sve GET: password/blowfish	
	Pošalji json POST: password/blowfish

Svaki json izgleda:
{
    "password": "",
    "key":""
}

SHA primjerak:
{
    "password": "frano",
    "key": "mySecretKey12345"
}

RSA primjerak:
{
    "password": "frano",
    "key":"MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAy2QGmtGy9c6Xmp+4omLjBBy1DTNiDOiA7QqGCF8e7BilKyEZfjPl3jtXNFuIpfUmG0hWkScldJDHLfq0oydUR6Jm1pfdTrC0vX4E5OWIjTQZj5g6FkJ6X1CgSyj9K9yekbsm8ckT/fi0pAGHueZKEOl8e8nllhOZ9vo4w4jjvqm8X7s/dQ1ZPzFz5bEz3OeKG1la4ghrUK5nBq8BCR1lC3h0bWkM9nO9+Sk+Y7HX3v5zoPY2WlKlWO8NQ3sb3sBd5lLk3v0HLY+0ibEnJ4Jvg6f2h96I/y+pK3Ocgx7oMQEfNw7bUgBPy+M/Ur2Ue3IJzpn5VOelIaB3D6Sv7H8N7wIDAQAB"
}

AES primjerak:
{
    "password": "frano",
    "key": "123456781234567812345678"
}


Blowfish primjerak:
{
    "password": "frano",
    "key": "123456781234567812345678"
}

