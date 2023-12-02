Has 3 parts.
- header
- payload
- signature

these are seperated by dots
ex:
xxxx.yyyy.zzzz

**header**
has 2 parts.
- signing algorithm
- token type
```json
{
	"alg": "HS256",
	"typ": "JWT"
}
```

**payload**
contains claims. they are the statements about an entity(ex: user) and additional data.
```json
{
	"sub": "123123",
	"name": "dous",
	"admin": "true"
}
```

**signature**
To create the signature part you have to take the encoded header, the encoded payload, a secret, the algorithm specified in the header, and sign that.

For example if you want to use the HMAC SHA256 algorithm, the signature will be created in the following way:

```
HMACSHA256(
	base64UrlEncode(header) + "." + 
	base64UrlEncode(payload),
	secret)
```

![[Pasted image 20231125035349.png]]

