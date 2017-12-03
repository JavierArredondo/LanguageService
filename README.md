Language Service
=======

To consume the post method, just consult the following url:
`207.154.197.207:1313/code`

Where _code_ is a JSON, the structure is about (only for execute code):

```json
{
  "lang": "Some language (Python, C or Java)",
  "code": "Block of code"
}
``` 
If you want execute code and check this, the structure is about:
```json
{
  "lang": "Python",
  "code": "def suma(x, y):\n\treturn x + y\nprint (\"Hola\")",
  "p1": "1,2",
  "p2": "2,3",
  "p3": "3,4",
  "function": "suma" 
  }
``` 
Where _p1_, _p2_ and _p3_ are the parameters for the function that you want check and _function_ is the function that you want check.



In this moment, the service support Python, C and Java, but to implement another language is easy.